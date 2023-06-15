package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "borrow_requests")
public class BorrowRequest {
    @Id
    @Column(name = "S_No")
    public int sno;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user_id;
    @ManyToOne
    @JoinColumn(name = "device_id")
    public Device device_id;
    public String approve_status;
    public String request_date;

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Device getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Device device_id) {
        this.device_id = device_id;
    }

    public String getApprove_status() {
        return approve_status;
    }

    public void setApprove_status(String approve_status) {
        this.approve_status = approve_status;
    }
}
