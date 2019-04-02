package com.example.geekshivam.i_cms;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class profile_activity extends Fragment {

    String name,email;

    TextView name_TV,email_TV;

    public void setDataFromActivity(String n,String e)
    {
        name=n;
        email=e;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(getActivity().getApplicationContext(),navigation_drawer.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }
        );


        return inflater.inflate(R.layout.activity_profile_activity,parent,false);
    }

    public void onViewCreated (View view,Bundle savedInstanceState)
    {
        //TODO:do below task
//        name_TV=(TextView)findViewById(R.id.profile_name);
//        name_TV.setText(name);
//
//        email_TV=(TextView)findViewById(R.id.profile_email);
//        email_TV.setText(email);
    }

    public void goback( Fragment fragment)
    {
        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

}
