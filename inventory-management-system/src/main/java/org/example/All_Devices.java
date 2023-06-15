package org.example;

public class All_Devices {
    public All_Devices(int dev_id, String model, String status, String work_condn) {
        this.dev_id = dev_id;
        this.model = model;
        this.status = status;
        this.work_condn = work_condn;
    }

    public int dev_id;
    public String model;
    public String status;
    public String work_condn;

    public String getWork_condn() {
        return work_condn;
    }

    public void setWork_condn(String work_condn) {
        this.work_condn = work_condn;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

