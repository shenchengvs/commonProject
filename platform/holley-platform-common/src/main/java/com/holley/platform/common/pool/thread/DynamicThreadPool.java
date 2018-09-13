package com.holley.platform.common.pool.thread;

import java.util.Stack;

/**
 * Implements Thread Pooling. Thread Pool simply keeps a bunch of suspended threads around to do some work.
 */
public class DynamicThreadPool implements ThreadPool {

    private Stack<WorkerThread> waiting;
    private int                 max;
    private Class<Object>       workerClass;
    private Object              instance;
    private boolean             PoolRungSign = true;

    /**
     * 建立线程池
     * 
     * @param max Max number of handler threads
     * @param workerClass Name of Worker implementation
     * @param instance
     * @throws Exception
     */
    public DynamicThreadPool(int max, Class<Object> workerClass, Object instance) throws Exception {
        this.max = max;
        waiting = new Stack<WorkerThread>();
        this.workerClass = workerClass;
        PoolWorker worker;
        WorkerThread wThread;
        this.instance = instance;
        for (int i = 0; i < max; i++) {
            worker = (PoolWorker) workerClass.newInstance();
            worker.setInstance(instance);
            wThread = new WorkerThread(workerClass.getName() + " Worker#" + i, worker);
            wThread.start();
            waiting.push(wThread);
        }
    }

    /**
     * Request the Pool to perform some work.
     */
    public void performWork(Object data) {
        WorkerThread thread = null;
        synchronized (waiting) {
            if (!waiting.empty()) {

                thread = (WorkerThread) waiting.pop();
            }
        }
        if (thread == null) {
            try {
                PoolWorker worker = (PoolWorker) workerClass.newInstance();
                worker.setInstance(instance);
                thread = new WorkerThread(workerClass.getName() + " additional worker", worker);
                thread.start();
            } catch (Exception ex) {
                throw new java.lang.RuntimeException("Problem creating instance of", ex);
            }
        }

        thread.wake(data);
    }

    /**
     * 
     */
    public void close() {
        WorkerThread thread;
        PoolRungSign = false;
        synchronized (waiting) {
            if (!waiting.isEmpty()) {
                thread = (WorkerThread) waiting.pop();
                thread.wake(null);
            }
        }
    }

    /**
     * Convience method used by WorkerThread to put Thread back on the stack
     * 
     * @param w WorkerThread to push
     * @return boolean True if pushed, false otherwise
     */
    private boolean push(WorkerThread worker) {
        boolean stayAround = false;
        if (PoolRungSign) synchronized (waiting) {
            if (waiting.size() < max) {
                stayAround = true;
                waiting.push(worker);
            }
        }
        return stayAround;
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
                if ((data == null) && PoolRungSign) wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public void run() {
            boolean stop = false;
            while (PoolRungSign && (!stop)) {

                Object processData;
                synchronized (this) {
                    processData = data;
                    data = null;
                }
                if (processData == null) {
                    rest();
                    continue;
                } else {
                    worker.run(processData);
                }
                if (PoolRungSign) stop = !(push(this));
            }
        }
    }
}
