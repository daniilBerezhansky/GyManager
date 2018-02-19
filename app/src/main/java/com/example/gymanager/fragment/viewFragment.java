package com.example.gymanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymanager.R;


public class viewFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_view;

    public static viewFragment getInstance(Context context){
        Bundle args = new Bundle();
        viewFragment fragment = new viewFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_navigation_view));
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view,container,false);
        return  view;
    }


    public void setContext(Context context) {
        this.context = context;
    }
}

