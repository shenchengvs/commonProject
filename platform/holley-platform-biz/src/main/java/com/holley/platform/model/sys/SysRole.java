package com.holley.platform.model.sys;

import java.util.Date;

import com.holley.platform.common.constants.RoleLevelEnum;
import com.holley.platform.common.util.DateUtil;

public class SysRole {

    private Short  roleid;

    private String disc;

    private Date   createtime;

    private String creator;

    private String remark;

    private Short  memberlevel;

    public Short getRoleid() {
        return roleid;
    }

    public void setRoleid(Short roleid) {
        this.roleid = roleid;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc == null ? null : disc.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Short getMemberlevel() {
        return memberlevel;
    }

    public void setMemberlevel(Short memberlevel) {
        this.memberlevel = memberlevel;
    }

    public String getMemberlevelname() {
        if (memberlevel == null) return "";
        String name = RoleLevelEnum.getText(memberlevel.shortValue());
        return name == null ? "" : name;
    }

    public String getCreatetimeStr() {
        return DateUtil.DateToLongStr(createtime);

    }

}
