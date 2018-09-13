package com.holley.platform.model.sys;

import java.util.Date;

import com.holley.platform.common.util.DateUtil;

public class SysWeblog extends SysWeblogKey {

    private Date    logtime;

    private Integer onlinetime;

    private Short   systemid;

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public Integer getOnlinetime() {
        return onlinetime;
    }

    public void setOnlinetime(Integer onlinetime) {
        this.onlinetime = onlinetime;
    }

    public Short getSystemid() {
        return systemid;
    }

    public void setSystemid(Short systemid) {
        this.systemid = systemid;
    }

    public String getLogtimeStr() {
        return DateUtil.DateToLongStr(logtime);
    }
}
