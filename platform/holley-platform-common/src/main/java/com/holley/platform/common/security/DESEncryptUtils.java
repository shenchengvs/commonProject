package com.holley.platform.common.security;

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * DES加密密钥长度为8 56位再加一个校验位
 */
public class DESEncryptUtils {

    /**
     * 根据secretKey解密
     * 
     * @param secretString 密文
     * @param secretKey 密钥
     * @return
     */
    public static String decrypt(String secretString, String secretKey) {
        if (StringUtils.isEmpty(secretString) || StringUtils.isEmpty(secretKey)) {
            return null;
        }
        try {
            byte[] encryptedData = Base64.decode(secretString);
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            SecureRandom sr = new SecureRandom();
            byte[] rawKeyData = (new String(secretKey)).getBytes();

            DESKeySpec dks = new DESKeySpec(rawKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);

            byte[] decryptedData = cipher.doFinal(encryptedData);

            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据secretKey加密
     * 
     * @param source 明文
     * @param secretKey 密钥
     * @return
     */
    public static String encrypt(String source, String secretKey) {
        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(secretKey)) {
            return null;
        }
        try {
            byte[] decryptData = source.getBytes();
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            SecureRandom sr = new SecureRandom();
            byte[] rawKeyData = (new String(secretKey)).getBytes();

            DESKeySpec dks = new DESKeySpec(rawKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);

            byte[] encryptData = cipher.doFinal(decryptData);

            return Base64.encode(encryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
