package org.example;

import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    public int branch_id;
    public String branch_name;
    @OneToOne
    @JoinColumn(name = "manager_id")
    public Manager manager_id;
    public String start_date;

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public Manager getManager_id() {
        return manager_id;
    }

    public void setManager_id(Manager manager_id) {
        this.manager_id = manager_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
