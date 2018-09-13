package com.holley.platform.serviceimpl.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.constants.LogOperatorEnum;
import com.holley.platform.common.constants.RoleEnum;
import com.holley.platform.common.constants.TreeNodeStateEnum;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.dao.sys.SysAccountMapper;
import com.holley.platform.dao.sys.SysAccountRoleMapper;
import com.holley.platform.dao.sys.SysButtondefMapper;
import com.holley.platform.dao.sys.SysModuledefMapper;
import com.holley.platform.dao.sys.SysRoleButtonMapper;
import com.holley.platform.dao.sys.SysRoleMapper;
import com.holley.platform.dao.sys.SysRoleModuleMapper;
import com.holley.platform.model.def.AccountInfo;
import com.holley.platform.model.def.LogInfo;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysAccount;
import com.holley.platform.model.sys.SysAccountExample;
import com.holley.platform.model.sys.SysAccountRoleExample;
import com.holley.platform.model.sys.SysAccountRoleKey;
import com.holley.platform.model.sys.SysButtondef;
import com.holley.platform.model.sys.SysModuledef;
import com.holley.platform.model.sys.SysRole;
import com.holley.platform.model.sys.SysRoleButtonKey;
import com.holley.platform.model.sys.SysRoleExample;
import com.holley.platform.model.sys.SysRoleModuleKey;
import com.holley.platform.model.sys.vo.ModuleBtnResult;
import com.holley.platform.model.sys.vo.PermissinTreeNode;
import com.holley.platform.service.sys.AccountRoleService;
import com.holley.platform.util.CachedButtondefUtil;
import com.holley.platform.util.CachedModuledefUtil;
import com.holley.platform.util.HostLogUtils;

import net.sf.json.JSONObject;

public class AccountRoleServiceImpl implements AccountRoleService {

    @Autowired
    SysAccountMapper     sysAccountMapper;
    @Autowired
    SysAccountRoleMapper sysAccountRoleMapper;
    @Autowired
    SysRoleMapper        sysRoleMapper;
    @Autowired
    SysModuledefMapper   sysModuledefMapper;
    @Autowired
    SysButtondefMapper   sysButtondefMapper;
    @Autowired
    SysRoleModuleMapper  sysRoleModuleMapper;
    @Autowired
    SysRoleButtonMapper  sysRoleButtonMapper;

    // sysAccount---------------------------------------
    @Override
    public int insertAccountInfo(AccountInfo record, LogInfo logInfo) {
        int row = 0;
        // 插入sys_account
        SysAccount sysAccount = getSysAccountFrom(record);
        row += sysAccountMapper.insert(sysAccount);

        // 插入sys_accountrole
        SysAccountRoleKey accountRole = getSysAccountRoleFrom(record);
        row += sysAccountRoleMapper.insert(accountRole);

        // 插入日志sys_hostlog
        if (row > 0 && logInfo != null) {
            String content = JSONObject.fromObject(sysAccount, JsonUtil.returnJosnConfig(false, Date.class)).toString();
            content = "表名:SYS_ACCOUNT;" + content;
            HostLogUtils.recordDocumentlog(logInfo.getAccount(), LogOperatorEnum.ADD, logInfo.getIp(), logInfo.getModuleid(), content);

            content = JSONObject.fromObject(accountRole, JsonUtil.returnJosnConfig(false, Date.class)).toString();
            content = "表名:SYS_ACCOUNTROLE;" + content;
            HostLogUtils.recordDocumentlog(logInfo.getAccount(), LogOperatorEnum.ADD, logInfo.getIp(), logInfo.getModuleid(), content);
        }

        return row;
    }

