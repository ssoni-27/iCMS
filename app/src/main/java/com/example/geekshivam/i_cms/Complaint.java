package com.example.geekshivam.i_cms;


import android.util.Log;

public class Complaint
{
    private String type_of_complaint,issue,description;
    private Boolean status;
    private String address;
    private int phoneNo;
    private String available_date,availabletime;

    //Construstors and getters,setters.
    public Complaint() {
    }

    //For fresh complaints.
    public Complaint(String type_of_complaint, String issue, String description, String address, int phoneNo, String available_date, String availabletime) {
        this.type_of_complaint = type_of_complaint;
        this.issue = issue;
        this.description = description;
        this.status = true;
        this.address = address;
        this.phoneNo = phoneNo;
        this.available_date = available_date;
        this.availabletime = availabletime;
    }

    //If complaint status needs to be set.
    public Complaint(String type_of_complaint, String issue, String description, String address,int phoneNo, String available_date, String availabletime,Boolean status) {
        this.type_of_complaint = type_of_complaint;
        this.issue = issue;
        this.description = description;
        this.status = status;
        this.address = address;
        this.phoneNo = phoneNo;
        this.available_date = available_date;
        this.availabletime = availabletime;
    }

    public String getType_of_complaint() {
        return type_of_complaint;
    }

    public String getIssue() {
        return issue;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public String getAvailable_date() {
        return available_date;
    }

    public String getAvailabletime() {
        return availabletime;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
