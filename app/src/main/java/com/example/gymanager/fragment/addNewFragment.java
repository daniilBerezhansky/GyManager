package com.example.gymanager.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.gymanager.PersonModel;
import com.example.gymanager.R;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.gymanager.Constants.URL.GET_PERSON;

public class addNewFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_add;
    private Button addButton;
    private EditText text_first, text_second,text_email;
    private int date_id =  0;
    private EditText start_date, end_date;
    private Calendar myCalendar = Calendar.getInstance();
    private String first_name,second_name,email;
    private Date start,end;
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
        rootView = inflater.inflate(R.layout.fragment_add,container,false);
        text_first =  rootView.findViewById(R.id.ed_name);
        text_second = rootView.findViewById(R.id.ed_sec_name);
        text_email =  rootView.findViewById(R.id.ed_email);
        start_date = rootView.findViewById(R.id.date_start);
        end_date = rootView.findViewById(R.id.date_end);
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
        addButton = rootView.findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = text_first.getText().toString();
                second_name = text_second.getText().toString();
                email = text_email.getText().toString();
                new AddPerson().execute();
                clearEdits();
            }
        });
        return rootView;
    }
    private void clearEdits(){
       EditText[] list = new EditText[]{text_first,text_second,text_email,start_date,end_date};
       for(EditText edit:list) edit.setText("");
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
                case 1: {
                    start_date.setText(sdf.format(myCalendar.getTime()));
                    start = myCalendar.getTime();
                    break;
                }
                case 2: {
                    end_date.setText(sdf.format(myCalendar.getTime()));
                    end = myCalendar.getTime();
                    break;
                }
            }

        }
    };


    private class AddPerson extends AsyncTask<Void, Void, PersonModel> {

        private Exception exception;

        protected PersonModel doInBackground(Void...voids) {
            PersonModel personModel = new PersonModel(first_name,second_name,email,start,end);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            try {
                PersonModel ret = restTemplate.postForObject(GET_PERSON,personModel, PersonModel.class);
            } catch (Exception e) {
                this.exception = e;
            }
            return personModel;
        }



        protected void onPostExecute(PersonModel feed) {

        }
    }

}
