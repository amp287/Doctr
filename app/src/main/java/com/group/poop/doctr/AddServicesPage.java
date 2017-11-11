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

public class AddServicesPage extends AppCompatActivity {

    private Spinner generalPracticeSpinner;
    private TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        generalPracticeSpinner = (Spinner) findViewById(R.id.generalPracticeSpinner);
        tempTextView = (TextView) findViewById(R.id.tempTextView);

    }

    public void onClickBackButton(View view){
        AddServicesPage.this.finish();
    }

    public String getSpinnerSelectedStringValue(Spinner spinner)
    {
        return spinner.getSelectedItem().toString();
    }

    public void setTheSpecialitySpinner(String generalPractice)
    {
        
    }


}

