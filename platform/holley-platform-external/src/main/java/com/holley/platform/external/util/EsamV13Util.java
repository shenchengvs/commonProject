package com.holley.platform.external.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.holley.platform.external.encryptor.service.EsamV13Service;
import com.sun.jna.Memory;

public class EsamV13Util {

    private static Logger logger    = Logger.getLogger(EsamV13Util.class);

    private static String IP        = "183.129.224.70";
    private static int    PORT      = 3390;
    private static int    TIMEOUT   = 60;                                 // 单位：秒
    public static boolean CONNECTED = false;

    static {
        InputStream is = null;
        try {
            is = EsamV13Util.class.getClassLoader().getResourceAsStream("esam.properties");
            Properties prop = new Properties();
            prop.load(is);

            IP = prop.getProperty("v13_ip");
            PORT = Integer.valueOf(prop.getProperty("v13_port"));
            TIMEOUT = Integer.valueOf(prop.getProperty("v13_timeout"));

        } catch (Exception e) {
            logger.error("First create initParam error : " + e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 连接密码机(服务器)函数
     * 
     * @return 0：成功，其他：失败
     */
    public static int setIp() {
        return EsamV13Service.INSTANCE.SetIp(IP, PORT, TIMEOUT);
    }

    /**
     * 身份认证函数
     * 
     * @param inMeterNo:输入的分散因子,字符型,长度8字节, “0000”+表号
     * @param outRand:输出的随机数1,字符型,长度8字节
     * @param outCipherText:输出的密文,字符型,长度8字节
     * @return 0：成功，其他：失败
     */
    public static int identityAuthentication(String inMeterNo, Memory outRand, Memory outCipherText) {
        return EsamV13Service.INSTANCE.IdentityAuthentication(inMeterNo, outRand, outCipherText);
    }

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
    public static int userControl(String inRand, String inMeterNo, String intEsamNo, String intData, Memory outCipherText) {
        return EsamV13Service.INSTANCE.UserControl(inRand, inMeterNo, intEsamNo, intData, outCipherText);
    }
}
