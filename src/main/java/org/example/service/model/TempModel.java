package org.example.service.model;

public class TempModel {

    private Integer tempId;
    private String userTel;
    private Long curTime;
    private String locDescribe;
    private String tag;

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Long getCurTime() {
        return curTime;
    }

    public void setCurTime(Long curTime) {
        this.curTime = curTime;
    }

    public String getLocDescribe() {
        return locDescribe;
    }

    public void setLocDescribe(String locDescribe) {
        this.locDescribe = locDescribe;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
