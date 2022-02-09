package com.zhane.laptops.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String manufacturer;
    private String baseClockSpeed;
    private String maxClockSpeed;
    private int numberOfCores;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBaseClockSpeed() {
        return baseClockSpeed;
    }

    public void setBaseClockSpeed(String baseClockSpeed) {
        this.baseClockSpeed = baseClockSpeed;
    }

    public String getMaxClockSpeed() {
        return maxClockSpeed;
    }

    public void setMaxClockSpeed(String maxClockSpeed) {
        this.maxClockSpeed = maxClockSpeed;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }
}