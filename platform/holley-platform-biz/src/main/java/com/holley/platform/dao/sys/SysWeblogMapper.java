package com.holley.platform.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.holley.platform.model.sys.SysWeblog;
import com.holley.platform.model.sys.SysWeblogExample;
import com.holley.platform.model.sys.SysWeblogKey;
import com.holley.platform.model.sys.vo.WebLogVo;

public interface SysWeblogMapper {

    int countByExample(SysWeblogExample example);

    int deleteByExample(SysWeblogExample example);

    int deleteByPrimaryKey(SysWeblogKey key);

    int insert(SysWeblog record);

    int insertSelective(SysWeblog record);

    List<SysWeblog> selectByExample(SysWeblogExample example);

    SysWeblog selectByPrimaryKey(SysWeblogKey key);

    int updateByExampleSelective(@Param("record") SysWeblog record, @Param("example") SysWeblogExample example);

    int updateByExample(@Param("record") SysWeblog record, @Param("example") SysWeblogExample example);

    int updateByPrimaryKeySelective(SysWeblog record);

    int updateByPrimaryKey(SysWeblog record);

    int mergeWeblog(SysWeblog record);

    List<WebLogVo> selectWebLogByPage(Map<String, Object> params);

    List<SysWeblog> selectWebLogDetailByPage(Map<String, Object> params);
}
