package com.holley.platform.serviceimpl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.platform.dao.sys.SysRuleMapper;
import com.holley.platform.model.sys.SysRule;
import com.holley.platform.model.sys.SysRuleExample;
import com.holley.platform.service.sys.RuleService;

public class RuleServiceImpl implements RuleService {

    @Autowired
    SysRuleMapper sysRuleMapper;

    @Override
    public SysRule selectRuleByPK(String id) {
        return sysRuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRule> selectRuleByExample(SysRuleExample emp) {
        return sysRuleMapper.selectByExample(emp);
    }

}
