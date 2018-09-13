package com.holley.platform.external.encryptor.service;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface EsamV13Service extends Library {

    EsamV13Service INSTANCE = (EsamV13Service) Native.loadLibrary((Platform.isWindows() ? "dll/gw/v13/GWV2RemoteD1000" : "c"), EsamV13Service.class);

    /**
     * 连接密码机(服务器)函数
     * 
     * @param inIp:密码机IP地址
     * @param inPort:密码机端口号
     * @param inTimeout：网络超时返回时间
     * @return 0：成功，其他：失败
     */
    public int SetIp(String inIp, int inPort, int inTimeout);

    /**
     * 身份认证函数
     * 
     * @param inMeterNo:输入的分散因子,字符型,长度8字节, “0000”+表号
     * @param outRand:输出的随机数1,字符型,长度8字节
     * @param outCipherText:输出的密文,字符型,长度8字节
     * @return 0：成功，其他：失败
     */
    public int IdentityAuthentication(String inMeterNo, Memory outRand, Memory outCipherText);

    /**
     * 控制命令加密函数
     * 
     * @param inRand:输入的随机数2,字符型,长度4字节
     * @param inMeterNo:输入的分散因子,字符型,长度8字节,“0000”+表号
     * @param inEsamNo:输入的ESAM 序列号,复位信息的后8字节,字符型,长度16
     * @param inData:表示跳闸、合闸、报警等控制命令明文,字符型
     * @param outCipherText:输出的密文,字符型
     * @return
     */
    public int UserControl(String inRand, String inMeterNo, String intEsamNo, String intData, Memory outCipherText);
}
