package com.example.geekshivam.i_cms.model;

/**
 * Created by Shivani Soni on 13-02-2019.
 */

public class model {
    private int imageid;
    private String  name;
    private String email;
    private String number;

    public model(int imageid, String name, String email, String number) {
        this.imageid = imageid;
        this.name = name;
        this.email = email;
        this.number=number;
    }

    public int getImageid() {return imageid;}

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public String getNumber(){
        return number;
    }

}
