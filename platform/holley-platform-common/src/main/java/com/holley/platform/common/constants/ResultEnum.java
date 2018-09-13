package com.holley.platform.common.constants;

public enum ResultEnum {
                        SUCCESS("成功", 1), FAIL("失败", 2);

    private final int    value;
    private final String key;

    ResultEnum(String key, int value) {
        this.value = value;
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return new Integer(value).toString();
    }

    public Short getShortValue() {
        Integer intValue = value;
        return intValue.shortValue();
    }

    public String getKey() {
        return key;
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static ResultEnum getEnmuByValue(int value) {
        for (ResultEnum type : ResultEnum.values()) {
            if (value == type.getValue()) {
                return type;
            }
        }
        return null;
    }
}
