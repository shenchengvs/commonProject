package com.holley.platform.common.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * 符号枚举
 * 
 * @author zhouli
 */
public enum ExpressionEnum {

    AFTER_EQUALS("0", ">="), BEFORE("1", "<"), EQUALS("2", "="), BETWEEN("3", "in"), AFTER_NOTEQUALS("4", ">");

    private final String value;
    private final String key;

    ExpressionEnum(String value, String key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
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
    public static ExpressionEnum getEnmuByValue(String value) {
        for (ExpressionEnum expression : ExpressionEnum.values()) {
            if (StringUtils.equals(value, expression.getValue())) {
                return expression;
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
    public static ExpressionEnum getEnmuByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (ExpressionEnum expression : ExpressionEnum.values()) {
            if (StringUtils.equals(name, expression.toString())) {
                return expression;
            }
        }
        return null;
    }
}
