package com.holley.platform.model.def;

import java.io.Serializable;

public class EntBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer           eid;
    private String            disc;
    private Integer           ownerid;
    private Short             ownertype;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public Short getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(Short ownertype) {
        this.ownertype = ownertype;
    }

}
