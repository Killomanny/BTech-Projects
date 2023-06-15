package org.example;

public class Obj_UserUsageHistory {

    public Obj_UserUsageHistory(int dev_id, String type, String model, String start_date, String due_date, String returned_at) {
        this.dev_id = dev_id;
        this.type = type;
        this.model = model;
        this.start_date = start_date;
        this.due_date = due_date;
        this.returned_at = returned_at;
    }

    private int dev_id;
    private String type;
    private String model;
    private String start_date;
    private String due_date;
    private String returned_at;

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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getReturned_at() {
        return returned_at;
    }

    public void setReturned_at(String returned_at) {
        this.returned_at = returned_at;
    }
}
