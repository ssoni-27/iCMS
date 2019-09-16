package com.example.geekshivam.i_cms;


import android.util.Log;

import java.sql.Timestamp;

public class Complaint
{
    private String timestamp,type_of_complaint,issue,description;
    private Boolean status;
    private String address;
    private int phoneNo;
    private String available_date,availabletime;
    //new variables for each complaint having default value
 private String complaintLodgedIp;
    private String technicianAlloted;
    private String  faultDiagnosis;
    private String  materialReplaced;
    private String  remarkReason;
    private String  callCompletedDate;
    private String  userRemark;
    private String  userRemarkDesc;
    private String technicianAllotedDate;   




    //Construstors and getters,setters.
    public Complaint() {


    }

    //For fresh complaints.
    public Complaint(String type_of_complaint, String issue, String description, String address, int phoneNo, String available_date, String availabletime) {
        String t=new Timestamp(System.currentTimeMillis()).toString();
        this.timestamp=t.substring(0,4)+
            t.substring(5,7)+
            t.substring(8,10)+
            t.substring(11,13)+
            t.substring(14,16)+
            t.substring(17,19)+
            t.substring(20,21);
        this.type_of_complaint = type_of_complaint;
        this.issue = issue;
        this.description = description;
        this.status = true;
        this.address = address;
        this.phoneNo = phoneNo;
        this.available_date = available_date;
        this.availabletime = availabletime;
        //default value for these new variables not needed from the user
       this.complaintLodgedIp="0.0.0";
        this.technicianAlloted="Mr. xyz";
        this.technicianAllotedDate="01/01/2020";
        this.faultDiagnosis="not diagnozed";
        this.materialReplaced="Router";
        this.remarkReason="Unknown";
        this.callCompletedDate="01/01/2020";
        this.userRemark="Excellent";
        this.userRemarkDesc="Excellent";



    }

    //If complaint status needs to be set.
    public Complaint(String timestamp,String type_of_complaint, String issue, String description, String address,int phoneNo, String available_date, String availabletime,Boolean status) {
        this.timestamp=timestamp;
        this.type_of_complaint = type_of_complaint;
        this.issue = issue;
        this.description = description;
        this.status = status;
        this.address = address;
        this.phoneNo = phoneNo;
        this.available_date = available_date;
        this.availabletime = availabletime;
    }

    public String getTimestamp() {
        return timestamp;
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
