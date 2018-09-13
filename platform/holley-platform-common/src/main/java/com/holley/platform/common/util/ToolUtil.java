package com.holley.platform.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ToolUtil {

    public static boolean isValida(String token) {
        return true;
    }

    /**
     * 不定参数校验空
     * 
     * @param args
     * @return
     */
    public static boolean isNull(String... args) {
        for (String arg : args) {
            if (StringUtils.isEmpty(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 不定参数校验数字
     * 
     * @param args
     * @return
     */
    public static boolean isNotNumber(String... args) {
        for (String arg : args) {
            if (!NumberUtils.isNumber(arg)) {
                return true;
            }
        }
        return false;
    }
}
