package com.huawei.deepitm.bean;

import org.paul.lib.base.BaseBean;

import java.util.List;

public class RegionBean extends BaseBean {

    private List<Region>data;

    public List<Region> getData() {
        return data;
    }

    public void setData(List<Region> data) {
        this.data = data;
    }
}
