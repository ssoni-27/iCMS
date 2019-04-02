package com.example.geekshivam.i_cms.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geekshivam.i_cms.R;
import com.example.geekshivam.i_cms.model.model;

import java.util.ArrayList;

/**
 * Created by Shivani Soni on 13-02-2019.
 */

public class Developer extends RecyclerView.Adapter<contact_us.Myholder> {
    private Context context;
    private ArrayList<model> modes;
    private ArrayList<model> number;

    public Developer(Context context, ArrayList<model> models) {
        this.context = context;
        this.modes = models;
        this.number = number;
    }

    @Override
    public contact_us.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.developer_row,null);
        contact_us.Myholder myholder=new contact_us.Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(contact_us.Myholder holder, final int position) {
        holder.pic.setImageResource(modes.get(position).getImageid());
        holder.name.setText(modes.get(position).getName());
        holder.email.setText(modes.get(position).getEmail());

    }


    @Override
    public int getItemCount() {
        return modes.size();

    }
    public static class Myholder extends RecyclerView.ViewHolder{

        ImageView pic;
        TextView name;
        TextView numbers;
        TextView email;
        TextView desc;

        private ImageView call;
        private ImageView mail;
        public Myholder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.image_develop);
            name=(TextView) itemView.findViewById(R.id.name_developer);
            email=(TextView) itemView.findViewById(R.id.email_developer);
            ImageView call=itemView.findViewById(R.id.call);
            call.setVisibility(View.INVISIBLE);
            numbers=(TextView) itemView.findViewById(R.id.number);


        }

    }

}
