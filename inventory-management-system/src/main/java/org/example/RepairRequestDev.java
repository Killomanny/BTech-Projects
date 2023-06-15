package org.example;

public class RepairRequestDev {
    public RepairRequestDev(int dev_id, String model, String type, String brand) {
        this.dev_id = dev_id;
        this.type = type;
        this.model = model;
        this.brand = brand;
    }
    private int dev_id;
    private String model;
    private String type;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
