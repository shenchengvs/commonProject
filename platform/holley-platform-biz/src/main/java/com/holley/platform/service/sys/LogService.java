package com.holley.platform.service.sys;

import java.util.List;
import java.util.Map;

import com.holley.platform.model.sys.SysHostLogVo;
import com.holley.platform.model.sys.SysHostlog;
import com.holley.platform.model.sys.SysWeblog;
import com.holley.platform.model.sys.SysWeblogExample;
import com.holley.platform.model.sys.vo.WebLogVo;

public interface LogService {

    // HostLog--------------------------
    int insertHostLog(SysHostlog record);

    List<SysHostLogVo> selectSysHostLog(Map<String, Object> params);

    // WebLog----------------------------
    int insertWebLog(SysWeblog record);

    int updateWebLogByPK(SysWeblog record);

    int updateWebLogByPKSelective(SysWeblog record);

    int countWebLogByExample(SysWeblogExample example);

    int mergeWeblog(SysWeblog record);

    public List<WebLogVo> selectWebLogByPage(Map<String, Object> params, String account);

    public List<SysWeblog> selectWebLogDetailByPage(Map<String, Object> params);

}
