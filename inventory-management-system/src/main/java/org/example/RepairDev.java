package org.example;

public class RepairDev {
    public RepairDev(int dev_id, String model, String type) {
        this.dev_id = dev_id;
        this.model = model;
        this.type = type;
    }
    private int dev_id;
    private String model;
    private String type;

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
