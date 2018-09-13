package com.holley.platform.external.util;

import com.holley.platform.external.encryptor.service.MkCardEService;

public class MkCardEUtil {

    /**
     * 连接密码机(服务器)函数
     * 
     * @return 0：成功，其他：失败
     */
    public static byte[] mkCdFrm(byte[] FrmBuff) {
        MkCardEService.INSTANCE.MkCdFrm(FrmBuff);
        return FrmBuff;
    }

}
