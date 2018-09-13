package com.holley.platform.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.holley.platform.model.sys.SysAccountRoleExample;
import com.holley.platform.model.sys.SysAccountRoleKey;

public interface SysAccountRoleMapper {

    int countByExample(SysAccountRoleExample example);

    int deleteByExample(SysAccountRoleExample example);

    int deleteByPrimaryKey(SysAccountRoleKey key);

    int insert(SysAccountRoleKey record);

    int insertSelective(SysAccountRoleKey record);

    List<SysAccountRoleKey> selectByExample(SysAccountRoleExample example);

    int updateByExampleSelective(@Param("record") SysAccountRoleKey record, @Param("example") SysAccountRoleExample example);

    int updateByExample(@Param("record") SysAccountRoleKey record, @Param("example") SysAccountRoleExample example);
}
