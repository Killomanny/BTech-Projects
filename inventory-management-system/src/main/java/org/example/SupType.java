package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supply_type")
public class SupType {
        @Id
        @Column(name = "supply_id")
        public int sup_type;
        @Column(name = "supply_name")
        public String sup_name;

        public int getSup_type() {
                return sup_type;
        }

        public void setSup_type(int sup_type) {
                this.sup_type = sup_type;
        }

        public String getSup_name() {
                return sup_name;
        }

        public void setSup_name(String sup_name) {
                this.sup_name = sup_name;
        }
}
