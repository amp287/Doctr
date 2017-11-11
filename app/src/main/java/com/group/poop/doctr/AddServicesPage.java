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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddServicesPage extends AppCompatActivity {

    private Spinner generalPracticeSpinner;
    private TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        generalPracticeSpinner = (Spinner) findViewById(R.id.generalPracticeSpinner);
        tempTextView = (TextView) findViewById(R.id.tempTextView);

        generalPracticeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected Item String
                String selectedText = getSpinnerSelectedStringValue(generalPracticeSpinner);

                // Retrieve the "speciality" or services list.
                String[] servicesList  = getServiceArrayName(selectedText);

                // Populate the services avaliable spinner
                pupulateServicesSpinner(servicesList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }
        });
    }

    public void onClickBackButton(View view){
        AddServicesPage.this.finish();
    }

    public String getSpinnerSelectedStringValue(Spinner spinner)
    {
        return spinner.getSelectedItem().toString();
    }

    private String[] getServiceArrayName(String generalPractice )
    {
        if( generalPractice.contains(( "Primary Health Care" )))
        {
           return getResources().getStringArray(R.array.primaryhealthcare_array);

        }else if( generalPractice.contains(( "Dental Care" )))
        {
            return getResources().getStringArray(R.array.dentalcare_array);
        }else if( generalPractice.contains(( "Specialty Care" )))
        {
            return getResources().getStringArray(R.array.specialtycare_array);
        }

        // Nothing Selected
        return null;
    }
    private void pupulateServicesSpinner(String[] servicesList)
    {
        // Populate the services spinner here.


    }

}

