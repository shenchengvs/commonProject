package com.holley.platform.common.dataobject.ex;

public class UserControl {

    private String inRand;        // 输入的随机数2,字符型,长度8
    private String inMeterNo;     // 输入的分散因子,字符型,长度16,“0000”+表号
    private String inEsamNo;      // 输入的ESAM 序列号,复位信息的后8 字节,字符型,长度16
    private String inData;        // 跳闸或合闸控制命令明文,字符型
    private String outCipherText; // 输出的密文,字符型

    public String getInRand() {
        return inRand;
    }

    public void setInRand(String inRand) {
        this.inRand = inRand;
    }

    public String getInMeterNo() {
        return inMeterNo;
    }

    public void setInMeterNo(String inMeterNo) {
        this.inMeterNo = inMeterNo;
    }

    public String getInEsamNo() {
        return inEsamNo;
    }

    public void setInEsamNo(String inEsamNo) {
        this.inEsamNo = inEsamNo;
    }

    public String getInData() {
        return inData;
    }

    public void setInData(String inData) {
        this.inData = inData;
    }

    public String getOutCipherText() {
        return outCipherText;
    }

    public void setOutCipherText(String outCipherText) {
        this.outCipherText = outCipherText;
    }

}
