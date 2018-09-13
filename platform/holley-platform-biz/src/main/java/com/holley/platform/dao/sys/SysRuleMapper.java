package com.holley.platform.dao.sys;

import com.holley.platform.model.sys.SysRule;
import com.holley.platform.model.sys.SysRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRuleMapper {
    int countByExample(SysRuleExample example);

    int deleteByExample(SysRuleExample example);

    int deleteByPrimaryKey(String ruleid);

    int insert(SysRule record);

    int insertSelective(SysRule record);

    List<SysRule> selectByExample(SysRuleExample example);

    SysRule selectByPrimaryKey(String ruleid);

    int updateByExampleSelective(@Param("record") SysRule record, @Param("example") SysRuleExample example);

    int updateByExample(@Param("record") SysRule record, @Param("example") SysRuleExample example);

    int updateByPrimaryKeySelective(SysRule record);

    int updateByPrimaryKey(SysRule record);
}