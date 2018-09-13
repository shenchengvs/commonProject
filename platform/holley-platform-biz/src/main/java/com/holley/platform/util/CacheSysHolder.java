package com.holley.platform.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.holley.platform.common.constants.RuleStatusEnum;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.sys.SysRule;
import com.holley.platform.model.sys.SysRuleExample;
import com.holley.platform.service.sys.RuleService;

/**
 * 缓存服务
 * 
 * @author zdd
 */
public class CacheSysHolder {

    private static RuleService          ruleService;
    private static Short                defaultSystemid;

    private static Map<String, SysRule> ruleMap = new HashMap<String, SysRule>();

    public static void init() {
        initRule();
    }

    /**
     * 初始化规则表数据
     */
    private static void initRule() {
        SysRuleExample emp = new SysRuleExample();
        SysRuleExample.Criteria cr = emp.createCriteria();
        cr.andStatusEqualTo(RuleStatusEnum.ENABLED.getShortValue());
        if (defaultSystemid != null && defaultSystemid.intValue() > 0) {
            cr.andSystemidEqualTo(defaultSystemid);
        }
        List<SysRule> ruleList = ruleService.selectRuleByExample(emp);
        for (SysRule record : ruleList) {
            ruleMap.put(record.getRuleid(), record);
        }
    }

    /**
     * 清空规则表缓存数据
     */
    public static void clearRule() {
        if (ruleMap != null) ruleMap.clear();
    }

    /**
     * 重载规则表数据
     */
    public static void reloadRule() {
        clearRule();
        initRule();
    }

    public static String getRuleValueByRuleid(String ruleid) {
        if (StringUtil.isEmpty(ruleid)) return null;
        SysRule rule = ruleMap.get(ruleid);
        return rule == null ? null : rule.getValue();

    }

    public void setRuleService(RuleService ruleService) {
        CacheSysHolder.ruleService = ruleService;
    }

    public void setDefaultSystemid(Short defaultSystemid) {
        CacheSysHolder.defaultSystemid = defaultSystemid;
    }

}
