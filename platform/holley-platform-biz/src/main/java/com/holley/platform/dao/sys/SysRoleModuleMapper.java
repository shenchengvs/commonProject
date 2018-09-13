package com.holley.platform.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.platform.model.sys.SysRoleModuleExample;
import com.holley.platform.model.sys.SysRoleModuleKey;

public interface SysRoleModuleMapper {

    int countByExample(SysRoleModuleExample example);

    int deleteByExample(SysRoleModuleExample example);

    int deleteByPrimaryKey(SysRoleModuleKey key);

    int insert(SysRoleModuleKey record);

    int insertSelective(SysRoleModuleKey record);

    List<SysRoleModuleKey> selectByExample(SysRoleModuleExample example);

    int updateByExampleSelective(@Param("record") SysRoleModuleKey record, @Param("example") SysRoleModuleExample example);

    int updateByExample(@Param("record") SysRoleModuleKey record, @Param("example") SysRoleModuleExample example);

    int deleteRoleModuleByParam(Map<String, Object> param);

    int insertRoleModuleBatch(List<SysRoleModuleKey> list);

    List<SysRoleModuleKey> selectRoleModuleByParam(Map<String, Object> param);

}
