package com.example.geekshivam.i_cms;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geekshivam.i_cms.Adapter.contact_us;
import com.example.geekshivam.i_cms.List.List;
import com.example.geekshivam.i_cms.model.model;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class  Contact extends AppCompatActivity {

 LinearLayoutManager layoutManager;
 ArrayList<model>models;
 RecyclerView recyclerView;
 contact_us contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
      recyclerView=(RecyclerView) findViewById(R.id.contact_us);
      layoutManager=new LinearLayoutManager(this);
      models= List.getList();
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setHasFixedSize(true);
      contact = new contact_us(Contact.this,models);
      recyclerView.setAdapter(contact);


    }
    }
