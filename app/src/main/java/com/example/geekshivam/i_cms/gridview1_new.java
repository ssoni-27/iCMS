package com.example.geekshivam.i_cms;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class gridview1_new extends Fragment  {

    MySQLiteOpenHelper myDB;

    final String TAG="iCMS";

    Spinner spinner1, spinner2, spinner3;
    Button date, time1, time2, register;
    Calendar c;
    TextView description;
    Dialog myDialog;
    //Firebase Database
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    //Complaint object
    Complaint complaint_obj;
    //date

    public void setDataFromActivity(MySQLiteOpenHelper msloh)
    {
        myDB=msloh;
        Log.d(TAG,"setDatafromActivity.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
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
        return inflater.inflate(R.layout.activity_gridview1_new, parent, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstancestate) {
        //Firebase reference initialise
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getInstance().getReference();
    //
        //dialog

      
        //current time1
        time1 = (Button) view.findViewById(R.id.time);
        time2 = (Button) view.findViewById(R.id.time2);
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment time = new timepicker();
                time.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });
        //time2
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment time1 = new timepicker2();
                time1.show(getActivity().getSupportFragmentManager(), "time picker");
            }
        });


        spinner1 = (Spinner) view.findViewById(R.id.spinner1);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        spinner3 = (Spinner) view.findViewById(R.id.spinner3);

        //register
        register = (Button) view.findViewById(R.id.ok);
        register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                //Complaint properly.
                complaint_obj = new Complaint(
                        spinner1.getSelectedItem().toString(),
                        spinner2.getSelectedItem().toString(),
                        "Description not added yet.",
                        spinner3.getSelectedItem().toString(),
                        123,
                        date.getText().toString(),
                        time1.getText().toString()+" to "+time2.getText().toString());

                register_complaint(mDatabaseReference, complaint_obj);
                Toast.makeText(getActivity().getApplicationContext(), "Your complaint is registered.", Toast.LENGTH_SHORT).show();
                Intent e = new Intent(getActivity(), navigation_drawer.class);
                startActivity(e);

            }
        });

        //date
        date = (Button) view.findViewById(R.id.button);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datepicker = new datepicker();
                datepicker.show(getActivity().getSupportFragmentManager(), "date picker");
            }
        });

        //spinner1
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),

                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Complaint_type));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        //spinnr2
        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,int pos, long id)
                {

                if (pos== 2)
                {
                    ArrayAdapter<String> array23 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.c_sub_cat2));
                    array23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(array23);
                }


                    else {
                    ArrayAdapter<String> array2 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.c_sub_cat));
                    array2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(array2);

                        }


                }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });





            ArrayAdapter<String> array = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Bhawan));
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(array);
        //toast
        String sp1 = String.valueOf(spinner1.getSelectedItem());
        if (sp1.contentEquals("Select Complaint")) {
            Toast.makeText(getActivity().getApplicationContext(),"Fill the requirements", Toast.LENGTH_SHORT).show();
        }

    }


    //register complaint and return success status.
    public boolean register_complaint(DatabaseReference m, Complaint c) {

        m.child("Complaints").push().setValue(c);

        //Add data to local database and result.
        boolean result= myDB.insertData_to_localDatabase(c);

        return result;
    }
}

