package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase_transactions")
public class PTransactions {
        @Id
        @Column(name = "transaction_id")
        public int trans_id;
        @OneToOne
        @JoinColumn(name = "supply_type")
        public SupType sup_type;
        @ManyToOne
        @JoinColumn(name = "device_id")
        public Device dev_id;
        @Column(name = "date")
        public String date_and_time;
        public int amount;
        @ManyToOne
        @JoinColumn(name = "sent_to")
        public Supplier sent_to;

        public Supplier getSent_to() {
                return sent_to;
        }

        public void setSent_to(Supplier sent_to) {
                this.sent_to = sent_to;
        }


        public int getTrans_id() {
                return trans_id;
        }

        public void setTrans_id(int trans_id) {
                this.trans_id = trans_id;
        }
        public SupType getSup_type() {
                return sup_type;
        }

        public void setSup_type(SupType sup_type) {
                this.sup_type = sup_type;
        }

        public Device getDev_id() {
                return dev_id;
        }

        public void setDev_id(Device dev_id) {
                this.dev_id = dev_id;
        }

        public String getDate_and_time() {
                return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
                this.date_and_time = date_and_time;
        }

        public int getAmount() {
                return amount;
        }

        public void setAmount(int amount) {
                this.amount = amount;
        }
}
