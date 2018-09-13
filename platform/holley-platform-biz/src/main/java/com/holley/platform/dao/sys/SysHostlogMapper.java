package com.holley.platform.dao.sys;

import com.holley.platform.model.sys.SysHostLogVo;
import com.holley.platform.model.sys.SysHostlog;
import com.holley.platform.model.sys.SysHostlogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysHostlogMapper {
    int countByExample(SysHostlogExample example);

    int deleteByExample(SysHostlogExample example);

    int deleteByPrimaryKey(Integer hostlogid);

    int insert(SysHostlog record);

    int insertSelective(SysHostlog record);

    List<SysHostlog> selectByExample(SysHostlogExample example);
    
    List<SysHostLogVo> selectSysHostLogByPage(Map<String, Object> params);

    SysHostlog selectByPrimaryKey(Integer hostlogid);

    int updateByExampleSelective(@Param("record") SysHostlog record, @Param("example") SysHostlogExample example);

    int updateByExample(@Param("record") SysHostlog record, @Param("example") SysHostlogExample example);

    int updateByPrimaryKeySelective(SysHostlog record);

    int updateByPrimaryKey(SysHostlog record);
    
}