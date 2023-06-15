package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "working_condition")
public class WorkCondn {
    @Id
    public int con_id;
    public String con;

    public int getCon_id() {
        return con_id;
    }

    public void setCon_id(int con_id) {
        this.con_id = con_id;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
}
