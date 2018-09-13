package com.holley.platform.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.platform.common.constants.LogOperatorEnum;
import com.holley.platform.common.constants.LogTypeEnum;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.sys.SysHostlog;
import com.holley.platform.model.sys.SysModuledef;
import com.holley.platform.model.sys.SysWeblog;
import com.holley.platform.model.sys.SysWeblogExample;
import com.holley.platform.service.sys.LogService;

public class HostLogUtils {

    private static Log        log = LogFactory.getLog(HostLogUtils.class);

    private static LogService logService;
    private static short      defaultSystemid;

    /**
     * 记录登录日志
     */
    public static void recordLoginlog(String account, String ip, String content) {
        recordSysHostlog(account, LogTypeEnum.RUN_LOGIN, LogOperatorEnum.ACCESS, ip, null, content);
    }

    /**
     * 记录权限的修改日志
     */
    public static void recordAccountlog(String account, LogOperatorEnum operatetype, String ip, String moduleid, String content) {
        recordSysHostlog(account, LogTypeEnum.RUN_ACCOUNT, operatetype, ip, moduleid, content);
    }

    /**
     * 记录档案的修改日志
     */
    public static void recordDocumentlog(String account, LogOperatorEnum operatetype, String ip, String moduleid, String content) {
        recordSysHostlog(account, LogTypeEnum.RUN_RECORD, operatetype, ip, moduleid, content);
    }

    /**
     * 记录拒绝的访问日志，用于登录前/后
     */
    public static void recordRefuselog(String account, String ip, String content) {
        recordSysHostlog(account, LogTypeEnum.ERROR_REFUSE, LogOperatorEnum.ACCESS, ip, null, content);
    }

    /**
     * 记录计算服务日志
     */
    public static void recordCaclLog(String module, String content) {
        recordSysHostlog("计算服务", LogTypeEnum.ERROR_REFUSE, LogOperatorEnum.RUN, null, null, module + ":" + content);
    }

    public static void recordSysHostlog(SysHostlog record) {
        log.info(record.getHostlogid());
        try {
            if (record.getContent().length() > 1000) record.setContent(record.getContent().substring(0, 1000) + "...");
            logService.insertHostLog(record);
            log.info(record.getHostlogid());
        } catch (Exception e) {
            log.error("HostLog save error:", e);
        }
    }

    private static void recordSysHostlog(String account, LogTypeEnum logtype, LogOperatorEnum operatetype, String ip, String moduleid, String content) {
        if (ip == null || ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        String moduleDisc = "";
        if (StringUtil.isNotEmpty(moduleid)) {
            SysModuledef module = CachedModuledefUtil.getModuledefByPrimaryKey(moduleid);
            moduleDisc = module == null ? "" : module.getDisc();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(operatetype.getTitle()).append("; ");
        if (StringUtil.isNotEmpty(moduleDisc)) {
            sb.append(moduleDisc).append("; ");
        }
        sb.append(content);

        SysHostlog record = new SysHostlog();
        record.setAccount(account);
        record.setContent(content);
        record.setIp(ip);
        record.setLogtime(new Date());
        record.setSystemid(defaultSystemid);// 光伏系统
        record.setType(logtype.getValue());
        recordSysHostlog(record);
    }

    /**
     * 记录账户登录信息
     * 
     * @param record
     */
    public static void recordInsertSysWebLog(SysWeblog record) {
        try {
            logService.insertWebLog(record);
        } catch (Exception e) {
            log.error("WebLoginLog save error", e);
        }
    }

    public static void recordMergeSysWebLog(SysWeblog record) {
        try {
            logService.mergeWeblog(record);
        } catch (Exception e) {
            log.error("WebLoginLog save error", e);
        }
    }

    public static int recordCount(SysWeblogExample example) {
        try {
            return logService.countWebLogByExample(example);
        } catch (Exception e) {
            log.error("WebLoginLog count error", e);
        }
        return 0;
    }

    /**
     * 记录账户退出信息
     * 
     * @param record
     */
    public static void recordUpdateSysWebLog(SysWeblog record) {
        try {
            logService.updateWebLogByPKSelective(record);
        } catch (Exception e) {
            log.error("WebLoginLog save error", e);
        }
    }

    public void setLogService(LogService logService) {
        HostLogUtils.logService = logService;
    }

    public static void setDefaultSystemid(short defaultSystemid) {
        HostLogUtils.defaultSystemid = defaultSystemid;
    }

}
