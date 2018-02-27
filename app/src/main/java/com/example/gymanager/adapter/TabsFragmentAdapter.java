package com.example.gymanager.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gymanager.R;
import com.example.gymanager.fragment.AbstractTabFragment;
import com.example.gymanager.fragment.addNewFragment;
import com.example.gymanager.fragment.historyFragment;
import com.example.gymanager.fragment.viewFragment;

import java.util.HashMap;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {
    private Map<Integer,AbstractTabFragment> tabs;
    private Context context;
   public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
    }
/*   public TabsFragmentAdapter(FragmentManager fm) {
       super(fm);
     //  this.context = context;
   }*/

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();

    }

    @Override
    public Fragment getItem(int position) {
       return tabs.get(position);
    }

    @Override
    public int getCount() {

        return tabs.size();

    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<Integer, AbstractTabFragment>(3);
        tabs.put(0, addNewFragment.getInstance(context));
        tabs.put(1, viewFragment.getInstance(context));
        tabs.put(2, historyFragment.getInstance(context));
    }
}
