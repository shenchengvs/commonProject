package com.holley.platform.model.def;

import com.holley.platform.common.constants.AccountTypeEnum;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.model.sys.SysAccount;

public class AccountInfo extends SysAccount {

    private Integer roleid;
    private String  rolename;
    private String  departmentname;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    // desc------------------------------
    public String getTypename() {
        if (getType() == null) return "";
        return AccountTypeEnum.getText(getType().intValue());
    }

    public String getCreatetimestr() {
        if (getCreatetime() == null) return "";
        return DateUtil.DateToLongStr(getCreatetime());
    }

}
