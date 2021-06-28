package com.playstore.sqlite1;

public class Consultation {
    private int id;
    private Contact contact;
    private Medicam medicam;
    private double doseAdmin;


    public Consultation(Contact contact, Medicam medicam, double doseAdmin) {
        this.contact = contact;
        this.medicam = medicam;
        this.doseAdmin = doseAdmin;
    }

    public Consultation(int id, Contact contact, Medicam medicam, double doseAdmin) {
        this.id = id;
        this.contact = contact;
        this.medicam = medicam;
        this.doseAdmin = doseAdmin;
    }

    public Contact getContact() {
        return contact;
    }

    public Medicam getMedicam() {
        return medicam;
    }

    public double getDoseAdmin() {
        return doseAdmin;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setMedicam(Medicam medicam) {
        this.medicam = medicam;
    }

    public void setDoseAdmin(double doseAdmin) {
        this.doseAdmin = doseAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "contact=" + contact +
                ", medicam=" + medicam +
                ", doseAdmin=" + doseAdmin +
                '}';
    }
}
