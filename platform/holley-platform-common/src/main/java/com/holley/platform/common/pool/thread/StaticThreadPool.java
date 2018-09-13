package com.holley.platform.common.pool.thread;

import java.util.LinkedList;
import java.util.Stack;

public class StaticThreadPool implements ThreadPool {

    private Stack<WorkerThread> waiting;
    private int                 max;
    private Class<Object>       workerClass;
    private Object              instance;
    private LinkedList<Object>  performWorkDataPool;
    private DispatchThread      dispatchThread;
    private boolean             dispatchWaitting = false;
    private boolean             noThreadWaitting = false;
    private boolean             PoolRungSign     = true;

    // private DebugWatch watch=null;

    /**
     * Creates a new Pool instance
     * 
     * @param max Max number of handler threads
     * @param workerClass Name of Worker implementation
     * @throws Exception
     */
    class DispatchThread extends Thread {

        synchronized void wake() {
            notify();
        }

        synchronized void rest() {
            try {
                boolean requireWait = true;

                synchronized (waiting) {
                    requireWait = noThreadWaitting && waiting.isEmpty();
                }
                if (!requireWait) {
                    synchronized (performWorkDataPool) {
                        requireWait = (dispatchWaitting && performWorkDataPool.isEmpty());
                    }
                }
                if (requireWait) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public DispatchThread(String id) {
            super(id);
        }

        public void run() {

            while (PoolRungSign) {
                WorkerThread thread = null;
                Object data = null;

                synchronized (performWorkDataPool) {
                    if (!performWorkDataPool.isEmpty()) {
                        data = performWorkDataPool.removeFirst();
                    }

                }
                if (data != null) {
                    while (true) {
                        synchronized (waiting) {
                            if (!waiting.isEmpty()) {
                                thread = (WorkerThread) waiting.pop();
                            }
                        }
                        if (thread != null) {
                            thread.wake(data);
                            break;
                        } else {
                            noThreadWaitting = true;
                            rest();
                            if (!PoolRungSign) {
                                return;
                            }
                            noThreadWaitting = false;
                        }
                    }
                } else {
                    dispatchWaitting = true;
                    rest();
                    if (!PoolRungSign) {
                        return;
                    }
                    dispatchWaitting = false;
                }

            }
        }
    }

    public StaticThreadPool(int max, Class<Object> workerClass, Object instance) throws Exception {
        this.setMax(max);
        waiting = new Stack<WorkerThread>();
        performWorkDataPool = new LinkedList<Object>();
        this.setWorkerClass(workerClass);
        PoolWorker worker;
        WorkerThread wThread;
        this.setInstance(instance);
        dispatchThread = new DispatchThread(workerClass.getName() + "Dispatch");
        dispatchThread.start();
        // watch= new DebugWatch(workerClass.getName()+"wait count=",30*1000);
        for (int i = 0; i < max; i++) {
            worker = (PoolWorker) workerClass.newInstance();
            worker.setInstance(instance);
            wThread = new WorkerThread(workerClass.getName() + " Worker#" + i, worker);
            wThread.start();
            waiting.push(wThread);
        }
    }

    /**
     * Convience method used by WorkerThread to put Thread back on the stack
     */
    public void performWork(Object data) {
        synchronized (performWorkDataPool) {
            performWorkDataPool.addLast(data);
        }
        if (dispatchWaitting) {
            dispatchThread.wake();
        }
    }

    public void close() {
        PoolRungSign = false;
        WorkerThread thread;
        synchronized (waiting) {
            if (!waiting.isEmpty()) {
                thread = (WorkerThread) waiting.pop();
                thread.wake(null);
            }
        }
        synchronized (performWorkDataPool) {
            performWorkDataPool.clear();
        }
        dispatchThread.wake();
    }

    private boolean push(WorkerThread worker) {
        boolean stayAround = false;

        if (PoolRungSign) {
            Object data = null;

            synchronized (performWorkDataPool) {
                if (!performWorkDataPool.isEmpty()) {
                    data = performWorkDataPool.removeFirst();
                }

            }
            if (data != null) {
                worker.wake(data);
                return true;
            }

            synchronized (waiting) {
                stayAround = true;
                waiting.push(worker);
            }
            if (noThreadWaitting) {
                dispatchThread.wake();
            }
        }
        return stayAround;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Object getInstance() {
        return instance;
    }

    public void setWorkerClass(Class<Object> workerClass) {
        this.workerClass = workerClass;
    }

    public Class<Object> getWorkerClass() {
        return workerClass;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    /**
     * Handler class for perform work requested by the Pool.
     */

    class WorkerThread extends Thread {

        private PoolWorker worker;
        private Object     data;

        /**
         * Creates a new WorkerThread
         * 
         * @param id Thread ID
         * @param worker Worker instance associated with the WorkerThread
         */
        WorkerThread(String id, PoolWorker worker) {
            super(id);
            this.worker = worker;
            data = null;
        }

        /**
         * Wakes the thread and does some work
         * 
         * @param data Data to send to the Worker
         * @return void
         */
        synchronized void wake(Object data) {
            this.data = data;
            notify();
        }

        synchronized void rest() {
            try {
                if ((data == null) && PoolRungSign) {
                    wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /**
         * WorkerThread's thread routine
         */

        public void run() {
            boolean stop = false;
            while ((!stop) && PoolRungSign) {
                Object processData;
                synchronized (this) {
                    processData = data;
                    data = null;
                }
                if (processData == null) {
                    rest();
                } else {
                    worker.run(processData);
                    if (PoolRungSign) {
                        stop = !(push(this));
                    }
                }

            }
        }

    }
}
