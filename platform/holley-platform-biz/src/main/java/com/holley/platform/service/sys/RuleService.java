package com.holley.platform.service.sys;

import java.util.List;

import com.holley.platform.model.sys.SysRule;
import com.holley.platform.model.sys.SysRuleExample;

public interface RuleService {

    SysRule selectRuleByPK(String id);

    List<SysRule> selectRuleByExample(SysRuleExample emp);

}
