package com.example.gymanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gymanager.adapter.TabsFragmentAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

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
        initToolbar();
        initNavigationView();
        initTabs();
        tabLayout = findViewById(R.id.tab_layout);


    }

    private void initToolbar() {
        toolbar = findViewById(R.id.ToolBar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initTabs() {
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(getApplicationContext(),getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void initNavigationView() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.view_navigation_open,R.string.view_navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.action_notification_item:
                        showNotificationTab();
                }
                return  true;
            }
        });
    }
    private void showNotificationTab(){
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }

}
