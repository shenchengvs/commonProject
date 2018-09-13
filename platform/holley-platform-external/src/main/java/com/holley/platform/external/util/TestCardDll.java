package com.holley.platform.external.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.MsgUtil;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

import net.sf.json.JSONObject;

public class TestCardDll {

    public interface MkCardTest extends Library {

        // msvcrt为dll名称,msvcrt目录的位置为:C:\Windows\System32下面,

        // EsamV13Util load = new EsamV13Util();
        MkCardTest INSTANCE = (MkCardTest) Native.loadLibrary((Platform.isWindows() ? "dll/gw/mkcard/MkCardFrm" : "c"), MkCardTest.class);

        /**
         * 数据包加密
         * 
         * @param FrmBuff
         */
        public void MkCdFrm(byte[] FrmBuff);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "680241000000000500050000010000500099990000000108000000000005130731163700001230800001";
        // String str = "6801310000000012000010009999000050010800180126000000000001000001";
        // String str = "680520051801260000000000000000000000000068";
        // str = MsgUtil.strReverse(str);
        byte[] byteArray = MsgUtil.StringToHexF(str);
        byte[] b = new byte[byteArray.length + 7];
        for (int i = 0; i < byteArray.length; i++) {
            b[i] = byteArray[i];
        }
        MkCardTest.INSTANCE.MkCdFrm(b);
        System.out.println(MsgUtil.getByteToHexString(b, b.length, ""));

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
        String url = "http://172.16.15.51:8280/external/encryptor/mkCard_mkCdFrm.action";
        String str = "680241000000000500050000010000500099990000000108000000000005130731163700001230800001";

        Map<String, String> map = new HashMap<String, String>();
        map.put("value", str);

        String json = JsonUtil.map2json(map);
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

}
