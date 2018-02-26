package com.example.gymanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.adapter.RecyclerViewClickListener;
import com.example.gymanager.adapter.ViewListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import static com.example.gymanager.fragment.addNewFragment.myRef;


public class viewFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_view;

    private ViewListAdapter viewListAdapter;
    private List<PersonModel> personList = new ArrayList<>();

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
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(LAYOUT,container,false);
        setupPersonList(recyclerView);
        return  recyclerView;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    private void setupPersonList(RecyclerView recyclerView){

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PersonModel person = dataSnapshot.getValue(PersonModel.class);
                personList.add(person);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewClickListener listener = (view,position) ->{
          // Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
            Fragment newFragment = personInfFragment.getInstance();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.viewPager, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        };
        viewListAdapter = new ViewListAdapter(personList,getActivity(),listener);
        recyclerView.setAdapter(viewListAdapter);

    }
}

