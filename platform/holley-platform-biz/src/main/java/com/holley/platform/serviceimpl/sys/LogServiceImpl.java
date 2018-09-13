package com.holley.platform.serviceimpl.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.holley.platform.dao.sys.SysHostlogMapper;
import com.holley.platform.dao.sys.SysWeblogMapper;
import com.holley.platform.model.sys.SysHostLogVo;
import com.holley.platform.model.sys.SysHostlog;
import com.holley.platform.model.sys.SysWeblog;
import com.holley.platform.model.sys.SysWeblogExample;
import com.holley.platform.model.sys.vo.WebLogVo;
import com.holley.platform.service.sys.LogService;
import com.holley.platform.util.HostLogUtils;

public class LogServiceImpl implements LogService {

    private static Log log = LogFactory.getLog(HostLogUtils.class);
    @Autowired
    SysHostlogMapper   sysHostlogMapper;
    @Autowired
    SysWeblogMapper    sysWeblogMapper;

    // HostLog------------------------------------
    @Override
    public int insertHostLog(SysHostlog record) {
        return sysHostlogMapper.insert(record);
    }

    @Override
    public List<SysHostLogVo> selectSysHostLog(Map<String, Object> params) {
        List<SysHostLogVo> sysHostLogList = sysHostlogMapper.selectSysHostLogByPage(params);
        return sysHostLogList;
    }

    // WebLog-------------------------------------
    @Override
    public int insertWebLog(SysWeblog record) {
        return sysWeblogMapper.insert(record);
    }

    @Override
    public int updateWebLogByPK(SysWeblog record) {
        return sysWeblogMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateWebLogByPKSelective(SysWeblog record) {
        return sysWeblogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int countWebLogByExample(SysWeblogExample example) {
        return sysWeblogMapper.countByExample(example);
    }

    @Override
    public int mergeWeblog(SysWeblog record) {
        return sysWeblogMapper.mergeWeblog(record);
    }

    @Override
    public List<WebLogVo> selectWebLogByPage(Map<String, Object> params, String account) {
        List<WebLogVo> webLogList = sysWeblogMapper.selectWebLogByPage(params);

        for (WebLogVo webLogVo : webLogList) {
            if (webLogVo.getAccount().equals(account)) {
                webLogVo.setEsname("");
            }
        }
        return webLogList;
    }

    @Override
    public List<SysWeblog> selectWebLogDetailByPage(Map<String, Object> params) {
        return sysWeblogMapper.selectWebLogDetailByPage(params);
    }

}
