package org.example;

import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

@Entity
@Table(name = "devices")
public class Device {
        @Id
        @Column(name = "device_id")
        public int dev_id;
        public String type;
        public String brand;
        public String model;
        @ManyToOne
        @JoinColumn(name = "condn")
        public WorkCondn workcon;  // -> condition(as in warranty status)
        @OneToOne
        @JoinColumn(name = "status")
        public DevStatus status;
        public int floor;
        public int room;

        public int getDev_id() {
                return dev_id;
        }

        public void setDev_id(int dev_id) {
                this.dev_id = dev_id;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getBrand() {
                return brand;
        }

        public void setBrand(String brand) {
                this.brand = brand;
        }

        public String getModel() {
                return model;
        }

        public void setModel(String model) {
                this.model = model;
        }

        public WorkCondn getWork() {
                return workcon;
        }

        public void setWork(WorkCondn workcon) {
                this.workcon = workcon;
        }

        public DevStatus getStatus() {
                return status;
        }

        public void setStatus(DevStatus status) {
                this.status = status;
        }

        public int getFloor() {
                return floor;
        }

        public void setFloor(int floor) {
                this.floor = floor;
        }

        public int getRoom() {
                return room;
        }

        public void setRoom(int room) {
                this.room = room;
        }
}
