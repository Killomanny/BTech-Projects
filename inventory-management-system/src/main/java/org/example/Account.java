package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
        @Id
        @Column(name = "account_id")
        private int acc_id;
        @Column(name = "account_name")
        private String acc_name;

        public int getAcc_id() {
                return acc_id;
        }

        public void setAcc_id(int acc_id) {
                this.acc_id = acc_id;
        }

        public String getAcc_name() {
                return acc_name;
        }

        public void setAcc_name(String acc_name) {
                this.acc_name = acc_name;
        }
}
