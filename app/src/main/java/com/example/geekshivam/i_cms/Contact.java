package com.example.geekshivam.i_cms;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
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

public class  Contact extends Fragment {

    LinearLayoutManager layoutManager;
    ArrayList<model> models;
    RecyclerView recyclerView;
    contact_us contact;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState) {
       return inflater.inflate(R.layout.activity_contact,parent,false);


    }


    @Override
        public void onViewCreated (View view,Bundle savedInstanceState)
        {
            recyclerView = (RecyclerView)view.findViewById(R.id.contact_us);
            layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            models = List.getList();
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            contact = new contact_us(getActivity().getApplicationContext(), models);
            recyclerView.setAdapter(contact);


        }
    }



