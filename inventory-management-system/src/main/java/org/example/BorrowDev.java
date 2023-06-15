package org.example;

public class BorrowDev {
    public BorrowDev(int dev_id, int user_id, String model, String start_date, String due_date)  {
        this.user_id = user_id;
        this.dev_id= dev_id;
        this.model = model;
        this.start_date=start_date;
        this.due_date=due_date;
    }
    private int dev_id;
    private String model;
    private int user_id;
    private String start_date;
    private String due_date;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
