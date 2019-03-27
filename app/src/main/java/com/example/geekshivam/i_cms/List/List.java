package com.example.geekshivam.i_cms.List;

import com.example.geekshivam.i_cms.R;
import com.example.geekshivam.i_cms.model.model;

import java.util.ArrayList;

/**
 * Created by Shivani Soni on 13-02-2019.
 */

public class List {

    public static ArrayList<model> getList(){
        ArrayList<model> modellist=new ArrayList<>();
        modellist.add(new model(R.drawable.yash_sir,"    Yashvardhan Sharma"," yash@pilani.bits-pilani.ac.in","9694096484"));
        modellist.add(new model(R.drawable.atul_sir,"Atul Runthala","atul.bitspilani@gmail.com","9352233844"));
        modellist.add(new model(R.drawable.bitspilani,"Vikas Singh","vikas@pilani.bits-pilani.ac.in","+91-1596 515100"));
        modellist.add(new model(R.drawable.bitspilani,"Surendra Kumar","surendra@pilani.bits-pilani.ac.in","+91-1596 515291"));
        return modellist;
    }
}
