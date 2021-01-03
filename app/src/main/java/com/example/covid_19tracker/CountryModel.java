package com.example.covid_19tracker;


public class CountryModel {
    private String flagImageUrl;
    private String country,cases,todayCases,todayDeaths,recovered,totalDeaths,active,critical;
    private Boolean collaspe=false;

    public Boolean getCollaspe() {
        return collaspe;
    }

    public void setCollaspe(Boolean collaspe) {
        this.collaspe = collaspe;
    }

    public CountryModel(String flagImageUrl, String country, String cases, String todayCases, String todayDeaths, String recovered, String totalDeaths, String active, String critical, Boolean collaspe) {
        this.flagImageUrl = flagImageUrl;
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.totalDeaths = totalDeaths;
        this.active = active;
        this.critical = critical;
        this.collaspe=collaspe;
    }

    public String getFlagImageUrl() {
        return flagImageUrl;
    }

    public void setFlagImageUrl(String flagImageUrl) {
        this.flagImageUrl = flagImageUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String toString()
    {
        return getCountry();
    }
}
