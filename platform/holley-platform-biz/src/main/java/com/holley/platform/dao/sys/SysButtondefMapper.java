package com.holley.platform.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.platform.model.sys.SysButtondef;
import com.holley.platform.model.sys.SysButtondefExample;

public interface SysButtondefMapper {

    int countByExample(SysButtondefExample example);

    int deleteByExample(SysButtondefExample example);

    int deleteByPrimaryKey(Short buttonid);

    int insert(SysButtondef record);

    int insertSelective(SysButtondef record);

    List<SysButtondef> selectByExample(SysButtondefExample example);

    SysButtondef selectByPrimaryKey(Short buttonid);

    int updateByExampleSelective(@Param("record") SysButtondef record, @Param("example") SysButtondefExample example);

    int updateByExample(@Param("record") SysButtondef record, @Param("example") SysButtondefExample example);

    int updateByPrimaryKeySelective(SysButtondef record);

    int updateByPrimaryKey(SysButtondef record);

    // List<SysButtondef> selectParentRoleButton(Map<String, Short> param);

    List<SysButtondef> selectAccountRoleButton(Map<String, Object> param);

    List<SysButtondef> selectButtondefByAccount(Map<String, Object> param);

    List<SysButtondef> selectButtondefBySystemid(Short systemid);

}
