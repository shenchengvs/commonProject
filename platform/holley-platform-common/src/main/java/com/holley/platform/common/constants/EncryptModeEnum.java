package com.holley.platform.common.constants;

import java.util.ArrayList;
import java.util.List;

import com.holley.platform.common.dataobject.ComboxBean;

public enum EncryptModeEnum {
    ENCRYPT_NONE(0, "不加密"), ENCRYPT_SOFTDES(1, "DES软加密");

    private final int    value;
    private final String name;

    EncryptModeEnum(int value, String name) {
        this.value = value;
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static EncryptModeEnum getEnmuByValue(int value) {
        for (EncryptModeEnum type : EncryptModeEnum.values()) {
            if (value == type.getValue()) {
                return type;
            }
        }
        return null;
    }

    /**
     * 转换为list
     * 
     * @return
     */
    public static List<ComboxBean> toList() {
        List<ComboxBean> list = new ArrayList<ComboxBean>();
        list.add(new ComboxBean(EncryptModeEnum.ENCRYPT_NONE.getName(), EncryptModeEnum.ENCRYPT_NONE.getStringValue()));
        list.add(new ComboxBean(EncryptModeEnum.ENCRYPT_SOFTDES.getName(),
                                EncryptModeEnum.ENCRYPT_SOFTDES.getStringValue()));
        return list;
    }
}
