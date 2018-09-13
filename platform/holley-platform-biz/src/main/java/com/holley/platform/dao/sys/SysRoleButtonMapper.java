package com.holley.platform.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.platform.model.sys.SysRoleButtonExample;
import com.holley.platform.model.sys.SysRoleButtonKey;

public interface SysRoleButtonMapper {

    int countByExample(SysRoleButtonExample example);

    int deleteByExample(SysRoleButtonExample example);

    int deleteByPrimaryKey(SysRoleButtonKey key);

    int insert(SysRoleButtonKey record);

    int insertSelective(SysRoleButtonKey record);

    List<SysRoleButtonKey> selectByExample(SysRoleButtonExample example);

    int updateByExampleSelective(@Param("record") SysRoleButtonKey record, @Param("example") SysRoleButtonExample example);

    int updateByExample(@Param("record") SysRoleButtonKey record, @Param("example") SysRoleButtonExample example);

    int deleteRoleButtonByParam(Map<String, Object> param);

    int insertRoleButtonBatch(List<SysRoleButtonKey> list);

    List<SysRoleButtonKey> selectRoleButtonByParam(Map<String, Object> param);
}
