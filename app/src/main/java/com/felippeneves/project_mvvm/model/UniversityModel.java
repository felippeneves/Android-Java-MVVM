package com.felippeneves.project_mvvm.model;

import java.util.List;

public class UniversityModel {

    private String alpha_two_code;
    private String name;
    private String country;

    public UniversityModel() {
    }

    public UniversityModel(String alpha_two_code, List<String> web_pages, String name, String country, List<String> domains) {
        this.alpha_two_code = alpha_two_code;
        this.name = name;
        this.country = country;
    }

    public String getAlpha_two_code() {
        return alpha_two_code;
    }

    public void setAlpha_two_code(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}