package com.holley.platform.common.dataobject.ex;

public class IdentityAuth {

    private String inMeterNo;    // 输入的分散因子,字符型,长度16, “0000”+表号
    private String outRand;      // 输出的随机数1,字符型,长度16
    private String outCipherText;// 输出的密文,字符型,长度16

    public String getInMeterNo() {
        return inMeterNo;
    }

    public void setInMeterNo(String inMeterNo) {
        this.inMeterNo = inMeterNo;
    }

    public String getOutRand() {
        return outRand;
    }

    public void setOutRand(String outRand) {
        this.outRand = outRand;
    }

    public String getOutCipherText() {
        return outCipherText;
    }

    public void setOutCipherText(String outCipherText) {
        this.outCipherText = outCipherText;
    }

}
