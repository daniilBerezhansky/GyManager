package com.example.gymanager.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.adapter.ViewListAdapter;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.gymanager.Constants.URL.GET_PERSON;


public class viewFragment extends AbstractTabFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final int LAYOUT = R.layout.fragment_view;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ViewListAdapter viewListAdapter;
    private List<PersonModel> personList = new ArrayList<>();

    public static viewFragment getInstance(Context context, List<PersonModel> list){
        Bundle args = new Bundle();
        viewFragment fragment = new viewFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_navigation_view));
         fragment.setPersonList(list);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(LAYOUT, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        setupPersonList(recyclerView);
        mSwipeRefreshLayout =  rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                loadRecyclerViewData();
            }
        });
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d("fragment","back");
            }
        });

        return  rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("fragment", "pause");
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void setupPersonList(RecyclerView recyclerView){

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewListAdapter = new ViewListAdapter(personList,getActivity());

        recyclerView.setAdapter(viewListAdapter);


    }
    public void setPersonList(List<PersonModel> personList) {
        this.personList = personList;
    }

    public void refreshData(List<PersonModel> data){
        viewListAdapter.setData(data);
        viewListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        loadRecyclerViewData();
    }
    private void loadRecyclerViewData()
    {
        mSwipeRefreshLayout.setRefreshing(true);
        new PersonTask().execute();

        mSwipeRefreshLayout.setRefreshing(false);

    }

    private class PersonTask extends AsyncTask<PersonModel,Object,List<PersonModel>> {
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
            viewListAdapter.setData(personModels);
            viewListAdapter.notifyDataSetChanged();

        }
    }

}

