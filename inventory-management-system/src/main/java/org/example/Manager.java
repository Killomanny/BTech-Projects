package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "managers")
public class Manager extends Person {
    @Id
    public int manager_id;
    public String digest;

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "manager_id=" + manager_id +
                ", digest='" + digest + '\'' +
                '}';
    }

    public String getDigest() {
        return digest;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

}
