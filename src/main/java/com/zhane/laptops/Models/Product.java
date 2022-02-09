package com.zhane.laptops.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String price;
    private String releasedYear;
    private String webcam;
    private String keyboard;

    @ManyToMany
    @JoinTable(name = "product_cpu",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cpu_id"))
    private Set<Cpu> availableCpus;

    @ManyToMany
    @JoinTable(name = "graphics_in_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "graphics_id"))
    private Set<Graphics> availableGraphics;

    @ManyToMany
    @JoinTable(name = "product_storage",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_id"))
    private Set<Storage> availableStorages;

    @ManyToMany
    @JoinTable(name = "product_screen",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "screen_id"))
    private Set<Screen> availableScreens;

    @ManyToMany
    @JoinTable(name = "product_ram",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ram_id"))
    private Set<Ram> availableRams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @JsonIgnoreProperties("products")
    private Manufacturer manufacturer;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getWebcam() {
        return webcam;
    }

    public void setWebcam(String webcam) {
        this.webcam = webcam;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public Set<Cpu> getAvailableCpus() {
        return availableCpus;
    }

    public void setAvailableCpus(Set<Cpu> availableCpus) {
        this.availableCpus = availableCpus;
    }

    public Set<Graphics> getAvailableGraphics() {
        return availableGraphics;
    }

    public void setAvailableGraphics(Set<Graphics> availableGraphics) {
        this.availableGraphics = availableGraphics;
    }

    public Set<Storage> getAvailableStorages() {
        return availableStorages;
    }

    public void setAvailableStorages(Set<Storage> availableStorages) {
        this.availableStorages = availableStorages;
    }

    public Set<Screen> getAvailableScreens() {
        return availableScreens;
    }

    public void setAvailableScreens(Set<Screen> availableScreens) {
        this.availableScreens = availableScreens;
    }

    public Set<Ram> getAvailableRams() {
        return availableRams;
    }

    public void setAvailableRams(Set<Ram> availableRams) {
        this.availableRams = availableRams;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
