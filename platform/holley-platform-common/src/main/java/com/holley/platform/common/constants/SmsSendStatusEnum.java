package com.holley.platform.common.constants;

import org.apache.commons.lang3.StringUtils;

public enum SmsSendStatusEnum {
    WAITING("0", "等待发送"), OK("1", "发送成功"), FAILED("2", "发送失败");

    private final String value;
    private final String name;

    SmsSendStatusEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public short getShortValue() {
        return Short.valueOf(value);
    }

    public String getName() {
        return name;
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static SmsSendStatusEnum getEnmuByValue(String value) {
        for (SmsSendStatusEnum status : SmsSendStatusEnum.values()) {
            if (StringUtils.equals(value, status.getValue())) {
                return status;
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
    public static SmsSendStatusEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (SmsSendStatusEnum status : SmsSendStatusEnum.values()) {
            if (StringUtils.equals(name, status.toString())) {
                return status;
            }
        }
        return null;
    }
}
