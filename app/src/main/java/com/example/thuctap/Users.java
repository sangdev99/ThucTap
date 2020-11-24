package com.example.thuctap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Users implements Serializable {

    @SerializedName("CompanyID")
    private int CompanyID ;
    @SerializedName("PKID")
    private String PKID ;
    @SerializedName("Username")
    private String Username ;
    @SerializedName("FirstName")
    private String FirstName ;
    @SerializedName("LastName")
    private String LastName ;
    @SerializedName("FullName")
    private String FullName ;
    @SerializedName("CompanyCD")
    private String CompanyCD ;
    @SerializedName("CompanyKey")
    private String CompanyKey ;

    public Users(int companyID, String PKID, String username, String firstName, String lastName, String fullName, String companyCD, String companyKey) {
        CompanyID = companyID;
        this.PKID = PKID;
        Username = username;
        FirstName = firstName;
        LastName = lastName;
        FullName = fullName;
        CompanyCD = companyCD;
        CompanyKey = companyKey;
    }

    public int getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(int companyID) {
        CompanyID = companyID;
    }

    public String getPKID() {
        return PKID;
    }

    public void setPKID(String PKID) {
        this.PKID = PKID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCompanyCD() {
        return CompanyCD;
    }

    public void setCompanyCD(String companyCD) {
        CompanyCD = companyCD;
    }

    public String getCompanyKey() {
        return CompanyKey;
    }

    public void setCompanyKey(String companyKey) {
        CompanyKey = companyKey;
    }
}
