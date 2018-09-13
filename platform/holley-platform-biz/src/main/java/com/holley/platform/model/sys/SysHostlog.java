package com.holley.platform.model.sys;

import java.util.Date;

import com.holley.platform.common.constants.LogTypeEnum;
import com.holley.platform.common.util.DateUtil;

public class SysHostlog {

    private Integer hostlogid;

    private Date    logtime;

    private String  account;

    private String  ip;

    private String  content;

    private Short   type;

    private Short   systemid;

    public Integer getHostlogid() {
        return hostlogid;
    }

    public void setHostlogid(Integer hostlogid) {
        this.hostlogid = hostlogid;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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

    public String getLogtypeStr() {
        return LogTypeEnum.getName(type);
    }
}
