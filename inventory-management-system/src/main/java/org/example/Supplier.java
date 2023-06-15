package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "suppliers")
public class Supplier {
        @Id
        @Column(name = "supplier_id")
        public int sup_id;
        @Column(name = "supplier_name")
        public String sup_name;
        public String email_id;
        public String contact;
        public String digest;

        public String getEmail_id() {
                return email_id;
        }

        public void setEmail_id(String email_id) {
                this.email_id = email_id;
        }

        public String getContact() {
                return contact;
        }

        public void setContact(String contact) {
                this.contact = contact;
        }

        public String getDigest() {
                return digest;
        }

        public void setDigest(String digest) {
                this.digest = digest;
        }

        public int getSup_id() {
                return sup_id;
        }

        public void setSup_id(int sup_id) {
                this.sup_id = sup_id;
        }

        public String getSup_name() {
                return sup_name;
        }

        public void setSup_name(String sup_name) {
                this.sup_name = sup_name;
        }
}
