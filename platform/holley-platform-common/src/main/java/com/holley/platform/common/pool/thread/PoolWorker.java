package com.holley.platform.common.pool.thread;

public interface PoolWorker {

    /**
     * @param data
     */
    public void run(Object data);

    /**
     * @param instance
     */
    public void setInstance(Object instance);
}
