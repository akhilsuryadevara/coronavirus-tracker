package com.personalproject.coronavirustracker.models;

public class RecoveredStats {
    private String state;
    private String country;
    private String Lat;
    private String Long;
    private int totalRecoveredCases;
    private int totalRecoveredCasesSincePreviousDay;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalRecoveredCases() {
        return totalRecoveredCases;
    }

    public void setTotalRecoveredCases(int totalRecoveredCases) {
        this.totalRecoveredCases = totalRecoveredCases;
    }

    public int getTotalRecoveredCasesSincePreviousDay() {
        return totalRecoveredCasesSincePreviousDay;
    }

    public void setTotalRecoveredCasesSincePreviousDay(int totalRecoveredCasesSincePreviousDay) {
        this.totalRecoveredCasesSincePreviousDay = totalRecoveredCasesSincePreviousDay;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    @Override
    public String toString() {
        return "RecoveryStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalRecoveredCases='" + totalRecoveredCases + '\'' +
                ", totalRecoveredCasesSincePreviousDay='" + totalRecoveredCasesSincePreviousDay + '\'' +
                '}';
    }
}
