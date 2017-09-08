package com.adityadua.recyclerviewdemo;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AdityaDua on 08/09/17.
 */

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {

    private ItemData[] itemDatas;

    public MyAdaptor(ItemData[] itemDatas) {
        this.itemDatas = itemDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,null);

        ViewHolder viewHolder = new ViewHolder(itemLayout);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtViewTitle.setText(itemDatas[position].getTitle());
        holder.imgViewIcon.setImageResource(itemDatas[position].getImageURL());

        holder.txtViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Snackbar.make(v, "ClickedON::"+itemDatas[position].getTitle(), Snackbar.LENGTH_SHORT)
                       .setAction("Action",null)
                       .show();
            }
        });





    }

    @Override
    public int getItemCount() {
        return itemDatas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // I need to add a row
        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            txtViewTitle = (TextView)itemView.findViewById(R.id.item_title);

            imgViewIcon = (ImageView)itemView.findViewById(R.id.item_icon);
        }
    }

}


