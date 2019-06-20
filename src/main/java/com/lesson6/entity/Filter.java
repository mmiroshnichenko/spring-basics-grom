package com.lesson6.entity;

import java.util.Date;

public class Filter {
    private Date dateFrom;
    private Date dateTo;
    private String cityFrom;
    private String cityTo;
    private String modelPlane;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getModelPlane() {
        return modelPlane;
    }

    public void setModelPlane(String modelPlane) {
        this.modelPlane = modelPlane;
    }
}
