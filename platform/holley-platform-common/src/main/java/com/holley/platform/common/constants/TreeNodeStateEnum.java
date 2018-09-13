package com.holley.platform.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * opened-展开<br>
 * closed-小时<br>
 * checked-选中<br>
 * 
 * @author zdd
 */

public enum TreeNodeStateEnum {

                               OPENED("opened", "展开"), CLOSED("closed", "合拢"), SELECTED("selected", "选中"), DISABLED("disabled", "不可用");

    private final String value;
    private final String title;

    TreeNodeStateEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static LogOperatorEnum getEnmuByValue(String value) {
        for (LogOperatorEnum log : LogOperatorEnum.values()) {
            if (StringUtils.equals(value, log.getValue())) {
                return log;
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
    public static LogOperatorEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (LogOperatorEnum log : LogOperatorEnum.values()) {
            if (StringUtils.equals(name, log.toString())) {
                return log;
            }
        }
        return null;
    }
}
