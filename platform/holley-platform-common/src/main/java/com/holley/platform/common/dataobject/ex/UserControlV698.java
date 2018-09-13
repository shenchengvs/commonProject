package com.holley.platform.common.dataobject.ex;

public class UserControlV698 extends UserControl {

    private int flag; // 整型, 0:公钥状态;1,私钥状态(需要特殊授权)

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
