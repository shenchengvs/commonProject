package com.holley.platform.external.util;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

import com.holley.platform.common.dataobject.ex.UserControl;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.MsgUtil;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import net.sf.json.JSONObject;

public class TestV13Dll {

    public interface EsamV13Test extends Library {

        // msvcrt为dll名称,msvcrt目录的位置为:C:\Windows\System32下面,

        // EsamV13Util load = new EsamV13Util();
        EsamV13Test INSTANCE = (EsamV13Test) Native.loadLibrary((Platform.isWindows() ? "dll/gw/v13/GWV2RemoteD1000" : "c"), EsamV13Test.class);

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
        public int IdentityAuthentication(String inMeterNo, byte[] outRand, byte[] outCipherText);

        /**
         * 控制命令加密函数
         * 
         * @param inRand:输入的随机数2,字符型,长度4字节
         * @param inMeterNo:输入的分散因子,字符型,长度8字节,“0000”+表号
         * @param intEsamNo:输入的ESAM 序列号,复位信息的后8字节,字符型,长度16
         * @param intData:表示跳闸、合闸、报警等控制命令明文,字符型
         * @param outCipherText:输出的密文,字符型
         * @return
         */
        public int UserControl(String inRand, String inMeterNo, String intEsamNo, String intData, Memory outCipherText);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 本地动态库测试//
        // testDll();

        // http请求接口测试//
        testHttpReq();

        // 定时测试//
        // testTimer();
    }

    /**
     * 动态库函数测试
     */
    private static void testDll() {
        // 连接设备测试
        // int val = EsamV13Test.INSTANCE.SetIp("183.129.224.70", 3390, 60);
        int val = EsamV13Test.INSTANCE.SetIp("172.16.23.33", 9000, 60);
        System.out.println("val=" + val);

        // 身份认证测试
        String inMeterNo = "0000062060200459";

        for (int index = 0; index < 1; index++) {
            // Memory outRand = new Memory(128);
            // Memory outCipherText = new Memory(1);
            // byte[] outRand = new byte[16];
            // byte[] outCipherText = new byte[16];
            // System.out.println("index=" + index);

            // int val2 = EsamV13Test.INSTANCE.IdentityAuthentication(inMeterNo, outRand, outCipherText);
            // System.out.println("val2=" + val2 + " inMeterNo=" + inMeterNo + ";outRand=" + outRand.getString(0) +
            // ";outCipherText=" + outCipherText.getString(0));
            // System.out.println("val2=" + val2 + " inMeterNo=" + inMeterNo + ";outRand=" + new String(outRand) +
            // ";outCipherText=" + new String(outCipherText));
            // 远程控制加密测试
            // byte[] ct = new byte[20];

            Memory ct = new Memory(128);
            int val3 = EsamV13Test.INSTANCE.UserControl("729ed07d", "0000333333333333", "010100000b00bf10", "1A00570116021118", ct);

            // int val3 = EsamV13Test.INSTANCE.UserControl("ED04B2EC", "0000333333333333", "010100000b00bf10",
            // "1A00181029000000", ct);
            System.out.println(" val3=" + val3 + " outCipherText=" + MsgUtil.getByteToHexString(ct.getByteArray(0, 20), 20, ""));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * http请求接口测试
     */
    private static void testHttpReq() {
        // 身份认证测试
        // String url = "http://172.16.15.51:8280/external/encryptor/esamV13_identityAuthentication.action";
        // IdentityAuth record = new IdentityAuth();
        // record.setInMeterNo("0000062060200459");

        // 远程控制加密测试
        String url = "http://47.97.17.22:8080/external/encryptor/esamV13_userControl.action";

        UserControl record = new UserControl();
        record.setInRand("729ed07d");
        record.setInMeterNo("0000333333333333");
        record.setInEsamNo("010100000b00bf10");
        record.setInData("1A00570116021118");

        String json = JsonUtil.object2json(record);
        try {
            for (int i = 0; i < 1; i++) {
                System.out.println("i=" + i);
                JSONObject jsonObject = HttpUtil.httpUrlConnection(url, json, null);
                if (jsonObject != null) {
                    System.out.println(jsonObject.toString());
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            System.out.println("请求失败");
            e.printStackTrace();

        }
    }

    /**
     * 定时测试
     */
    private static void testTimer() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                int i = 0;
                while (true) {
                    i++;
                    try {
                        // 本地动态库测试//
                        // testDll();
                        // http请求接口测试//
                        testHttpReq();

                        TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }
}
