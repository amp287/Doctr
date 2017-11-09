package com.group.poop.doctr;
import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import android.widget.ArrayAdapter;

import java.util.Date;
import java.util.List;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;
    public final static String EXTRA_MESSAGE = "EmailAddress";

    // Register
    private TextView mRegisterAccountTextView;
    // UI references.
    private View mProgressView;
    private View mLoginFormView;

    // Email / Password / UserName
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private AutoCompleteTextView mFullName;

    // Birthday
    private TextView mBirthdayTextView;
    private Spinner mMonthSpinner;
    private Spinner mDateSpinner;
    private Spinner mYearSpinner;

    // User Type/Gender
    private TextView mUserTypeTextView;
    private TextView mGenderTextView;

    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBase = new DataBase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Register
        mRegisterAccountTextView = (TextView) findViewById(R.id.registerAccountTextView);

        // Email / Password / UserName
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mFullName = (AutoCompleteTextView) findViewById(R.id.fullName);

        // Birthday
        mBirthdayTextView = (TextView) findViewById(R.id.birthdayTextView);
        mMonthSpinner = (Spinner) findViewById(R.id.month_spinner);
        mDateSpinner = (Spinner) findViewById(R.id.date_spinner);
        mYearSpinner = (Spinner) findViewById(R.id.year_spinner);

        // User Type/Gender
        mUserTypeTextView = (TextView) findViewById(R.id.userTypeTextView);
        mGenderTextView = (TextView) findViewById(R.id.genderTextView);
        // Setup
        addItemsToDateSpinner(31);
        addItemsToYearSpinner();

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

    public void addItemsToDateSpinner(int days) {
        if( (days > 31) || (days <= 0) )
        {
            // TODO - Handle this with and Exception
            // Set default
            days = 31;
        }
        List<String> daysInMonthList = new ArrayList<String>();

        daysInMonthList.add("Select Day");
        for(int i = 1; i <= days; i++)
        {
            daysInMonthList.add(Integer.toString(i));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, daysInMonthList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDateSpinner.setAdapter(dataAdapter);
    }

    public void addItemsToYearSpinner() {
        List<String> yearsList = new ArrayList<String>();

        yearsList.add("Select Year");
        int current_year = 2017;
        int yearRange = current_year - 100;
        for(int i = current_year; i > yearRange ; i--)
        {
            yearsList.add(Integer.toString(i));
        }

    // TODO - Create a spinner Listener Function for the mon
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, yearsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mYearSpinner.setAdapter(dataAdapter);
}

    public void onGenderRadioButtonClicked(View view)
    {
        // TODO - Not sure if anything needs to be done here.

    }

    public void onUserTypeRadioButtonClicked(View view)
    {
        // TODO - If Doctor is selected, add speciality.
        // TODO - If Patient is pressed, remove speciality.

    }

    public void onClickAddProfilePicture(View view){
        // TODO - Implement selecting a local picture for profile Picture.
    }

    public void onClickSubmitButton(View view)
    {
        boolean success = true;

        // TODO - Once the Submit Button is pressed, collect and validate all of the data
        // TODO - Send errors to each of the data fields that need correction*.

        // Email
        String emailString = mEmailView.getText().toString();

        if(emailString == null || emailString.isEmpty())
        {
            mEmailView.setError("Enter Email!");
            success = false;
        }else if( !UserProfile.validEmailAddress(emailString))        {
            mEmailView.setError("Email Not Valid!");
            success = false;
        } else if (dataBase.emailExistInDatabase(emailString.trim())) {
            mEmailView.setError("Email already registered!");
            success = false;
        } else {
            // No Issues
            mEmailView.setError(null);
        }

        // Password
        String passwordString = mPasswordView.getText().toString();

        if( passwordString == null || passwordString.trim().isEmpty())
        {
            mPasswordView.setError("Enter Password!");
            success = false;
        }else if( !UserProfile.validPassword(passwordString)){
            mPasswordView.setError("Invalid Password!");
            success = false;
        }else{
            // No Issues
            mPasswordView.setError(null);
        }

        // Full Name
        String fullNameString = mFullName.getText().toString();

        if(fullNameString == null || fullNameString.trim().isEmpty()){
            mFullName.setError("Enter Full Name!");
            success = false;
        }else if(!UserProfile.validFullName(fullNameString)){
            mFullName.setError("Invalid Full Name!");
            success = false;
        }else{
            mFullName.setError(null);
        }

        // User Type
        UserProfile.USER_TYPE user_type = getUserType();

        if(user_type == UserProfile.USER_TYPE.UNKNOWN)
        {
            // TODO - Set warning
            mUserTypeTextView.setError("User Type must be set!");
            success = false;
        }else{
            mUserTypeTextView.setError(null);
        }


        // Gender
        UserProfile.GENDER gender = getGender();

        if(gender == UserProfile.GENDER.UNKNOWN )
        {
            mGenderTextView.setError("Gender must be set!");
            success = false;
        }else{
            mGenderTextView.setError(null);
        }

        // Birthday
        Date birthDay = getBirthDate();

        if( birthDay == null )
        {
            mBirthdayTextView.setError("Birthday must be set!");
            success = false;
        }else
        {
            mBirthdayTextView.setError(null);
        }

        if( success == false )
        {
            return;
        }else
        {
            // Attempt to create the entire Profile
            UserProfile up;
            up = new UserProfile(
                    emailString,
                    passwordString,
                    fullNameString,
                    user_type,
                    gender,
                    birthDay
            );

            boolean registerSuccess = dataBase.registerUserProfile(up);

            if( registerSuccess ){
                // TODO - Maybe set a "success" message.
                Register.this.finish();
            }else{
                // TODO - Signal an Error
                mRegisterAccountTextView.setError("dataBase.registerUserProfile: Error!");

            }
        }
    }

    private String getSelectedRadioButtonStringFromGroup(@IdRes int id)
    {
        RadioGroup radioGroup = (RadioGroup) findViewById(id);

        int radioButtonID = radioGroup.getCheckedRadioButtonId();

        RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonID);

        // If no radioButton is selected
        if( radioButton == null )
        {
            return null;
        }

        return (String) radioButton.getText();
    }

    private String getSelectedSpinnerStringFromGroup(@IdRes int id)
    {
        Spinner spinner = (Spinner) findViewById(id);

        if( spinner == null)
        {
            return null;
        }

        return (String)spinner.getSelectedItem();
    }

    private UserProfile.USER_TYPE getUserType()
    {
        try {
            String selectedtext = getSelectedRadioButtonStringFromGroup(R.id.userTypeRadioGroup);
            return UserProfile.getUserType(selectedtext);
        }catch(IllegalStateException ex)
        {
            return null;
        }
    }

    private UserProfile.GENDER getGender()
    {
        String selectedtext = getSelectedRadioButtonStringFromGroup(R.id.genderRadioGroup);
        return UserProfile.getGender(selectedtext);
    }

    private Date getBirthDate()
    {
        boolean success = true;

        // Get Month
        String month = getSelectedSpinnerStringFromGroup(R.id.month_spinner);
        if( (month == null) || (month.contains("Select")))
        {
            success = false;
        }

        // Get Day
        String day = getSelectedSpinnerStringFromGroup(R.id.date_spinner);

        if( (day == null) || (day.contains("Select")) )
        {
            success = false;
        }

        // Get Year
        String year = getSelectedSpinnerStringFromGroup(R.id.year_spinner);

        if( (year == null) || (year.contains("Select")) ){
            success = false;
        }

        if(success)
        {
            return UserProfile.getBirthdate(year, month, day);
        }else {
            return null;
        }
    }

}

