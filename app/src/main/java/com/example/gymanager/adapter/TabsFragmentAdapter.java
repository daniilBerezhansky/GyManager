package com.example.gymanager.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.fragment.AbstractTabFragment;
import com.example.gymanager.fragment.addNewFragment;
import com.example.gymanager.fragment.historyFragment;
import com.example.gymanager.fragment.viewFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {
    private List<PersonModel> data;

    private Context context;
    private Map<Integer, AbstractTabFragment> tabs;
    private viewFragment vf;
    public TabsFragmentAdapter(FragmentManager fm, Context context, List<PersonModel> data) {
        super(fm);
        this.data = data;
        this.context = context;
        initTabsMap(context);
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        vf = viewFragment.getInstance(context, data);

        tabs.put(0, addNewFragment.getInstance(context));
        tabs.put(1, vf);
        tabs.put(2, historyFragment.getInstance(context));

    }
    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    public void setData(List<PersonModel> data) {
        this.data = data;
        vf.refreshData(data);

    }

}
