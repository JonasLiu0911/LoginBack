package org.example.service.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleModel {
    private Integer id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer userId;
    private String scheduleTitle;
    private String scheduleInfo;
    private Long scheduleStartTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(String scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public Long getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(Long scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

}
