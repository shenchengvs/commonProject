package com.holley.platform.common.constants.ex;

public enum SecretKeyEnum {
                           PUBLIC_KEY(0, "公钥"), PRIVATE_KEY(1, "私钥");

    private final int    value;
    private final String text;

    SecretKeyEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public Short getShortValue() {
        Integer obj = value;
        return obj.shortValue();
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static SecretKeyEnum getEnmuByValue(int value) {
        for (SecretKeyEnum at : SecretKeyEnum.values()) {
            if (at.getValue() == value) {
                return at;
            }
        }
        return null;
    }

    /**
     * 通过传入的字符串匹配枚举获得描述，传入值
     * 
     * @param value
     * @return
     */
    public static String getText(int value) {
        SecretKeyEnum type = getEnmuByValue(value);
        return type == null ? "未知错误" : type.text;
    }

}
