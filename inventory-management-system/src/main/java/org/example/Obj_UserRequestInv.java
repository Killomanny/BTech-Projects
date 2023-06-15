package org.example;

public class Obj_UserRequestInv {

    public Obj_UserRequestInv(int user_id, int dev_id, String request_date) {
        this.user_id = user_id;
        this.dev_id = dev_id;
        this.request_date = request_date;
    }

    private int user_id;
    private int dev_id;
    private String request_date;

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

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }
}
