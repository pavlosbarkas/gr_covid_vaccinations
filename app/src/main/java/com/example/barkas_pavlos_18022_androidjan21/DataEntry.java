package com.example.barkas_pavlos_18022_androidjan21;

import androidx.annotation.NonNull;

public class DataEntry {

    private String area;
    private String daydiff;
    private String daytotal;
    private String totalVaccinations;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDaydiff() {
        return daydiff;
    }

    public void setDaydiff(String daydiff) {
        this.daydiff = daydiff;
    }

    public String getDaytotal() {
        return daytotal;
    }

    public void setDaytotal(String daytotal) {
        this.daytotal = daytotal;
    }

    public String getTotalVaccinations() {
        return totalVaccinations;
    }

    public void setTotalVaccinations(String totalVaccinations) {
        this.totalVaccinations = totalVaccinations;
    }

    @NonNull
    @Override
    public String toString() {
        return "DataEntry{" +
                "area=" + area + '\'' +
                ", daydiff=" + daydiff + '\'' +
                ", daytotal=" + daytotal + '\'' +
                ", totalvaccinations=" + totalVaccinations + '\'' + '}';

    }
}