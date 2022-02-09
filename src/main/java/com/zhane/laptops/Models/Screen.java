package com.zhane.laptops.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String resolution;
    private String size;
    private String refreshRate;
    private String technology;
    private String peakBrightness;
    private String colourAccuracy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getPeakBrightness() {
        return peakBrightness;
    }

    public void setPeakBrightness(String peakBrightness) {
        this.peakBrightness = peakBrightness;
    }

    public String getColourAccuracy() {
        return colourAccuracy;
    }

    public void setColourAccuracy(String colourAccuracy) {
        this.colourAccuracy = colourAccuracy;
    }
}
