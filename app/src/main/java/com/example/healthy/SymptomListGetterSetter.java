package com.example.healthy;

public class SymptomListGetterSetter {
    String symptom;
    String remedy;
    String day;
    String month;
    String tvSymptom;
    String tvRemedy;

    public SymptomListGetterSetter(String symptom, String remedy, String day, String month, String tvSymptom, String tvRemedy) {
        this.symptom = symptom;
        this.remedy = remedy;
        this.day = day;
        this.month = month;
        this.tvSymptom = tvSymptom;
        this.tvRemedy = tvRemedy;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTvSymptom() {
        return tvSymptom;
    }

    public void setTvSymptom(String tvSymptom) {
        this.tvSymptom = tvSymptom;
    }

    public String getTvRemedy() {
        return tvRemedy;
    }

    public void setTvRemedy(String tvRemedy) {
        this.tvRemedy = tvRemedy;
    }
}
