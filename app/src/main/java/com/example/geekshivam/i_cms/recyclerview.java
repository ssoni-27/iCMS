package com.example.geekshivam.i_cms;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    public recyclerview(String[] data){
         this.data = data;
    }
    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
        View view= inflater.inflate(R.layout.recycler_items,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgrammingViewHolder holder, int position) {
        String title =data[position];
        holder.textView.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;

    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imageicon;
        TextView textView;
        public ProgrammingViewHolder(View itemview){
            super(itemview);
            imageicon=(ImageView) itemview.findViewById(R.id.icon);
            textView=(TextView) itemview.findViewById(R.id.abc);
        }
    }
}
