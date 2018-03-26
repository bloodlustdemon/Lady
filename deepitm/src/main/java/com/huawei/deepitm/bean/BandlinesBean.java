package com.huawei.deepitm.bean;


import org.paul.lib.base.BaseBean;

public class BandlinesBean extends BaseBean {


    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BandlinesBean{" +
                "data=" + data +
                '}';
    }

    public class Data {
        private String customerId;
        private int lineCount;
        private int dataCount;
        private int issue;

        public int getIssue() {
            return issue;
        }

        public void setIssue(int issue) {
            this.issue = issue;
        }

        private BroadBandLinePerformance broadBandLinePerformance;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getLineCount() {
            return lineCount;
        }

        public void setLineCount(int lineCount) {
            this.lineCount = lineCount;
        }

        public int getDataCount() {
            return dataCount;
        }

        public void setDataCount(int dataCount) {
            this.dataCount = dataCount;
        }

        public BroadBandLinePerformance getBroadBandLinePerformance() {
            return broadBandLinePerformance;
        }

        public void setBroadBandLinePerformance(BroadBandLinePerformance broadBandLinePerformance) {
            this.broadBandLinePerformance = broadBandLinePerformance;
        }
    }

}
