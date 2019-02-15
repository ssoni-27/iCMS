package com.example.geekshivam.i_cms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.geekshivam.i_cms.Adapter.contact_us;
import com.example.geekshivam.i_cms.List.List;
import com.example.geekshivam.i_cms.List.developer_list;
import com.example.geekshivam.i_cms.model.model;

import java.util.ArrayList;

public class Developer extends AppCompatActivity {
    LinearLayoutManager layoutManager;
    ArrayList<model> models;
    RecyclerView recyclerView;
    contact_us contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        recyclerView=(RecyclerView) findViewById(R.id.developer_recycle);
        layoutManager=new LinearLayoutManager(this);
        models= developer_list.getList();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        contact = new contact_us(Developer.this,models);
        recyclerView.setAdapter(contact);
    }
}
