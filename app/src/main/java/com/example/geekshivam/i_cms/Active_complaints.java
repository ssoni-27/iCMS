package com.example.geekshivam.i_cms;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Active_complaints extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.activity_active_complaints, parent, false);

    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState)
    {


        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        String[] z= {"complaint1","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2"
                ,"Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2"};
        recyclerView.setAdapter(new recyclerview(z));


    }


}
