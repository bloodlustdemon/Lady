package com.huawei.deepitm.bean;

import java.util.List;
import java.util.Map;

public class BroadBandLinePerformance {
    private String lineId, offeringId, subscriberName, lineAddress,
            lineCoordinate;
    private Float bandWidth;
    private long effDate, expDate;
    private Map<Long, Float> upLinkTraffics, downLinkTraffics, avgUpLinkSpeeds, avgDownLinkSpeeds, upLinkUtilizations, downLinkUtilizations;

    @Override
    public String toString() {
        return "BroadBandLinePerformance{" +
                "lineId='" + lineId + '\'' +
                ", offeringId='" + offeringId + '\'' +
                ", subscriberName='" + subscriberName + '\'' +
                ", lineAddress='" + lineAddress + '\'' +
                ", lineCoordinate='" + lineCoordinate + '\'' +
                ", bandWidth=" + bandWidth +
                ", effDate=" + effDate +
                ", expDate=" + expDate +
                ", upLinkTraffics=" + upLinkTraffics +
                ", downLinkTraffics=" + downLinkTraffics +
                ", avgUpLinkSpeeds=" + avgUpLinkSpeeds +
                ", avgDownLinkSpeeds=" + avgDownLinkSpeeds +
                ", upLinkUtilizations=" + upLinkUtilizations +
                ", downLinkUtilizations=" + downLinkUtilizations +
                '}';
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
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

    public Float getBandWidth() {
        return bandWidth;
    }

    public void setBandWidth(Float bandWidth) {
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

    public Map<Long, Float> getUpLinkTraffics() {
        return upLinkTraffics;
    }

    public void setUpLinkTraffics(Map<Long, Float> upLinkTraffics) {
        this.upLinkTraffics = upLinkTraffics;
    }

    public Map<Long, Float> getDownLinkTraffics() {
        return downLinkTraffics;
    }

    public void setDownLinkTraffics(Map<Long, Float> downLinkTraffics) {
        this.downLinkTraffics = downLinkTraffics;
    }

    public Map<Long, Float> getAvgUpLinkSpeeds() {
        return avgUpLinkSpeeds;
    }

    public void setAvgUpLinkSpeeds(Map<Long, Float> avgUpLinkSpeeds) {
        this.avgUpLinkSpeeds = avgUpLinkSpeeds;
    }

    public Map<Long, Float> getAvgDownLinkSpeeds() {
        return avgDownLinkSpeeds;
    }

    public void setAvgDownLinkSpeeds(Map<Long, Float> avgDownLinkSpeeds) {
        this.avgDownLinkSpeeds = avgDownLinkSpeeds;
    }

    public Map<Long, Float> getUpLinkUtilizations() {
        return upLinkUtilizations;
    }

    public void setUpLinkUtilizations(Map<Long, Float> upLinkUtilizations) {
        this.upLinkUtilizations = upLinkUtilizations;
    }

    public Map<Long, Float> getDownLinkUtilizations() {
        return downLinkUtilizations;
    }

    public void setDownLinkUtilizations(Map<Long, Float> downLinkUtilizations) {
        this.downLinkUtilizations = downLinkUtilizations;
    }
}
