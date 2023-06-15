package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_devices")
public class SupplierDevice {
    @Id
    public int dev_id;
    public String type;
    public String brand;
    public String model;
    public int cost;
    public int quantity;
    @ManyToOne
    @JoinColumn(name = "sold_by")
    public Supplier sold_by;
    @Transient
    public int sid = 0;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSold_by() {
        return sold_by;
    }

    public void setSold_by(Supplier sold_by) {
        this.sold_by = sold_by;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "SupplierDevice{" +
                "dev_id=" + dev_id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", sold_by=" + sold_by +
                ", sid=" + sid +
                '}';
    }
}
