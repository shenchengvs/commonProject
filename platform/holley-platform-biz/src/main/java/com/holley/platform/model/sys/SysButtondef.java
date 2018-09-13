package com.holley.platform.model.sys;

import java.io.Serializable;

public class SysButtondef implements Serializable {

    private static final long serialVersionUID = 241022882175776186L;

    private Short             buttonid;

    private String            disc;

    private String            moduleid;

    private String            url;

    private String            icon;

    private String            note;

    // def-------
    private boolean           checked;

    public Short getButtonid() {
        return buttonid;
    }

    public void setButtonid(Short buttonid) {
        this.buttonid = buttonid;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc == null ? null : disc.trim();
    }

    public String getModuleid() {
        return moduleid;
    }

    public void setModuleid(String moduleid) {
        this.moduleid = moduleid == null ? null : moduleid.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
