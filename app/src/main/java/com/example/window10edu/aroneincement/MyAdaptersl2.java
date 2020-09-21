package com.example.window10edu.aroneincement;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdaptersl2 extends RecyclerView.Adapter<MyAdaptersl2.ViewHolder> {

    private List<Dataselect2> mDataset;
    private MyClickListener mCallback;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardselect2_layout,parent,false);

        ViewHolder dataObjHolder = new ViewHolder(view);
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.icon.setImageResource(mDataset.get(position).getmIcon());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }




    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public MyAdaptersl2(List<Dataselect2> myDataset) {
        mDataset = myDataset;
    }

    public interface MyClickListener{
        public void onItemClick(int position, View v);

    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.iconsl2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mCallback.onItemClick(getAdapterPosition(), v);


        }
    }



}
