package com.example.gymanager.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class addNewFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_add;
    private Button addButton;
    private EditText text_first, text_second,text_email;
    private FirebaseDatabase firebaseDatabase;
    public static DatabaseReference myRef;
    private int date_id =  0;
    private EditText start_date, end_date;
    private Calendar myCalendar = Calendar.getInstance();

    private String first_name,second_name,email,start,end;
   public static addNewFragment getInstance(Context context){
       Bundle args = new Bundle();
       addNewFragment fragment = new addNewFragment();
       fragment.setArguments(args);
       fragment.setContext(context);
       fragment.setTitle(context.getString(R.string.tab_navigation_add));
       return fragment;
   }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add,container,false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("Items");
        text_first =  view.findViewById(R.id.ed_name);
        text_second = view.findViewById(R.id.ed_sec_name);
        text_email =  view.findViewById(R.id.ed_email);
        start_date = view.findViewById(R.id.date_start);
        end_date = view.findViewById(R.id.date_end);
        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_id=1;
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date_id=2;
                new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        addButton = view.findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = text_first.getText().toString();
                second_name = text_second.getText().toString();
                email = text_email.getText().toString();
                start = start_date.getText().toString();
                end = end_date.getText().toString();
                Log.d("test",start);
                PersonModel personModel = new PersonModel(first_name,second_name,email,start,end);
                myRef.push().setValue(personModel);
            }
        });
        return  view;
    }



    public void setContext(Context context) {
        this.context = context;
    }

    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
        private void updateLabel() {
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
            switch (date_id){
                case 1: start_date.setText(sdf.format(myCalendar.getTime()));
                break;
                case 2: end_date.setText(sdf.format(myCalendar.getTime()));
            }

        }
    };


}