    @Override
    public int updateAccountInfo(AccountInfo record, LogInfo logInfo) {
        int row1 = 0;
        int row2 = 0;
        // 更新sys_account
        SysAccount sysAccount = getSysAccountFrom(record);
        row1 += sysAccountMapper.updateByPrimaryKeySelective(sysAccount);

        // 更新sys_accountrole
        SysAccountRoleKey accountRole = null;
        if (record.getRoleid() != null) {
            accountRole = getSysAccountRoleFrom(record);

            SysAccountRoleExample emp = new SysAccountRoleExample();
            SysAccountRoleExample.Criteria cr = emp.createCriteria();
            cr.andAccountEqualTo(record.getAccount());
            List<SysAccountRoleKey> accountRoleList = sysAccountRoleMapper.selectByExample(emp);
            if (accountRoleList != null && accountRoleList.size() > 0) {
                SysAccountRoleKey oldAccountRole = accountRoleList.get(0);
                if (accountRole.getRoleid().intValue() != oldAccountRole.getRoleid().intValue()) {
                    row2 += sysAccountRoleMapper.updateByExampleSelective(accountRole, emp);
                }
            } else {
                row2 += sysAccountRoleMapper.insert(accountRole);
            }
        }

        // 插入日志sys_hostlog
        String content = "";
        if (row1 > 0 && logInfo != null) {
            content = JSONObject.fromObject(sysAccount, JsonUtil.returnJosnConfig(false, Date.class)).toString();
            content = "表名:SYS_ACCOUNT;" + content;
            HostLogUtils.recordDocumentlog(logInfo.getAccount(), LogOperatorEnum.MODIFY, logInfo.getIp(), logInfo.getModuleid(), content);

        }

        if (row2 > 0 && logInfo != null) {
            content = JSONObject.fromObject(accountRole, JsonUtil.returnJosnConfig(false, Date.class)).toString();
            content = "表名:SYS_ACCOUNTROLE;" + content;
            HostLogUtils.recordDocumentlog(logInfo.getAccount(), LogOperatorEnum.MODIFY, logInfo.getIp(), logInfo.getModuleid(), content);
        }
        return row1 + row2;
    }

    private SysAccount getSysAccountFrom(AccountInfo record) {
        SysAccount sysAccount = new SysAccount();
        sysAccount.setAccount(record.getAccount());
        sysAccount.setAddress(record.getAddress());
        sysAccount.setCreateaccount(record.getCreateaccount());
        sysAccount.setCreatetime(record.getCreatetime());
        sysAccount.setDepartmentid(record.getDepartmentid());
        sysAccount.setEmail(record.getEmail());
        sysAccount.setName(record.getName());
        sysAccount.setPassword(record.getPassword());
        sysAccount.setPosition(record.getPosition());
        sysAccount.setStation(record.getStation());
        sysAccount.setSex(record.getSex());
        sysAccount.setTelephone(record.getTelephone());
        sysAccount.setType(record.getType());
        return sysAccount;
    }

    private SysAccountRoleKey getSysAccountRoleFrom(AccountInfo record) {
        SysAccountRoleKey accountRole = new SysAccountRoleKey();
        accountRole.setAccount(record.getAccount());
        accountRole.setRoleid(record.getRoleid().shortValue());
        return accountRole;
    }

