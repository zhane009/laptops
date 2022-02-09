package com.zhane.laptops.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String capacity;
    private String speed;
    private int freeSlot;
    private String technology;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public int getFreeSlot() {
        return freeSlot;
    }

    public void setFreeSlot(int freeSlot) {
        this.freeSlot = freeSlot;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
}
