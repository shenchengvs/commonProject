package com.holley.platform.model.sys.vo;

import com.holley.platform.common.constants.AccountTypeEnum;
import com.holley.platform.model.sys.SysWeblog;

public class WebLogVo extends SysWeblog {

    private String  esname;     // 所属企业
    private Integer count;      // 登录次数
    private Integer accountType;// 账号类型
    private String  name;       // 用户名称

    public String getEsname() {
        return esname;
    }

    public void setEsname(String esname) {
        this.esname = esname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountTypeStr() {
        if (accountType == null) return "";
        String type = AccountTypeEnum.getText(accountType);
        return type == null ? "" : type;

    }

}
