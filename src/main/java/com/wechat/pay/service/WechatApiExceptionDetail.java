package com.wechat.pay.service;

/**
 * @author daibo
 * @date 2022/12/29 15:48
 */
public class WechatApiExceptionDetail {

    protected String field;
    protected String value;

    protected String issue;

    protected String location;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
