package com.example.gymanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gymanager.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

public class addNewFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_add;
    private Button addButton;
    private EditText text_first, text_second,text_email;
    private FirebaseDatabase firebaseDatabase;
    public static DatabaseReference myRef;

    private String first_name,second_name,email;
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
        addButton = view.findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = text_first.getText().toString();
                second_name = text_second.getText().toString();
                email = text_email.getText().toString();
                ItemDTO itemDTO = new ItemDTO(first_name,second_name,email);
                myRef.push().setValue(itemDTO);
            }
        });
        return  view;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    @IgnoreExtraProperties
    static class ItemDTO implements Serializable {
        String firstName;
        String secondName;
        String email;

        public ItemDTO(String firstName, String secondName, String email) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.email = email;
        }
    }
}
