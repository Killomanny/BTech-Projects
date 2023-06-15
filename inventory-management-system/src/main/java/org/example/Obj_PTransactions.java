package org.example;

public class Obj_PTransactions {
    public Obj_PTransactions(int trans_id, int dev_id, String date, String type, int amount) {
        this.trans_id = trans_id;
        this.dev_id = dev_id;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    private int trans_id;
    private int dev_id;
    private String date;
    private String type;
    private int amount;

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public int getDev_id() {
        return dev_id;
    }

    public void setDev_id(int dev_id) {
        this.dev_id = dev_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
