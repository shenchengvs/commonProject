package com.holley.platform.model.def;

import java.io.Serializable;
import java.util.List;

import com.holley.platform.model.sys.SysButtondef;
import com.holley.platform.model.sys.SysModuledef;

/**
 * 权限对象
 * 
 * @author road
 */
public class Permission implements Serializable {

    private static final long  serialVersionUID = 1L;
    private List<SysModuledef> modules;              // 功能
    private List<EntBaseInfo>  enterprises;          // 企业
    private List<SysButtondef> buttons;              // 按钮 

    public List<SysModuledef> getModules() {
        return modules;
    }

    public void setModules(List<SysModuledef> modules) {
        this.modules = modules;
    }

    public List<EntBaseInfo> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(List<EntBaseInfo> enterprises) {
        this.enterprises = enterprises;
    }

    public List<SysButtondef> getButtons() {
        return buttons;
    }

    public void setButtons(List<SysButtondef> buttons) {
        this.buttons = buttons;
    }

}
