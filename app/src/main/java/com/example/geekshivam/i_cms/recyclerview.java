package com.example.geekshivam.i_cms;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;

/**
 * Created by Shivani Soni on 07-02-2019.
 */

public class recyclerview extends RecyclerView.Adapter<recyclerview.ProgrammingViewHolder> {

    private String[] data;
    private Context mContext;
    private Cursor mCursor;


    public recyclerview(Context context,Cursor cursor)
    {
        mContext=context;
        mCursor=cursor;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("iCMS","reached on create view holder");

        LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
        View view= inflater.inflate(R.layout.recycler_items,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgrammingViewHolder holder, int position) {

        Log.d("iCMS","onBindViewHolder() called.");

        if(!mCursor.moveToPosition(position))
        {
            return;
        }

        Log.d("iCMS","reached on bind holder");
        String title =mCursor.getString(3)+":"+mCursor.getString(4);
        String date=mCursor.getString(2);
        date=date.substring(6,8)+"/"+
                date.substring(4,6)+"/"+
                date.substring(0,4);

        holder.complaint.setText(title);
        holder.hostel.setText(mCursor.getString(6));
        holder.date.setText(date);

        Log.d("iCMS",title);
    }

    @Override
    public int getItemCount() {
        Log.d("iCMS","getCount() called= "+mCursor.getCount());
        return mCursor.getCount();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imageicon;
        TextView textView;
        TextView complaint;
        TextView hostel;
        TextView date;
        public ProgrammingViewHolder(View itemview){
            super(itemview);

            imageicon=(ImageView) itemview.findViewById(R.id.icon);
            complaint=(TextView) itemview.findViewById(R.id.abc);
            hostel=itemview.findViewById(R.id.hostel);
            date=itemview.findViewById(R.id.date);

        }
    }
}
