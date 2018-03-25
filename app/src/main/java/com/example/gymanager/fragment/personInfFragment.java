package com.example.gymanager.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;
import com.example.gymanager.dialogs.datePickerDialog;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static com.example.gymanager.Constants.URL.GET_PERSON;


public class personInfFragment extends AbstractTabFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public static final String POSITION = "position";
    private PersonModel data;
    private int pos = -1;
    private EditText firstName, secondName, email, begin,end;
    private String upFirstName, upSecondName, upEmail;
    private Date upBegin, upEnd;
    private Switch editable;
    private long id;
    private Button delete, update;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    public static personInfFragment newInstance(int position, PersonModel data){
        personInfFragment personInfFragment = new personInfFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION,position);
        personInfFragment.setArguments(args);
        personInfFragment.setData(data);
        return personInfFragment;
    }

    public personInfFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_person_inf,container,false);
        
        toolbar = rootView.findViewById(R.id.toolbar);
        setupToolbar();
        appBarLayout = rootView.findViewById(R.id.app_bar);
        firstName = rootView.findViewById(R.id.f_n);
        secondName = rootView.findViewById(R.id.s_n);
        email = rootView.findViewById(R.id.mail);
        begin = rootView.findViewById(R.id.s_d);
        end = rootView.findViewById(R.id.e_d);
        editable = rootView.findViewById(R.id.switchEdit);
        editable.setOnCheckedChangeListener(this);
        if (getArguments() != null) {
        pos = getArguments().getInt(POSITION);}
        id = data.getId();
        firstName.setText(data.getFirstName());
        secondName.setText(data.getSecondName());
        email.setText(data.getEmail());
        begin.setText(data.getDateStart()+"");
        end.setText(data.getDateEnd()+"");
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v,begin,upBegin);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog(v,end,upEnd);
            }
        });
        delete = rootView.findViewById(R.id.btnDel);
        delete.setOnClickListener(this);
        return rootView;
    }
    private void setupToolbar() {

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public PersonModel getPerson(int pos){


        return new PersonModel();
    }

    public void setData(PersonModel data) {
        this.data = data;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        EditText[] em  = new EditText[]{firstName, secondName, email, begin,end};
        if(isChecked){
            for(EditText el: em) el.setEnabled(true);
        }
        else {
            for(EditText el: em) el.setEnabled(false);
        }
    }

    public void showDatePickerDialog(View view, EditText editText, Date date){
        datePickerDialog newFragment = new datePickerDialog();
        newFragment.setEditText(editText);
        date = newFragment.getDate();
        newFragment.show(((Activity)getContext()).getFragmentManager(),"tag");

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btnDel:{
            showOkdialog();
           }
       }

    }

    private class DelPerson extends AsyncTask<Void, Void, Void> {

        private Exception exception;

        protected Void doInBackground(Void...voids) {

            RestTemplate restTemplate = new RestTemplate();
            String uri = GET_PERSON+id;
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            try {
                restTemplate.delete(uri);
            } catch (Exception e) {
                this.exception = e;
            }

            return null;
        }



        protected void onPostExecute(PersonModel feed) {


        }
    }

    private void showOkdialog(){
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Title of alert dialog");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                               new DelPerson().execute();

               getFragmentManager().popBackStackImmediate();

            } });


        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            } });
        adb.show();
    }
}
