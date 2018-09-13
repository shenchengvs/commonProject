package com.holley.platform.service.sys;

import java.util.List;
import java.util.Map;

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
import com.holley.platform.model.sys.SysRoleExample;
import com.holley.platform.model.sys.vo.PermissinTreeNode;

public interface AccountRoleService {

    // sysAccount---------------------------------------
    int insertAccountInfo(AccountInfo record, LogInfo logInfo);

    int updateAccountInfo(AccountInfo record, LogInfo logInfo);

    int updateAccountByPKSelective(SysAccount record);

    int deleteAccountByPK(String account, LogInfo logInfo);

    SysAccount selectAccountByPK(String account);

    AccountInfo selectAccountInfoByPK(String account);

    List<SysAccount> selectAccountByExample(SysAccountExample example);

    List<AccountInfo> selectAccountByPage(Map<String, Object> param);

    // sysRole-------------------------------------------
    List<SysRole> selectRoleByExample(SysRoleExample example);

    SysRole selectRoleByPK(Short roleid);

    int updateRoleByPKSelective(SysRole record);

    int deleteRoleByPK(Short roleid);

    int insertRole(SysRole record);

    List<PermissinTreeNode> loadPermission(Short roleid, WebUser user, Short systemid);

    int updatePermission(Short roleid, List<String> idList, LogInfo logInfo, Short systemid);

    // sysAccountRole-----------------------------------
    SysAccountRoleKey selectAccountRoleByAccount(String account);

    List<SysAccountRoleKey> selectAccountRoleByExample(SysAccountRoleExample example);

    // sysModuledef-------------------------------------
    List<SysModuledef> selectModuledefByAccount(String account, Short systemid);

    // sysButtondef-------------------------------------
    List<SysButtondef> selectButtondefByAccount(String account, Short systemid);

    List<SysButtondef> selectButtondefBySystemid(Short systemid);

}
