package org.example;

public class DeviceRequest {

    public DeviceRequest(int dev_id, String type, String model, String status) {
        this.dev_id = dev_id;
        this.type = type;
        this.model = model;
        this.status = status;
    }

    private int dev_id;
    private String type;
    private String model;
    private String status;

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
