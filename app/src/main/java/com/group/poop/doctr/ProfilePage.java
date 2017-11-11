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

public class ProfilePage extends AppCompatActivity {
    // Profile Image
    private ImageButton profilePictureImageButton;

    // Profile Info
    private TextView userNameTextView;
    private TextView dateOfBirthTextView;
    private TextView emailAddressTextView;
    private TextView addressTextView;

    // Widgets
    private ImageButton settingsImageButton;
    private ImageButton emailImageButton;
    private ImageButton calendarImageButton;
    private ImageButton searchImageButton;
    private ImageButton servicesImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);

        // Profile Image
        profilePictureImageButton = (ImageButton) findViewById(R.id.profilePictureImageButton);

        // Profile Info
        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        dateOfBirthTextView = (TextView) findViewById(R.id.dateOfBirthTextView);
        emailAddressTextView = (TextView) findViewById(R.id.emailAddressTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);

        // Widgets
        settingsImageButton = (ImageButton) findViewById(R.id.settingsImageButton);
        emailImageButton = (ImageButton) findViewById(R.id.emailImageButton);
        calendarImageButton = (ImageButton) findViewById(R.id.calendarImageButton);
        searchImageButton = (ImageButton) findViewById(R.id.searchImageButton);
        servicesImageButton = (ImageButton) findViewById(R.id.servicesImageButton);

    }

    public void onClickSettingsButton(View view){
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);

    }
    public void onClickEmailButton(View view){
        Intent intent = new Intent(this, MessengerPage.class);
        startActivity(intent);
    }
    public void onClickCalendarButton(View view){
        Intent intent = new Intent(this, CalendarPage.class);
        startActivity(intent);

    }
    public void onClickSearchButton(View view){
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);

    }
    public void onClickAddServicesButton(View view){
        Intent intent = new Intent(this, AddServicesPage.class);
        startActivity(intent);
    }

    public void onClickMedicalHistoryButton(View view){
        Intent intent = new Intent(this, MedicalHistoryPage.class);
        startActivity(intent);
    }

}

