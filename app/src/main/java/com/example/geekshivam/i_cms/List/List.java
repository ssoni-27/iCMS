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
        modellist.add(new model(R.drawable.yash_sir,"Dr. Yashvardhan Sharma"," yash@pilani.bits-pilani.ac.in"));
        modellist.add(new model(R.drawable.atul_sir,"Atul Runthala","atul.bitspilani@gmail.com"));
        return modellist;
    }
}
