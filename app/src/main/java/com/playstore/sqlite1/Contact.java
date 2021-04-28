package com.playstore.sqlite1;

public class Contact {
    private int id = 0;
    private String name;
    private int phone;


//constructor
    public Contact(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Contact(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

//setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }
}
