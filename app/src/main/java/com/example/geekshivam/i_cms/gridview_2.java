package com.example.geekshivam.i_cms;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class gridview_2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState)
        {

            return inflater.inflate(R.layout.activity_gridview_2 ,parent, false);

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
