package com.huawei.deepitm.bean;

/**
 * 存储数据bena类
 * Created by wen on 2017/6/14.
 */

public class TimeData {

    private String posttime;
    private String title;
    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TimeData(String posttime, String title, String name, String content) {
        this.title = title;
        this.posttime = posttime;
        this.name = name;

        this.content = content;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
