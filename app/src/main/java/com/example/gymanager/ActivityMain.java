package com.example.gymanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.gymanager.fragment.MainFragment;

public class ActivityMain extends AppCompatActivity {
    private static final int LAYOUT = R.layout.activity_main;

    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;

    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Fragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
        tabLayout = findViewById(R.id.tab_layout);



    }




}
