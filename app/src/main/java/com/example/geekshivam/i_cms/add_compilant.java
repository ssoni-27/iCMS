package com.example.geekshivam.i_cms;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class add_compilant extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    Spinner spinner1, spinner2, spinner3;
    Button date, time1, time2,register;
    Calendar c;

    //date
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDatestring = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date.setText(currentDatestring);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        time1.setText("Hour: " + hourOfDay + " Minutes: " + minute);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_compilant);
//current time1
        time1 = (Button) findViewById(R.id.time);
        time2 = (Button) findViewById(R.id.time2);
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment time = new timepicker();
                time.show(getSupportFragmentManager(), "time picker");
            }
        });
        //time2
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment time1 = new timepicker2();
                time1.show(getSupportFragmentManager(), "time picker");
            }
        });


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
       //register
        register=(Button) findViewById(R.id.ok);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(add_compilant.this, "Your complaint is registered.", Toast.LENGTH_SHORT).show();
                Intent e=new Intent(add_compilant.this,navigation_drawer.class);
                startActivity(e);

            }
        });

        //date
        date = (Button) findViewById(R.id.button);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment datepicker = new datepicker();
                datepicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //spinner1
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(add_compilant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Complaint_type));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        //spinnr2
        ArrayAdapter<String> array2 = new ArrayAdapter<String>(add_compilant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.c_sub_cat));
        array2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(array2);

        ArrayAdapter<String> array23 = new ArrayAdapter<String>(add_compilant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.c_sub_cat2));
        array23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(array23);
        //spinner3
        ArrayAdapter<String> array = new ArrayAdapter<String>(add_compilant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Bhawan));
        array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(array);
        //toast
        String sp1 = String.valueOf(spinner1.getSelectedItem());
        if (sp1.contentEquals("Select Complaint")) {
            Toast.makeText(this, "Fill the requirements", Toast.LENGTH_SHORT).show();
        }

    }

}