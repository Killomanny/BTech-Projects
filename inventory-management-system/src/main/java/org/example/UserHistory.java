package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "usage_history")
public class UserHistory {
    @Id
    @Column(name = "S_No")
    public int sno;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user_id;
    @ManyToOne
    @JoinColumn(name = "device_id")
    public Device device_id;
    public String start_date;
    public String due_date;

    @Override
    public String toString() {
        return "UserHistory{" +
                "sno=" + sno +
                ", user_id=" + user_id +
                ", device_id=" + device_id +
                ", start_date='" + start_date + '\'' +
                ", due_date='" + due_date + '\'' +
                ", returned_at='" + returned_at + '\'' +
                '}';
    }

    public String returned_at;

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
