package com.example.geekshivam.i_cms.List;

import com.example.geekshivam.i_cms.R;
import com.example.geekshivam.i_cms.model.model;

import java.util.ArrayList;

/**
 * Created by Shivani Soni on 13-02-2019.
 */

public class developer_list {
    public static ArrayList<model> getList(){
        ArrayList<model> modellist=new ArrayList<>();
        modellist.add(new model(R.drawable.shivam,"Shivam Soni"," f20171238@pilani.bits-pilani.ac.in","7728850326"));
        modellist.add(new model(R.drawable.tnzl,"Tanzeel Ur Rahman","f20170836@pilani.bits-pilani.ac.in","9119225203"));
        modellist.add(new model(R.drawable.dhruv,"Dhruv Singhal","f20170765@pilani.bits-pilani.ac.in","9971780889"));
        modellist.add(new model(R.drawable.som_bhaiya,"Sombuddha Chakravarty","f2016165@pilani.bits-pilani.ac.in","8697394768"));
        return modellist;
    }
}
