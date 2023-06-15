package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "fine_transactions")
public class FTransaction {
        @Id
        @Column(name = "transaction_id")
        private int trans_id;
        @Column(name = "deposit_from")
        @ManyToOne
        @JoinColumn(name = "deposit_from")
        private User from;
        @Column(name = "deposit_to")
        @ManyToOne
        @JoinColumn(name = "deposit_to")
        private Account to;
        private String date_time;
        private int amount;

        public int getTrans_id() {
                return trans_id;
        }

        public void setTrans_id(int trans_id) {
                this.trans_id = trans_id;
        }

        public User getFrom() {
                return from;
        }

        public void setFrom(User from) {
                this.from = from;
        }

        public Account getTo() {
                return to;
        }

        public void setTo(Account to) {
                this.to = to;
        }

        public String getDate_time() {
                return date_time;
        }

        public void setDate_time(String date_time) {
                this.date_time = date_time;
        }

        public int getAmount() {
                return amount;
        }

        public void setAmount(int amount) {
                this.amount = amount;
        }
}