    @Override
    public int updateAccountByPKSelective(SysAccount record) {
        return sysAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SysAccount selectAccountByPK(String account) {
        return sysAccountMapper.selectByPrimaryKey(account);
    }

    @Override
    public AccountInfo selectAccountInfoByPK(String account) {
        return sysAccountMapper.selectAccountInfoByPK(account);
    }

    @Override
    public List<SysAccount> selectAccountByExample(SysAccountExample example) {
        return sysAccountMapper.selectByExample(example);
    }

    @Override
    public int deleteAccountByPK(String account, LogInfo logInfo) {
        int row1 = 0;
        int row2 = 0;
        row1 += sysAccountMapper.deleteByPrimaryKey(account);

        SysAccountRoleExample emp = new SysAccountRoleExample();
        SysAccountRoleExample.Criteria cr = emp.createCriteria();
        cr.andAccountEqualTo(account);
        row2 += sysAccountRoleMapper.deleteByExample(emp);

        String content = "";
        if (row1 > 0 && logInfo != null) {
            content = "表名:SYS_ACCOUNT;{account=" + account + "}";
            HostLogUtils.recordDocumentlog(logInfo.getAccount(), LogOperatorEnum.DELETE, logInfo.getIp(), logInfo.getModuleid(), content);

        }

        if (row2 > 0 && logInfo != null) {
            content = "表名:SYS_ACCOUNTROLE;{account=" + account + "}";
            HostLogUtils.recordDocumentlog(logInfo.getAccount(), LogOperatorEnum.DELETE, logInfo.getIp(), logInfo.getModuleid(), content);
        }
        return row1 + row2;
    }

    @Override
    public List<AccountInfo> selectAccountByPage(Map<String, Object> param) {
        return sysAccountMapper.selectAccountByPage(param);
    }

    // sysRole------------------------------------------
    @Override
    public List<SysRole> selectRoleByExample(SysRoleExample example) {
        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public SysRole selectRoleByPK(Short roleid) {
        return sysRoleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public int updateRoleByPKSelective(SysRole record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteRoleByPK(Short roleid) {
        return sysRoleMapper.deleteByPrimaryKey(roleid);
    }

    @Override
    public int insertRole(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public List<PermissinTreeNode> loadPermission(Short roleid, WebUser user, Short systemid) {
        // 查找当前登录用户所属角色的模块权限
        List<SysModuledef> moduleList = new LinkedList<SysModuledef>();
        int webUserRoleid = user.getRoleid();
        if (webUserRoleid == RoleEnum.ADMINISTRATOR.getValue()) {
            // 以平台管理员角色登录时
            moduleList = CachedModuledefUtil.MODULEDEF;
        } else {
            // 不是平台管理员登录时
            // SysRoleModuleExample rmemp = new SysRoleModuleExample();
            // SysRoleModuleExample.Criteria rmcr = rmemp.createCriteria();
            // rmcr.andRoleidEqualTo(user.getRoleid());
            // List<SysRoleModuleKey> roleModules = sysRoleModuleMapper.selectByExample(rmemp);
            // for (SysRoleModuleKey roleModule : roleModules) {
            // SysModuledef sysModuledef = CachedModuledefUtil.getModuledefByPrimaryKey(roleModule.getModuleid());
            // if (sysModuledef != null) {
            // moduleList.add(sysModuledef);
            // moduleList.addAll(CachedModuledefUtil.getParentModule(sysModuledef.getParentmoduleid()).values());
            // }
            // }
            moduleList = user.getPermission().getModules();
        }

        // 查找当前用户所属角色的按钮权限
        List<SysButtondef> btnList = new LinkedList<SysButtondef>();
        if (webUserRoleid == RoleEnum.ADMINISTRATOR.getValue()) {
            // 以平台管理员角色登录时
            btnList = CachedButtondefUtil.BUTTONDEF;
        } else {
            // 不是平台管理员登录时
            // param.put("roleId", (short) webUserRoleid);
            // btnList = sysButtondefMapper.selectParentRoleButton(param);
            btnList = user.getPermission().getButtons();
        }
        Map<String, List<SysButtondef>> buttondefMap = new HashMap<String, List<SysButtondef>>();
        List<SysButtondef> tempButtons = null;
        for (SysButtondef sysButtondef : btnList) {
            if (buttondefMap.containsKey(sysButtondef.getModuleid())) {
                buttondefMap.get(sysButtondef.getModuleid()).add(sysButtondef);
            } else {
                tempButtons = new LinkedList<SysButtondef>();
                tempButtons.add(sysButtondef);
                buttondefMap.put(sysButtondef.getModuleid(), tempButtons);
            }
        }

        // 查找所选编辑角色的模块权限
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleid", roleid);
        param.put("systemid", systemid);
        List<SysRoleModuleKey> roleModules = sysRoleModuleMapper.selectRoleModuleByParam(param);
        Map<String, String> roleModuleMap = new HashMap<String, String>();
        for (SysRoleModuleKey key : roleModules) {
            roleModuleMap.put(key.getModuleid(), key.getModuleid());
        }

        // 查找所选编辑角色的按钮权限
        List<SysRoleButtonKey> roleBurrons = sysRoleButtonMapper.selectRoleButtonByParam(param);
        Map<Short, Short> roleButtonMap = new HashMap<Short, Short>();
        for (SysRoleButtonKey key : roleBurrons) {
            roleButtonMap.put(key.getButtonid(), key.getButtonid());
        }

        // 处理
        Map<String, ModuleBtnResult> map = new HashMap<String, ModuleBtnResult>();
        ModuleBtnResult moduleBtn = null;
        for (SysModuledef sysModuledef : moduleList) {
            String moduleid = sysModuledef.getModuleid();
            moduleBtn = new ModuleBtnResult();
            moduleBtn.setModuleid(moduleid);
            moduleBtn.setModuledisc(sysModuledef.getDisc());
            if (buttondefMap.containsKey(moduleid)) {
                List<SysButtondef> buttonList = buttondefMap.get(moduleid);
                for (SysButtondef button : buttonList) {
                    if (roleButtonMap.containsKey(button.getButtonid())) {
                        button.setChecked(true);
                    } else {
                        button.setChecked(false);
                    }
                }
                moduleBtn.setButtonList(buttonList);
            }
            if (roleModuleMap.containsKey(moduleid)) {
                moduleBtn.setChecked(true);
            } else {
                moduleBtn.setChecked(false);
            }
            map.put(moduleid, moduleBtn);
        }
        List<PermissinTreeNode> treeNodeList = new LinkedList<PermissinTreeNode>();
        treeNodeList.addAll(getRoleModuleButtonWithPrivilege(null, systemid, map));
        return treeNodeList;
    }

    private List<PermissinTreeNode> getRoleModuleButtonWithPrivilege(String parentmoduleid, Short systemId, Map<String, ModuleBtnResult> moduleBtnMap) {
        List<PermissinTreeNode> treeNodeList = new LinkedList<PermissinTreeNode>();
        List<SysModuledef> sysModuledefList = null;
        if (StringUtil.isEmpty(parentmoduleid)) {
            sysModuledefList = CachedModuledefUtil.getTopModuledefList();
        } else {
            sysModuledefList = CachedModuledefUtil.getChildModuleList(parentmoduleid);
        }
        String type = "module";
        List<PermissinTreeNode> mchildren;
        List<PermissinTreeNode> bchildren;
        Map<String, Boolean> state = new HashMap<String, Boolean>();
        for (SysModuledef sysModuledef : sysModuledefList) {
            if (moduleBtnMap == null || moduleBtnMap.containsKey(sysModuledef.getModuleid())) {
                ModuleBtnResult moduleBtn = moduleBtnMap.get(sysModuledef.getModuleid());
                PermissinTreeNode node = new PermissinTreeNode();
                node.setId(sysModuledef.getModuleid() + Globals.COLUMNSPLIT + type);
                node.setText(sysModuledef.getDisc());
                node.setType(type);
                node.setSelected(moduleBtn.isChecked());
                if (moduleBtn.isChecked()) {
                    state = new HashMap<String, Boolean>();
                    state.put(TreeNodeStateEnum.SELECTED.getValue(), true);
                    node.setState(state);
                }

                mchildren = getRoleModuleButtonWithPrivilege(sysModuledef.getModuleid(), systemId, moduleBtnMap);
                if (mchildren == null || mchildren.size() == 0) {
                    bchildren = convertButtondef2TreeNode(moduleBtn.getButtonList());
                    if (bchildren == null || bchildren.size() == 0) {
                        node.leaf = true;
                    } else {
                        node.setChildren(bchildren);
                    }
                } else {
                    node.setChildren(mchildren);
                    node.leaf = false;
                }
                treeNodeList.add(node);
            }
        }
        return treeNodeList;
    }

    private List<PermissinTreeNode> convertButtondef2TreeNode(List<SysButtondef> list) {
        if (list == null || list.size() == 0) return null;
        List<PermissinTreeNode> nodeList = new ArrayList<PermissinTreeNode>();
        PermissinTreeNode node;
        String type = "button";
        Map<String, Boolean> state = new HashMap<String, Boolean>();
        for (SysButtondef record : list) {
            node = new PermissinTreeNode();
            node.setId(record.getButtonid() + Globals.COLUMNSPLIT + type);
            node.setText(record.getDisc());
            node.setType(type);
            node.setSelected(record.isChecked());
            state = new HashMap<String, Boolean>();
            if (record.isChecked()) {
                state.put(TreeNodeStateEnum.SELECTED.getValue(), true);
            } else {
                state.put(TreeNodeStateEnum.SELECTED.getValue(), false);
            }
            node.setState(state);
            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public int updatePermission(Short roleid, List<String> idList, LogInfo logInfo, Short systemid) {
        int row = 0;
        List<SysRoleModuleKey> roleModuleList = new LinkedList<SysRoleModuleKey>();
        List<SysRoleButtonKey> roleButtonList = new LinkedList<SysRoleButtonKey>();
        SysRoleModuleKey rm;
        SysRoleButtonKey rb;
        String[] paramArray;
        for (String str : idList) {
            paramArray = str.split(Globals.COLUMNSPLIT);
            if (paramArray.length != 2) continue;
            if (paramArray[1].equals("module")) {// 功能节点
                rm = new SysRoleModuleKey();
                rm.setRoleid(roleid);
                rm.setModuleid(paramArray[0]);
                roleModuleList.add(rm);
            } else if (paramArray[1].equals("button")) {// 按钮节点
                rb = new SysRoleButtonKey();
                rb.setRoleid(roleid);
                rb.setButtonid(Short.valueOf(paramArray[0]));
                roleButtonList.add(rb);
            } else {
                continue;
            }
        }
        // 判断勾选的按钮所属功能是否被选中，若未被选中，则无效
        List<SysRoleButtonKey> rbList = new LinkedList<SysRoleButtonKey>();
        SysButtondef buttondef;
        for (SysRoleModuleKey rmk : roleModuleList) {
            for (SysRoleButtonKey rbk : roleButtonList) {
                buttondef = CachedButtondefUtil.getButtondefByPrimaryKey(rbk.getButtonid());
                if (buttondef != null && buttondef.getModuleid().equals(rmk.getModuleid())) {
                    rbList.add(rbk);
                }
            }
        }

        // 先删除原先的sys_rolemodule
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleid", roleid);
        param.put("systemid", systemid);
        row += sysRoleModuleMapper.deleteRoleModuleByParam(param);
        // 插入sys_rolemodule
        if (roleModuleList != null && roleModuleList.size() > 0) {
            row += sysRoleModuleMapper.insertRoleModuleBatch(roleModuleList);
        }

        // 先删除原先的sys_rolebutton
        row += sysRoleButtonMapper.deleteRoleButtonByParam(param);
        // 插入sys_rolebutton
        if (rbList != null && rbList.size() > 0) {
            row += sysRoleButtonMapper.insertRoleButtonBatch(rbList);
        }
        return row;
    }

    // sysAccountRole-----------------------------------
    @Override
    public SysAccountRoleKey selectAccountRoleByAccount(String account) {
        SysAccountRoleExample emp = new SysAccountRoleExample();
        SysAccountRoleExample.Criteria cr = emp.createCriteria();
        cr.andAccountEqualTo(account);
        List<SysAccountRoleKey> list = sysAccountRoleMapper.selectByExample(emp);
        SysAccountRoleKey record = null;
        if (list != null && list.size() > 0) {
            record = list.get(0);
        }
        return record;
    }

    @Override
    public List<SysAccountRoleKey> selectAccountRoleByExample(SysAccountRoleExample example) {
        return sysAccountRoleMapper.selectByExample(example);
    }

    // sysModuledef----------------------------------
    @Override
    public List<SysModuledef> selectModuledefByAccount(String account, Short systemid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("systemid", systemid);
        return sysModuledefMapper.selectModuledefByAccount(params);
    }

    @Override
    public List<SysButtondef> selectButtondefByAccount(String account, Short systemid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("account", account);
        params.put("systemid", systemid);
        return sysButtondefMapper.selectButtondefByAccount(params);
    }

    @Override
    public List<SysButtondef> selectButtondefBySystemid(Short systemid) {
        return sysButtondefMapper.selectButtondefBySystemid(systemid);
    }

}
