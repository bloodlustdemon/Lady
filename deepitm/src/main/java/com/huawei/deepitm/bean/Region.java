package com.huawei.deepitm.bean;

public class Region {
    private String infoKey;
    private String displayString;
    private int orderNo;

    public String getInfoKey() {
        return infoKey;
    }

    public void setInfoKey(String infoKey) {
        this.infoKey = infoKey;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Region{" +
                "infoKey='" + infoKey + '\'' +
                ", displayString='" + displayString + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
