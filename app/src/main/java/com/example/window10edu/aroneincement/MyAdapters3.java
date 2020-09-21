package com.example.window10edu.aroneincement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapters3 extends RecyclerView.Adapter<MyAdapters3.ViewHolder>  {
    private List<Dataselect3> mDataset;
    private MyClickListener mCallback;

    @Override
    public MyAdapters3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardselect3_layout,parent,false);

        MyAdapters3.ViewHolder dataObjHolder = new MyAdapters3.ViewHolder(view);
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapters3.ViewHolder holder, int position) {
        holder.icon.setImageResource(mDataset.get(position).getmIcon());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }




    public void setOnItemClickListener(MyAdapters3.MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public MyAdapters3(List<Dataselect3> myDataset) {
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
            icon = (ImageView)itemView.findViewById(R.id.icons3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mCallback.onItemClick(getAdapterPosition(), v);


        }
    }



}

