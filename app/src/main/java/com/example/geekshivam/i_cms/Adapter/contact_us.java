package com.example.geekshivam.i_cms.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.media.Image;
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
import com.example.geekshivam.i_cms.recyclerview;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Shivani Soni on 12-02-2019.
 */

public class contact_us extends RecyclerView.Adapter<contact_us.Myholder> {

private Context context;
private ArrayList<model> modes;

    public contact_us(Context context, ArrayList<model> models) {
        this.context = context;
        this.modes = models;
    }

    @Override
public Myholder onCreateViewHolder( ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_us,null);
      Myholder myholder=new Myholder(view);
      return myholder;
        }

    @Override
    public void onBindViewHolder(Myholder holder, final int position) {
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
    TextView email;
            private ImageView call;
        private ImageView mail;
        public Myholder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.yoyo);
            name=(TextView) itemView.findViewById(R.id.tv_name);
            email=(TextView) itemView.findViewById(R.id.email);

        }

    }

}

