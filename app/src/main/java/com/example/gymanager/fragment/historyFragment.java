package com.example.gymanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymanager.R;


public class historyFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_history;
    public static historyFragment getInstance(Context context){
        Bundle args = new Bundle();
        historyFragment fragment = new historyFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_navigation_history));
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_history,container,false);
        return rootView;
    }


    public void setContext(Context context) {
        this.context = context;
    }
}
