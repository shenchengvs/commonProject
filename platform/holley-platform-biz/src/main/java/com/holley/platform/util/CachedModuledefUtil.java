package com.holley.platform.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.holley.platform.common.util.StringUtil;
import com.holley.platform.dao.sys.SysModuledefMapper;
import com.holley.platform.model.sys.SysModuledef;
import com.holley.platform.model.sys.SysModuledefExample;

/**
 * sys_module缓存和操作方法
 * 
 * @author road
 */
public class CachedModuledefUtil {

    private static SysModuledefMapper      sysModuledefMapper;
    private static short                   defaultSystemid;
    /**
     * 系统菜单缓存
     */
    public static final List<SysModuledef> MODULEDEF = new ArrayList<SysModuledef>();

    public static void init() {
        // 初始化载入系统模块信息 double check
        if (CachedModuledefUtil.MODULEDEF.size() == 0) {
            synchronized (CachedModuledefUtil.MODULEDEF) {
                SysModuledefExample example = new SysModuledefExample();
                example.createCriteria().andSystemidEqualTo(defaultSystemid);

                example.setOrderByClause("ModuleID");
                CachedModuledefUtil.MODULEDEF.addAll(sysModuledefMapper.selectByExample(example));
            }
        }
    }

    /**
     * 取当前id对应的模块
     * 
     * @param moduleid
     * @return
     */
    public static SysModuledef getModuledefByPrimaryKey(String moduleid) {
        for (SysModuledef sysModuledef : MODULEDEF) {
            if (moduleid.equals(sysModuledef.getModuleid())) {
                return sysModuledef;
            }
        }
        return null;
    }

    public static List<SysModuledef> getTopModuledefList() {
        List<SysModuledef> result = new LinkedList<SysModuledef>();
        // moduleid 与 parentmoduleid 不等
        for (SysModuledef sysModuledef : MODULEDEF) {
            if (sysModuledef.getModuleid().equalsIgnoreCase(sysModuledef.getParentmoduleid())) {
                result.add(sysModuledef);
            }
        }

        return result;
    }

    /**
     * 通过父模块ID取子模块列表,但不包括moduleid = parendmoduleid的数据
     * 
     * @param parentmoduleid
     * @return
     */
    public static List<SysModuledef> getChildModuleList(String parentmoduleid) {
        List<SysModuledef> result = new LinkedList<SysModuledef>();
        for (SysModuledef sysModuledef : MODULEDEF) {
            if (parentmoduleid.equals(sysModuledef.getParentmoduleid()) && !sysModuledef.getModuleid().equals(sysModuledef.getParentmoduleid())) {
                result.add(sysModuledef);
            }
        }
        return result;
    }

    /**
     * 递归调用父对象模块
     * 
     * @param parentModuleid
     * @return
     */
    public static Map<String, SysModuledef> getParentModule(String parentModuleid) {
        Map<String, SysModuledef> moduleMap = new HashMap<String, SysModuledef>();
        SysModuledef sysModuledef = getModuledefByPrimaryKey(parentModuleid);
        if (sysModuledef != null) {
            moduleMap.put(sysModuledef.getModuleid(), sysModuledef);
            if (!sysModuledef.getModuleid().equals(sysModuledef.getParentmoduleid())) {
                moduleMap.putAll(getParentModule(sysModuledef.getParentmoduleid()));
            }
        }
        return moduleMap;
    }

    /**
     * 判断url是否在模块列表中
     * 
     * @param url
     * @return
     */
    public static boolean constains(String url) {
        if (StringUtil.isEmpty(url)) return false;
        for (SysModuledef sysModuledef : MODULEDEF) {
            if (url.equals(sysModuledef.getUrl())) {
                return true;
            }
        }
        return false;
    }

    public void setSysModuledefMapper(SysModuledefMapper sysModuledefMapper) {
        CachedModuledefUtil.sysModuledefMapper = sysModuledefMapper;
    }

    public void setDefaultSystemid(short defaultSystemid) {
        CachedModuledefUtil.defaultSystemid = defaultSystemid;
    }

}
