package com.holley.platform.common.pool;

import java.util.Date;

import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.util.StringUtil;

public abstract class BaseJob implements Runnable {

    protected String jobName;

    public void run() {
        beforeRun();
        execute();
        afterRun();
    }

    public void createJobName(String moduleName) {
        this.jobName = moduleName + "_" + StringUtil.randomString(10);
    }

    public abstract void execute();

    public void beforeRun() {
        ThreadInfo threadInfo = getThreadInfo();
        threadInfo.setStart(new Date());
        threadInfo.setStatus(ThreadInfo.RUNNING);
        setThreadInfo(threadInfo);
    }

    public void afterRun() {
        ThreadInfo threadInfo = getThreadInfo();
        threadInfo.setEnd(new Date());
        threadInfo.setStatus(ThreadInfo.FINISH);
        setThreadInfo(threadInfo);
    }

    public void failRun() {
        ThreadInfo threadInfo = getThreadInfo();
        threadInfo.setEnd(new Date());
        threadInfo.setStatus(ThreadInfo.ERROR);
        setThreadInfo(threadInfo);
    }

    public ThreadInfo getThreadInfo() {
        return (ThreadInfo) Globals.THREAD_INFO.get(jobName);
    }

    public void setThreadInfo(ThreadInfo threadInfo) {
        Globals.THREAD_INFO.put(jobName, threadInfo);
    }

    public void removeJob() {
        if (StringUtil.isNotEmpty(jobName)) {
            Globals.THREAD_INFO.remove(jobName);
        }
    }

    public String getJobName() {
        return jobName;
    }

}
