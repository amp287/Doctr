package com.group.poop.doctr;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MessengerPage extends AppCompatActivity {

    private DataBase dataBase;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            //TODO: go to login activity
        }



    }

    public void onClickBackButton(View view){
        MessengerPage.this.finish();
    }
}

