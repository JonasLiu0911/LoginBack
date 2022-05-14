package org.example.service.model;

import java.math.BigDecimal;

public class LocationModel {
    private Integer locId;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String locationDesc;
    private String locationTag;
    private Integer locationRadius;

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
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

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getLocationTag() {
        return locationTag;
    }

    public void setLocationTag(String locationTag) {
        this.locationTag = locationTag;
    }

    public Integer getLocationRadius() {
        return locationRadius;
    }

    public void setLocationRadius(Integer locationRadius) {
        this.locationRadius = locationRadius;
    }
}
