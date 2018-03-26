package com.huawei.deepitm.bean;

public class BroadBandLine {
    private int status, type;
    private String level;
    private Data broadBandLine;

    @Override
    public String toString() {
        return "BroadBandLine{" +
                "status=" + status +
                ", type=" + type +
                ", level='" + level + '\'' +
                ", broadBandLine=" + broadBandLine +
                '}';
    }

    public Data getBroadBandLine() {
        return broadBandLine;
    }

    public void setBroadBandLine(Data broadBandLine) {
        this.broadBandLine = broadBandLine;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public class Data {
        @Override
        public String toString() {
            return "Data{" +
                    "lineId='" + lineId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", subscriberId='" + subscriberId + '\'' +
                    ", subscriberName='" + subscriberName + '\'' +
                    ", offeringId='" + offeringId + '\'' +
                    ", offeringName='" + offeringName + '\'' +
                    ", lineType='" + lineType + '\'' +
                    ", lineAddress='" + lineAddress + '\'' +
                    ", lineCoordinate='" + lineCoordinate + '\'' +
                    ", bandWidth=" + bandWidth +
                    ", effDate=" + effDate +
                    ", expDate=" + expDate +
                    '}';
        }

        private String lineId, customerId, customerName, subscriberId, subscriberName,
                offeringId, offeringName, lineType, lineAddress, lineCoordinate;
        private int bandWidth;
        private long effDate, expDate;

        public String getLineId() {
            return lineId;
        }

        public void setLineId(String lineId) {
            this.lineId = lineId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSubscriberId() {
            return subscriberId;
        }

        public void setSubscriberId(String subscriberId) {
            this.subscriberId = subscriberId;
        }

        public String getSubscriberName() {
            return subscriberName;
        }

        public void setSubscriberName(String subscriberName) {
            this.subscriberName = subscriberName;
        }

        public String getOfferingId() {
            return offeringId;
        }

        public void setOfferingId(String offeringId) {
            this.offeringId = offeringId;
        }

        public String getOfferingName() {
            return offeringName;
        }

        public void setOfferingName(String offeringName) {
            this.offeringName = offeringName;
        }

        public String getLineType() {
            return lineType;
        }

        public void setLineType(String lineType) {
            this.lineType = lineType;
        }

        public String getLineAddress() {
            return lineAddress;
        }

        public void setLineAddress(String lineAddress) {
            this.lineAddress = lineAddress;
        }

        public String getLineCoordinate() {
            return lineCoordinate;
        }

        public void setLineCoordinate(String lineCoordinate) {
            this.lineCoordinate = lineCoordinate;
        }

        public int getBandWidth() {
            return bandWidth;
        }

        public void setBandWidth(int bandWidth) {
            this.bandWidth = bandWidth;
        }

        public long getEffDate() {
            return effDate;
        }

        public void setEffDate(long effDate) {
            this.effDate = effDate;
        }

        public long getExpDate() {
            return expDate;
        }

        public void setExpDate(long expDate) {
            this.expDate = expDate;
        }
    }
}
