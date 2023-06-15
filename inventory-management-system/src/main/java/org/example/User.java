package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends Person {
    @Id
    public int user_id;
    public String digest;

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
