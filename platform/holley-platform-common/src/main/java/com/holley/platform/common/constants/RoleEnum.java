package com.holley.platform.common.constants;

/**
 * 系统自定义的角色<br>
 */
public enum RoleEnum {
                      ADMINISTRATOR(1, "系统管理员");

    private final int    value;
    private final String text;

    RoleEnum(int value, String text) {
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
        RoleEnum task = getEnmuByValue(value);
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
    public static RoleEnum getEnmuByValue(int value) {
        for (RoleEnum record : RoleEnum.values()) {
            if (value == record.getValue()) {
                return record;
            }
        }
        return null;
    }
}
