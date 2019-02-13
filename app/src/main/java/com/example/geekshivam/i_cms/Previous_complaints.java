package com.example.geekshivam.i_cms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Previous_complaints extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_complaints);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] z= {"complaint1","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2"
                ,"Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2","Complaint2"};
        recyclerView.setAdapter(new recyclerview(z));
     }
}
