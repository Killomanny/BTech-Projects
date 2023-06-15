package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "fund_requests")
public class FundRequest {
    @Id
    @Column(name = "request_id")
    public int req_id;
    @ManyToOne
    @JoinColumn(name = "device_id")
    public Device device_id;
    @ManyToOne
    @JoinColumn(name = "sup_device_id")
    public SupplierDevice sup_dev_id;

    public int cost;
    public String purpose;
    public String approve_status = "pending";

    public SupplierDevice getSup_dev_id() {
        return sup_dev_id;
    }

    public void setSup_dev_id(SupplierDevice sup_dev_id) {
        this.sup_dev_id = sup_dev_id;
    }


    public String getApprove_status() {
        return approve_status;
    }

    public void setApprove_status(String approve_status) {
        this.approve_status = approve_status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }

    public Device getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Device device_id) {
        this.device_id = device_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
