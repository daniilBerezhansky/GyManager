package com.example.gymanager.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.gymanager.R;
import com.example.gymanager.fragment.personInfFragment;

public class NavigationUtils {
    public static void navigateToPerson(Activity context){
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        Fragment fragment = personInfFragment.getInstance();
        transaction.hide(((AppCompatActivity) context).getSupportFragmentManager().findFragmentById(R.id.viewPager));
        transaction.add(R.id.viewPager, fragment);
        transaction.addToBackStack(null).commit();
    }
}
