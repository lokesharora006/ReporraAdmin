package com.example.reporraadmin.data.model;

public class Admin {
    private String fullName;
    private String companyName;
    private String email;

    public Admin() {
    } // Firestore needs this

    public Admin(String fullName, String companyName, String email) {
        this.fullName = fullName;
        this.companyName = companyName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }
}