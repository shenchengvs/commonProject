package com.holley.platform.common.dataobject;

import java.util.ArrayList;
import java.util.List;

public class EchartsVO {

    private List<String> xList     = new ArrayList<String>();

    private String       name;
    private String       name2;
    private List<String> dataList  = new ArrayList<String>();
    private List<String> dataList2 = new ArrayList<String>();

    private String       dataListJson;
    private String       dataListJson2;

    private String       unit;                               // 数据单位

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public List<String> getxList() {
        return xList;
    }

    public void setxList(List<String> xList) {
        this.xList = xList;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public List<String> getDataList2() {
        return dataList2;
    }

    public void setDataList2(List<String> dataList2) {
        this.dataList2 = dataList2;
    }

    public String getDataListJson() {
        return dataListJson;
    }

    public void setDataListJson(String dataListJson) {
        this.dataListJson = dataListJson;
    }

    public String getDataListJson2() {
        return dataListJson2;
    }

    public void setDataListJson2(String dataListJson2) {
        this.dataListJson2 = dataListJson2;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
