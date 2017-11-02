package com.group.poop.doctr;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;


public class Register extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;
    public final static String EXTRA_MESSAGE = "EmailAddress";

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBase = new DataBase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        // Get The email address from Login Form
        String passedEmail;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                passedEmail= null;
            } else {
                passedEmail= extras.getString(EXTRA_MESSAGE);
            }
        } else {
            passedEmail= (String) savedInstanceState.getSerializable(EXTRA_MESSAGE);
        }

        // Set Email address
        mEmailView.setText(passedEmail);

    }

    public void onGenderRadioButtonClicked(View view)
    {
        // TODO - Not sure if anything needs to be done here.

    }

}

