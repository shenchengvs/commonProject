package com.holley.platform.common.constants;

/**
 * 采集方式 <br>
 */
public enum DcsPropEnum {
                         AUTO(0, "自动"), MANUAL(1, "手动");

    private final int    value;
    private final String text;

    DcsPropEnum(int value, String text) {
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
        DcsPropEnum task = getEnmuByValue(value);
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
    public static DcsPropEnum getEnmuByValue(int value) {
        for (DcsPropEnum record : DcsPropEnum.values()) {
            if (value == record.getValue()) {
                return record;
            }
        }
        return null;
    }
}
