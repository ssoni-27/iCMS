package com.example.geekshivam.i_cms;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class profile_activity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return inflater.inflate(R.layout.activity_profile_activity,parent,false);
    }

    public void onViewCreated (View view,Bundle savedInstanceState)
    {

    }

    public void goback( Fragment fragment)
    {
        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();



    }

}
