package org.example;

public class DeviceHistory {
    public DeviceHistory(int user_id, int dev_id, String start_date, String due_date, String returned_at) {
        this.user_id = user_id;
        this.dev_id = dev_id;
        this.start_date = start_date;
        this.due_date = due_date;
        this.returned_at = returned_at;
    }

    private int user_id;
    private int dev_id;
    private String start_date;
    private String due_date;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    private String returned_at;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public String getBorrowed_date() {
        return start_date;
    }

    public void setBorrowed_date(String borrowed_date) {
        this.start_date = borrowed_date;
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
