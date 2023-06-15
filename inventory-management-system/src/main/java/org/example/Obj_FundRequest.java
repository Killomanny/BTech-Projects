package org.example;

public class Obj_FundRequest {
    public Obj_FundRequest(String type, int request_id, String model, String purpose, int cost, int dev_id) {
        this.type = type;
        this.request_id = request_id;
        this.model = model;
        this.purpose = purpose;
        this.cost = cost;
        this.dev_id = dev_id;
    }

    private String type;
    private int request_id;

    private String model;
    private String purpose;
    private int cost;
    private int dev_id;

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
