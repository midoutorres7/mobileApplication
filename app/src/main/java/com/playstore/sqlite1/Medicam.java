package com.playstore.sqlite1;

public class Medicam {
    private int id;
    private String name;
    private double doseComp;
    private double CLAIRANCE_MIN;
    private double CLAIRANCE_MAX;
    private double KEY_BILI_MIN;
    private double KEY_BILI_MAX;
    private double KEY_TGOTGA_MIN;
    private double KEY_TGOTGA_MAX;


    ////////////////////////Constructor//////////////////////////


    public Medicam() {

    }

    public Medicam(String name, double doseComp, double CLAIRANCE_MIN, double CLAIRANCE_MAX, double KEY_BILI_MIN, double KEY_BILI_MAX, double KEY_TGOTGA_MIN, double KEY_TGOTGA_MAX) {
        this.name = name;
        this.doseComp = doseComp;
        this.CLAIRANCE_MIN = CLAIRANCE_MIN;
        this.CLAIRANCE_MAX = CLAIRANCE_MAX;
        this.KEY_BILI_MIN = KEY_BILI_MIN;
        this.KEY_BILI_MAX = KEY_BILI_MAX;
        this.KEY_TGOTGA_MIN = KEY_TGOTGA_MIN;
        this.KEY_TGOTGA_MAX = KEY_TGOTGA_MAX;
    }

    public Medicam(int id, String name, double doseComp, double CLAIRANCE_MIN, double CLAIRANCE_MAX, double KEY_BILI_MIN, double KEY_BILI_MAX, double KEY_TGOTGA_MIN, double KEY_TGOTGA_MAX) {
        this.id = id;
        this.name = name;
        this.doseComp = doseComp;
        this.CLAIRANCE_MIN = CLAIRANCE_MIN;
        this.CLAIRANCE_MAX = CLAIRANCE_MAX;
        this.KEY_BILI_MIN = KEY_BILI_MIN;
        this.KEY_BILI_MAX = KEY_BILI_MAX;
        this.KEY_TGOTGA_MIN = KEY_TGOTGA_MIN;
        this.KEY_TGOTGA_MAX = KEY_TGOTGA_MAX;
    }


    //////////////////Geters//////////////////////////

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDoseComp() {
        return doseComp;
    }

    public double getCLAIRANCE_MIN() {
        return CLAIRANCE_MIN;
    }

    public double getCLAIRANCE_MAX() {
        return CLAIRANCE_MAX;
    }

    public double getKEY_BILI_MIN() {
        return KEY_BILI_MIN;
    }

    public double getKEY_BILI_MAX() {
        return KEY_BILI_MAX;
    }

    public double getKEY_TGOTGA_MIN() {
        return KEY_TGOTGA_MIN;
    }

    public double getKEY_TGOTGA_MAX() {
        return KEY_TGOTGA_MAX;
    }



    ////////////////////Seters//////////////////////////

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoseComp(double doseComp) {
        this.doseComp = doseComp;
    }

    public void setCLAIRANCE_MIN(double CLAIRANCE_MIN) {
        this.CLAIRANCE_MIN = CLAIRANCE_MIN;
    }

    public void setCLAIRANCE_MAX(double CLAIRANCE_MAX) {
        this.CLAIRANCE_MAX = CLAIRANCE_MAX;
    }

    public void setKEY_BILI_MIN(double KEY_BILI_MIN) {
        this.KEY_BILI_MIN = KEY_BILI_MIN;
    }

    public void setKEY_BILI_MAX(double KEY_BILI_MAX) {
        this.KEY_BILI_MAX = KEY_BILI_MAX;
    }

    public void setKEY_TGOTGA_MIN(double KEY_TGOTGA_MIN) {
        this.KEY_TGOTGA_MIN = KEY_TGOTGA_MIN;
    }

    public void setKEY_TGOTGA_MAX(double KEY_TGOTGA_MAX) {
        this.KEY_TGOTGA_MAX = KEY_TGOTGA_MAX;
    }


    //toString
    @Override
    public String toString() {
        return name;
    }
}
