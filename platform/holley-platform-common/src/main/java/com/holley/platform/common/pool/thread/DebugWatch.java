package com.holley.platform.common.pool.thread;

public class DebugWatch extends Thread {

    private int count = 0;
    long        interval;
    String      message;

    public DebugWatch(String message, long interval) {
        super();
        this.interval = interval;
        this.message = message;
        this.start();
    }

    public synchronized int add(int inc) {
        count += inc;
        return count;
    }

    public synchronized int setCount(int nCount) {
        int c = count;
        count = nCount;
        return c;
    }

    public void run() {
        int lastPrint = 0;
        while (true) {
            if (lastPrint != count) {
                System.out.print((new java.util.Date()).toString());
                System.out.println(message + String.valueOf(count));
                lastPrint = count;
            }
            try {
                Thread.sleep(interval);
            } catch (Exception e) {
            }

        }

    }
}
