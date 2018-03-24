package com.huawei.deepitm.bean;

public class Msg {

    private String serialNo;
    private int notifyType;
    private String subject;
    private String content;
    private int noticeStatus;
    private int serviceType;
    private String receiver;
    private String unifyID;
    private int readStatus;
    private int beID;
    private int orgID;
    private long createdTime;
    private String createdBy;
    private long updatedTime;
    private String updatedBy;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(int noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUnifyID() {
        return unifyID;
    }

    public void setUnifyID(String unifyID) {
        this.unifyID = unifyID;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public int getBeID() {
        return beID;
    }

    public void setBeID(int beID) {
        this.beID = beID;
    }

    public int getOrgID() {
        return orgID;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "serialNo='" + serialNo + '\'' +
                ", notifyType=" + notifyType +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", noticeStatus=" + noticeStatus +
                ", serviceType=" + serviceType +
                ", receiver='" + receiver + '\'' +
                ", unifyID='" + unifyID + '\'' +
                ", readStatus=" + readStatus +
                ", beID=" + beID +
                ", orgID=" + orgID +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
