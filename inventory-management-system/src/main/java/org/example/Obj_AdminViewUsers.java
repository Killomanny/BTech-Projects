package org.example;

public class Obj_AdminViewUsers {
    public Obj_AdminViewUsers(int user_id, String full_name, String email_id, String contact) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.email_id = email_id;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Obj_AdminViewUsers{" +
                "user_id=" + user_id +
                ", full_name='" + full_name + '\'' +
                ", email_id='" + email_id + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    private int user_id;
    private String full_name;
    private String email_id;
    private String contact;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

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
}
