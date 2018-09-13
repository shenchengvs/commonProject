package com.holley.platform.common.pool;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadInfo {

    public final static int     INIT    = 0;
    public final static int     RUNNING = 1;
    public final static int     FINISH  = 2;
    public final static int     ERROR   = 3;

    private String              moduleName;
    private int                 status;                                            // 0:初始化,1:运行中,2完毕
    private Date                start;
    private Date                end;

    private Map<String, Object> addin   = new ConcurrentHashMap<String, Object>(); // 额外信息
    private Object              addInfo;
    private Runnable            job;

    public Runnable getJob() {
        return job;
    }

    public void setJob(Runnable job) {
        this.job = job;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Map<String, Object> getAddin() {
        return addin;
    }

    public Object getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(Object addInfo) {
        this.addInfo = addInfo;
    }

}
