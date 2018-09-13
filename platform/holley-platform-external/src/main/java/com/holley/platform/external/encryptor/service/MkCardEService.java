package com.holley.platform.external.encryptor.service;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface MkCardEService extends Library {

    MkCardEService INSTANCE = (MkCardEService) Native.loadLibrary((Platform.isWindows() ? "dll/gw/mkcard/MkCardFrm" : "c"), MkCardEService.class);

    /**
     * 数据包加密<br>
     * 返回数据包多了7个字节：5个字节的密文+CS+16
     * 
     * @param FrmBuff
     */
    public void MkCdFrm(byte[] FrmBuff);

}
