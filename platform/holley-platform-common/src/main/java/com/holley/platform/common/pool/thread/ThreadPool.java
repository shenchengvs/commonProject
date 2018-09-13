package com.holley.platform.common.pool.thread;

public interface ThreadPool {

    public void performWork(Object data);

    public void close();
}
