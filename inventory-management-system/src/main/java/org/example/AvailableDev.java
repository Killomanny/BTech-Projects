package org.example;

public class AvailableDev {

    private int dev_id;
    private String model;
    private String type;

    public AvailableDev(int dev,String mode, String type){
        this.dev_id=dev;
        this.model=mode;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
