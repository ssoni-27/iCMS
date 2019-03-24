package com.example.geekshivam.i_cms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;


/**
 * Created by Shivani Soni on 06-02-2019.
 */

public class datepicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

  Button date;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        date=getActivity().findViewById(R.id.button);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDatestring = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        date.setText(currentDatestring);


    }
}
