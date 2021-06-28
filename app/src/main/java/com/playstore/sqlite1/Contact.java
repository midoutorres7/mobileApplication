package com.playstore.sqlite1;

public class Contact {
    private int id = 0;
    private String name;
    private int phone;
    private String sex = "Homme";
    private int age;
    private double poid;
    private double creatinine = 0;
    private double bili = 0;
    private double tgoTga = 0;

    public Contact() {

    }

    public Contact(int id, String name, int phone, String sex, int age, double poid, double creatinine, double bili, double tgoTga) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.poid = poid;
        this.creatinine = creatinine;
        this.bili = bili;
        this.tgoTga = tgoTga;
    }

    public Contact(String name, int phone, String sex, int age, double poid, double creatinine, double bili, double tgoTga) {
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.age = age;
        this.poid = poid;
        this.creatinine = creatinine;
        this.bili = bili;
        this.tgoTga = tgoTga;
    }

//getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public double getPoid() {
        return poid;
    }

    public double getCreatinine() {
        return creatinine;
    }

    public double getBili() {
        return bili;
    }

    public double getTgoTga() {
        return tgoTga;
    }

    //Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPoid(double poid) {
        this.poid = poid;
    }

    public void setCreatinine(double creatinine) {
        this.creatinine = creatinine;
    }

    public void setBili(double bili) {
        this.bili = bili;
    }

    public void setTgoTga(double tgoTga) {
        this.tgoTga = tgoTga;
    }

    //toString
    @Override
    public String toString() {
        return name;
    }


}
