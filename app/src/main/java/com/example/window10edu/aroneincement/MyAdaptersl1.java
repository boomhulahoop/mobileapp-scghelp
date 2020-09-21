package com.example.window10edu.aroneincement;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyAdaptersl1 extends RecyclerView.Adapter<MyAdaptersl1.ViewHolder>{


    private List<Dataselect1> mDataset;
    private MyClickListener mCallback;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardselect1_layout,parent,false);

        ViewHolder dataObjHolder = new ViewHolder(view);
        return dataObjHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getmText1());

        holder.icon.setImageResource(mDataset.get(position).getmIcon());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }




    public void setOnItemClickListener(MyClickListener mCallback){
        this.mCallback = mCallback;
    }

    public MyAdaptersl1(List<Dataselect1> myDataset) {
        mDataset = myDataset;
    }

    public interface MyClickListener{
        public void onItemClick(int position, View v);

    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        TextView title, desc;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.txtTitle);

            icon = (ImageView)itemView.findViewById(R.id.icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mCallback.onItemClick(getAdapterPosition(), v);


        }
    }

}


