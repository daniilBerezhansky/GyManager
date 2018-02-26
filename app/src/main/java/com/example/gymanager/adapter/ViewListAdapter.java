package com.example.gymanager.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.fragment.personInfFragment;
import com.example.gymanager.fragment.viewFragment;

import java.util.List;

public class ViewListAdapter extends RecyclerView.Adapter<ViewListAdapter.AllPersonGridHolder>{
    private List<PersonModel> allPersonList;
    private Context mContext;
    private RecyclerViewClickListener recyclerViewClickListener;

    public ViewListAdapter(List<PersonModel> allPersonList, Context mContext, RecyclerViewClickListener listener) {
        this.allPersonList = allPersonList;
        this.mContext = mContext;
        this.recyclerViewClickListener = listener;
    }

    @Override
    public AllPersonGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        return new AllPersonGridHolder(v,recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(AllPersonGridHolder holder, int position) {
        PersonModel allPersonItem = allPersonList.get(position);
        holder.first_name.setText(allPersonItem.getFirstName());
        holder.second_name.setText(allPersonItem.getSecondName());

    }

    @Override
    public int getItemCount() {
        return(null != allPersonList ? allPersonList.size():0);
    }

    private RecyclerViewClickListener mListener;

    public class AllPersonGridHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView first_name , second_name;
        public AllPersonGridHolder(View itemView,RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            this.first_name  =  itemView.findViewById(R.id.first_name);
            this.second_name =  itemView.findViewById(R.id.second_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           mListener.onClick(v,getAdapterPosition());
        }

    }
} 
