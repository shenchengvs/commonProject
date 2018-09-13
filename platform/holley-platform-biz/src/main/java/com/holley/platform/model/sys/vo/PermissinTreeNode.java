package com.holley.platform.model.sys.vo;

import java.util.List;
import java.util.Map;

public class PermissinTreeNode {

    public String                  id;
    public String                  text;
    public String                  type;
    public List<PermissinTreeNode> children;
    public boolean                 selected;
    public String                  iconCls;
    public int                     checkState; // 树节点选中状态，0：不选中，1：选中，2：半选
    public Map<String, Boolean>    state;
    public boolean                 leaf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<PermissinTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<PermissinTreeNode> children) {
        this.children = children;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Boolean> getState() {
        return state;
    }

    public void setState(Map<String, Boolean> state) {
        this.state = state;
    }

}
