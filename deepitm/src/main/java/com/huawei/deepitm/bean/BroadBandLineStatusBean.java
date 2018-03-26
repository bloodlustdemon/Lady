package com.huawei.deepitm.bean;

import org.paul.lib.base.BaseBean;

import java.util.List;

public class BroadBandLineStatusBean extends BaseBean {


    private Data data;

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BroadBandLineStatusBean{" +
                "data=" + data +
                '}';
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private int lineCount,issue,alarmLineCount,goodDays;
        private List<BroadBandLine> broadBandLineStatuss;

        @Override
        public String toString() {
            return "Data{" +
                    "lineCount=" + lineCount +
                    ", issue=" + issue +
                    ", alarmLineCount=" + alarmLineCount +
                    ", goodDays=" + goodDays +
                    ", broadBandLineStatuss=" + broadBandLineStatuss +
                    '}';
        }

        public int getLineCount() {
            return lineCount;
        }

        public void setLineCount(int lineCount) {
            this.lineCount = lineCount;
        }

        public int getIssue() {
            return issue;
        }

        public void setIssue(int issue) {
            this.issue = issue;
        }

        public int getAlarmLineCount() {
            return alarmLineCount;
        }

        public void setAlarmLineCount(int alarmLineCount) {
            this.alarmLineCount = alarmLineCount;
        }

        public int getGoodDays() {
            return goodDays;
        }

        public void setGoodDays(int goodDays) {
            this.goodDays = goodDays;
        }

        public List<BroadBandLine> getBroadBandLineStatuss() {
            return broadBandLineStatuss;
        }

        public void setBroadBandLineStatuss(List<BroadBandLine> broadBandLineStatuss) {
            this.broadBandLineStatuss = broadBandLineStatuss;
        }
    }

}
