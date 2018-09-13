package com.holley.platform.common.dataobject.ex;

public class IdentityAuthV698 extends IdentityAuth {

    private int    flag;    // 整型, 0:公钥状态;1,私钥状态
    private String factory; // 厂家名称，可以为空，主要用于兼容2009 版电表身份认证

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

}
