package com.example.gymanager.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymanager.R;


public class personInfFragment extends AbstractTabFragment {

    /*public static personInfFragment getInstance(){
        Bundle args = new Bundle();
        personInfFragment fragment = new personInfFragment();
        fragment.setArguments(args);
        //fragment.setContext(context);
      //  fragment.setTitle(context.getString(R.string.tab_navigation_history));
        return fragment;
    }*/

    public personInfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_person_inf,container,false);
        return  view;
    }
    public void setContext(Context context) {
        this.context = context;
    }

}
