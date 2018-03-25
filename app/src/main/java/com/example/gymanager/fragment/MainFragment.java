package com.example.gymanager.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymanager.Constants;
import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.adapter.TabsFragmentAdapter;
import com.example.gymanager.adapter.ViewListAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.gymanager.Constants.URL.GET_PERSON;


public class MainFragment extends Fragment {

    private ViewPager viewPager;
    private android.support.v7.widget.Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    TabsFragmentAdapter adapter;
    private ViewListAdapter vAdapter;
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        toolbar = rootView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
        drawerLayout = rootView.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.view_navigation_open,R.string.view_navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = rootView.findViewById(R.id.navigation);
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
        viewPager = rootView.findViewById(R.id.viewPager);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);


        return rootView;
    }

    private void showNotificationTab(){
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }

    private void setupViewPager(ViewPager viewPager) {

        adapter = new TabsFragmentAdapter(getChildFragmentManager(),getContext(), new ArrayList<PersonModel>());
        try {
            new PersonTask().execute();
        }
        catch (RuntimeException e){
            Log.d("Error","Error");
            System.exit(0);
        }
        viewPager.setAdapter(adapter);


        viewPager.setCurrentItem(0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {

                drawerLayout.openDrawer(GravityCompat.START);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private class PersonTask extends AsyncTask<PersonModel,Object,List<PersonModel>>  {
        @Override
        protected List<PersonModel> doInBackground(PersonModel... personModels){
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                ResponseEntity<PersonModel[]> responseEntity = restTemplate.getForEntity(GET_PERSON, PersonModel[].class);
                List<PersonModel> list = Arrays.asList(responseEntity.getBody());
                return list;

        }
        @Override
        protected void onPostExecute(List<PersonModel> personModels) {
            List<PersonModel> list = new ArrayList<PersonModel>();

            list.addAll(personModels);

            adapter.setData(list);

        }
    }

}
