package org.example;

public class Obj_AdminViewMans {
    public Obj_AdminViewMans(int manager_id, String full_name, String email, String contact, String branch) {
        this.manager_id = manager_id;
        this.full_name = full_name;
        this.email = email;
        this.contact = contact;
        this.branch = branch;
    }

    private int manager_id;
    private String full_name;
    private String email;
    private String contact;
    private String branch;

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
