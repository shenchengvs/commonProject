package com.holley.platform.common.constants;

public enum RoleLevelEnum {
                           PLATFORM(1, "平台管理员"), BUSINESS(2, "运营商管理员"), ENTERPRISE(3, "企业管理员"), OPERATOR(4, "企业操作员");

    private final int    value;
    private final String text;

    RoleLevelEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static String getText(int value) {
        RoleLevelEnum task = getEnmuByValue(value);
        return task == null ? null : task.getText();
    }

    public Short getShortValue() {
        Integer obj = value;
        return obj.shortValue();
    }

    /**
     * 通过传入的值匹配枚举
     * 
     * @param value
     * @return
     */
    public static RoleLevelEnum getEnmuByValue(int value) {
        for (RoleLevelEnum record : RoleLevelEnum.values()) {
            if (value == record.getValue()) {
                return record;
            }
        }
        return null;
    }

}
