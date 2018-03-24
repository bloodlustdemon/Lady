package com.huawei.deepitm.bean;

import java.util.Map;

public class DataT {
    private Map<Long,Double> downLinkUtilizations;

    public Map<Long, Double> getDownLinkUtilizations() {
        return downLinkUtilizations;
    }

    public void setDownLinkUtilizations(Map<Long, Double> downLinkUtilizations) {
        this.downLinkUtilizations = downLinkUtilizations;
    }
}
