package com.example.gymanager.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.fragment.personInfFragment;

import java.util.List;

public class NavigationUtils {
    public static void navigateToPerson(Context context, int position, PersonModel data){
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        Fragment fragment =  personInfFragment.newInstance(position,data);
        transaction.hide(((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.fragment_container));
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null).commit();
    }
}
