package com.huawei.deepitm.bean;


import org.paul.lib.base.BaseBean;
import java.util.List;

public class UnReadMsgBean extends BaseBean {


    private List<Msg> data;

    public List<Msg> getData() {
        return data;
    }

    public void setData(List<Msg> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UnReadMsgBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
