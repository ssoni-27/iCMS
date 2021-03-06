package com.example.geekshivam.i_cms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class gridview_2 extends Fragment {

    MySQLiteOpenHelper myDB;
    String query_previous="SELECT * FROM complaint_list WHERE STATUS='false'";
    private static String table_name="complaint_list";

    public void setDataFromActivity(MySQLiteOpenHelper a)
    {
        myDB=a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState)
        {
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
            return inflater.inflate(R.layout.activity_gridview_2 ,parent, false);

        }

        @Override
                public void onViewCreated(View view,Bundle savedInstanceState)
        {

            RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recycle);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

            Cursor cursor=myDB.getReadableDatabase().rawQuery(query_previous,null);
            recyclerView.setAdapter(new recyclerview(getContext(),cursor));

            //recyclerView.setAdapter(new recyclerview(getContext(),myDB.getAllData()));
        }
}
