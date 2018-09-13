package com.holley.platform.common.constants;

/**
 * 档案状态 <br>
 */
public enum RecordStatusEnum {
                          NORMAL(1, "正常"), DEL(2, "删除");

    private final int    value;
    private final String text;

    RecordStatusEnum(int value, String text) {
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
        RecordStatusEnum task = getEnmuByValue(value);
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
    public static RecordStatusEnum getEnmuByValue(int value) {
        for (RecordStatusEnum record : RecordStatusEnum.values()) {
            if (value == record.getValue()) {
                return record;
            }
        }
        return null;
    }
}
