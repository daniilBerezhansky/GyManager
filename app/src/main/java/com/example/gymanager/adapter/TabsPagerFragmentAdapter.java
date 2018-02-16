package com.example.gymanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gymanager.fragment.ExampleFragment;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {
    private  String[] tabs;
    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "Tab 1",
                "test",
                "Tab_2"
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return ExampleFragment.getInstanse();
            case 1:return ExampleFragment.getInstanse();
            case 2:return ExampleFragment.getInstanse();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
