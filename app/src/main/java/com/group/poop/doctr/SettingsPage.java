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
import android.widget.AdapterView;

import java.util.Date;
import java.util.List;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class SettingsPage extends AppCompatActivity {
    private DataBase dataBase;

    // Views
    private Button deleteProfileButton;
    private Button deletePrivilegeButton;
    private ListView viewingPrivilegeListView;

    // Adapter List
    ArrayAdapter<String> privilegeList_Adapter;
    private int lastClickedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dataBase = new DataBase();

        // Views
        deleteProfileButton = (Button) findViewById(R.id.deleteProfileButton);
        //deletePrivilegeButton = (Button) findViewById(R.id.deletePrivilegeButton);
        viewingPrivilegeListView = (ListView) findViewById(R.id.viewingPrivilegeListView);

        initialize_viewingPrivilegeListView();

        viewingPrivilegeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Selected Item
                lastClickedPosition = position;

                // Alert
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsPage.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    // YES - Remove Item
                    public void onClick(DialogInterface dialog, int which) {

                        // Get Views Name
                        String viewersName = privilegeList_Adapter.getItem(lastClickedPosition);

                        removeView(viewersName);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Reset
                        lastClickedPosition = -1;

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    private void removeView(String viewText)
    {
        // Removed Item
        privilegeList_Adapter.remove(viewText);

        // TODO - Remove from dataBase
    }



    String[] ListElements = new String[] {
            "Dr. Scott",
            "Dr. Pepper",
            "Dr. Osborn",
            "Dr. Oz"
    };

    public void initialize_viewingPrivilegeListView()
    {
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        privilegeList_Adapter = new ArrayAdapter<String>
                (SettingsPage.this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        viewingPrivilegeListView.setAdapter(privilegeList_Adapter);

    }

    public void onClickDeleteMyProfile(View view)
    {
        // TODO - Actually Send a UserProfile Object.
        dataBase.deleteUserProfile(null);

    }

}

