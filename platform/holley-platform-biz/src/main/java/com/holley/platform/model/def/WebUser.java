package com.holley.platform.model.def;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录人的信息<br>
 * 登录时CommonConstants.SESSION_KEY保存到session<br>
 * 它也用来做登录验证<br>
 * loginout时候可以去掉此session<br>
 * 
 * @author zhouli
 */
public class WebUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            account;              // 账号
    private String            name;                 // 用户名
    private int               type;                 // 用户类别

    private int               departmentid;         // 所属部门编码
    private String            departmentname;       // 所属部门名称
    private String            systemname;           // 企业自定义的系统名称

    private short             roleid;               // 所属角色
    private Permission        permission;           // 权限

    private String            ip;                   // 客户端ip
    private Date              logindate;            // 登录时间
    private String            logindatestr;
    private String            sessionid;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public String getLogindatestr() {
        return logindatestr;
    }

    public void setLogindatestr(String logindatestr) {
        this.logindatestr = logindatestr;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public short getRoleid() {
        return roleid;
    }

    public void setRoleid(short roleid) {
        this.roleid = roleid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

}
