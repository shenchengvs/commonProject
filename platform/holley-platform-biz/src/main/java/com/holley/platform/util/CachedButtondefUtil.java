package com.holley.platform.util;

import java.util.ArrayList;
import java.util.List;

import com.holley.platform.common.util.StringUtil;
import com.holley.platform.dao.sys.SysButtondefMapper;
import com.holley.platform.model.sys.SysButtondef;

/**
 * sys_buttondef缓存和操作方法
 * 
 * @author road
 */
public class CachedButtondefUtil {

    private static SysButtondefMapper      sysButtondefMapper;
    private static short                   defaultSystemid;
    /**
     * 系统菜单缓存
     */
    public static final List<SysButtondef> BUTTONDEF = new ArrayList<SysButtondef>();

    public static void init() {
        // 初始化载入系统模块信息 double check
        if (CachedButtondefUtil.BUTTONDEF.size() == 0) {
            synchronized (CachedButtondefUtil.BUTTONDEF) {
                CachedButtondefUtil.BUTTONDEF.addAll(sysButtondefMapper.selectButtondefBySystemid(defaultSystemid));
            }
        }
    }

    /**
     * 取当前id对应的模块
     * 
     * @param moduleid
     * @return
     */
    public static SysButtondef getButtondefByPrimaryKey(Short buttonid) {
        for (SysButtondef record : BUTTONDEF) {
            if (buttonid.equals(record.getButtonid())) {
                return record;
            }
        }
        return null;
    }

    /**
     * 判断url是否在按钮列表中
     * 
     * @param url
     * @return
     */
    public static boolean constains(String url) {
        if (StringUtil.isEmpty(url)) return false;
        for (SysButtondef record : BUTTONDEF) {
            if (url.equals(record.getUrl())) {
                return true;
            }
        }
        return false;
    }

    public void setSysButtondefMapper(SysButtondefMapper sysButtondefMapper) {
        CachedButtondefUtil.sysButtondefMapper = sysButtondefMapper;
    }

    public void setDefaultSystemid(short defaultSystemid) {
        CachedButtondefUtil.defaultSystemid = defaultSystemid;
    }

}
