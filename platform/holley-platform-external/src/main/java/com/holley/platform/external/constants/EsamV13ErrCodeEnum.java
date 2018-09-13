package com.holley.platform.external.constants;

public enum EsamV13ErrCodeEnum {
                                ERR_0(0, "成功"), // <br>
                                ERR_2001(2001, "错误的IP地址"), // <br>
                                ERR_2002(2002, "错误的加密机端口"), // <br>
                                ERR_2003(2003, "错误的超时时间"), // <br>
                                ERR_2004(2004, "未调用SetIp函数"), // <br>
                                ERR_2005(2005, "错误的输入数据"), // <br>
                                ERR_2006(2006, "错误的输入数据"), // <br>
                                ERR_2007(2007, "加密机连接错误"), // <br>
                                ERR_2010(2010, "SOCKET错误"), // <br>
                                ERR_2011(2011, "错误的输入数据"), // <br>
                                ERR_2012(2012, "加密机返回错误"), // <br>
                                ERR_2013(2013, "加密机通讯错误"), // <br>
                                ERR_2015(2015, "加密机返回数据错误"), // <br>
                                ERR_2016(2016, "MAC比对错误"), // <br>
                                ERR_1405(1405, "SOCKET通信错误"), // <br>
                                ERR_1406(1406, "SCKET 接收错误"), // <br>
                                ERR_1407(1407, "SOCKET通讯超时"), // <br>
                                ERR_1408(1408, "SOCKET通信错误"); // <br>;

    private final int    value;
    private final String text;

    EsamV13ErrCodeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public Short getShortValue() {
        Integer obj = value;
        return obj.shortValue();
    }

    /**
     * 通过传入的字符串匹配枚举，传入值
     * 
     * @param value
     * @return
     */
    public static EsamV13ErrCodeEnum getEnmuByValue(int value) {
        for (EsamV13ErrCodeEnum at : EsamV13ErrCodeEnum.values()) {
            if (at.getValue() == value) {
                return at;
            }
        }
        return null;
    }

    /**
     * 通过传入的字符串匹配枚举获得描述，传入值
     * 
     * @param value
     * @return
     */
    public static String getText(int value) {
        EsamV13ErrCodeEnum type = getEnmuByValue(value);
        return type == null ? "未知错误" : type.text;
    }

}
