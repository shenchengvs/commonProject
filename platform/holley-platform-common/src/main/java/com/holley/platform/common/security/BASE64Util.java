package com.holley.platform.common.security;

import java.io.IOException;

public class BASE64Util {

    public static String encodeBase64(String src) {
        if (src == null) {
            return null;
        }

        sun.misc.BASE64Encoder encode = new sun.misc.BASE64Encoder();
        return encode.encode(src.getBytes());
    }

    public static String decodeBASE64(String s) {
        if (s == null) {
            return null;
        }
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String args[]) throws IOException {

        sun.misc.BASE64Encoder encode = new sun.misc.BASE64Encoder();

        String base64 = encode.encode("五笔字型电子计算机".getBytes());

        System.out.println(base64);

        sun.misc.BASE64Decoder decode = new sun.misc.BASE64Decoder();

        byte[] b = decode.decodeBuffer(base64);

        System.out.println(new String(b));

    }
}
