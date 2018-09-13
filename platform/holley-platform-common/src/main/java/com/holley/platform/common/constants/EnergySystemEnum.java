package com.holley.platform.common.constants;

import org.apache.commons.lang3.StringUtils;

public enum EnergySystemEnum {
                        EMCP(1, "能源管理系统"), PV(2, "分布式光伏数据监控运营云平台"), ES(3, "智慧式用电安全监测系统平台");

    private final int    value;
    private final String text;

    EnergySystemEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public Short getShortValue() {
        return ((Integer) value).shortValue();
    }

    public String getText() {
        return text;
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static EnergySystemEnum getEnmuByValue(int value) {
        for (EnergySystemEnum powerType : EnergySystemEnum.values()) {
            if (value == powerType.getValue()) {
                return powerType;
            }
        }
        return null;
    }

    /**
     * 通过传入的字符串匹配枚举,传入名字
     * 
     * @param name
     * @return
     */
    public static EnergySystemEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (EnergySystemEnum powerType : EnergySystemEnum.values()) {
            if (StringUtils.equals(name, powerType.toString())) {
                return powerType;
            }
        }
        return null;
    }
}
