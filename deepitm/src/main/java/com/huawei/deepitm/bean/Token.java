package com.huawei.deepitm.bean;

public class Token {

    private String tid;
    private long expiryTime;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    @Override
    public String toString() {
        return "{" +
                "tid='" + tid + '\'' +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
