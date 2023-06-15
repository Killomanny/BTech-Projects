package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dev_status")
public class DevStatus {
    @Override
    public String toString() {
        return "DevStatus{" +
                "status_id=" + status_id +
                ", status='" + status + '\'' +
                '}';
    }

    @Id
    public int status_id;
    public String status;

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
