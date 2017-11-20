package com.group.poop.doctr;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingsPage extends AppCompatActivity {
    private DataBase dataBase;

    private ListView viewingPriviladgeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dataBase = new DataBase();

        // Views
        viewingPriviladgeListView = (ListView) findViewById(R.id.viewingPriviladgeListView);

        initialize_ViewingPriviladgeListView();
    }

    String[] ListElements = new String[] {
            "Android",
            "PHP"
    };

    public void initialize_ViewingPriviladgeListView()
    {
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (SettingsPage.this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        viewingPriviladgeListView.setAdapter(adapter);
    }

    public void onClickDeleteMyProfile(View view)
    {
        // TODO - Actually Send a UserProfile Object.
        dataBase.deleteUserProfile(null);

    }

}

