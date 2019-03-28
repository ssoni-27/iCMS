package com.example.geekshivam.i_cms;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Shivani Soni on 06-02-2019.
 */

public class timepicker2 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    Button time1;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)  {
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int min=calendar.get(Calendar.MINUTE);
        time1=getActivity().findViewById(R.id.time2);
        return new TimePickerDialog(getActivity(),R.style.MyTimePickerDialogStyle,this,hour,min, DateFormat.is24HourFormat(getActivity()));

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        time1.setText("Hour: " + i + " Minutes: " +i1);
    }
}
