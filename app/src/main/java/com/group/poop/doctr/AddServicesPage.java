package com.group.poop.doctr;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.text.NumberFormat;

public class AddServicesPage extends AppCompatActivity {

    // Select Service
    private TextView serviceTextView;
    private Spinner generalPracticeSpinner;
    private Spinner servicesSpinner;

    // Hours
    private TextView hoursTextView;
    private Spinner fromTimeSpinner;
    private Spinner toTimeSpinner;

    // Cost
    private TextView costTextView;
    private EditText costAmountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        // Select Service Spinners
        generalPracticeSpinner = (Spinner) findViewById(R.id.generalPracticeSpinner);
        servicesSpinner = (Spinner) findViewById(R.id.servicesSpinner);

        // Select Hours Spinners
        hoursTextView = (TextView) findViewById(R.id.hoursTextView);
        fromTimeSpinner = (Spinner) findViewById(R.id.fromTimeSpinner);
        toTimeSpinner = (Spinner) findViewById(R.id.toTimeSpinner);

        serviceTextView = (TextView) findViewById(R.id.serviceTextView);

        // Cost
        costTextView = (TextView) findViewById(R.id.costTextView);
        costAmountEditText = (EditText) findViewById(R.id.costAmountEditText);

        generalPracticeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected Item String
                String selectedText = getSpinnerSelectedStringValue(generalPracticeSpinner);

                // Retrieve the "speciality" or services list.
                ArrayList<String> servicesList  = getServiceArrayName(selectedText);

                // Populate the services avaliable spinner
                populateServicesSpinner(servicesList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }
        });
    }

    public String getSpinnerSelectedStringValue(Spinner spinner)
    {
        return spinner.getSelectedItem().toString();
    }

    private ArrayList<String> getServiceArrayName(String generalPractice )
    {
        String[] stringArray = null;
        ArrayList<String> stringArrayList = new ArrayList<String>();

        if( generalPractice.contains(( "Primary Health Care" )))
        {
            stringArray = getResources().getStringArray(R.array.primaryhealthcare_array);

        }else if( generalPractice.contains(( "Dental Care" )))
        {
            stringArray = getResources().getStringArray(R.array.dentalcare_array);
        }else if( generalPractice.contains(( "Specialty Care" )))
        {
            stringArray = getResources().getStringArray(R.array.specialtycare_array);
        }

        // Nothing Selected
        if(stringArray == null)
        {
            return stringArrayList;
        }

        // Add each service to ArrayList
        for(String str: stringArray)
        {
            stringArrayList.add(str);
        }

        return stringArrayList;

    }
    private void populateServicesSpinner(ArrayList<String> servicesList)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, servicesList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicesSpinner.setAdapter(adapter);

//        ArrayAdapter<String> gameKindArray= new ArrayAdapter<String>(servicesSpinner, servicesList);
//        gameKindArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        gameKind.setAdapter(gameKindArray);

    }

    public void onClickAddServiceButton(View view)
    {
        boolean success = true;

        // Get Service
        String service = getSelectedService();

        // Get Hour Range
        String fromHour = getSelectedFromHour();
        String toHour = getSelectedToHour();

        if( (fromHour != null) && (toHour != null)){
            this.hoursTextView.setError(null);
        }else{
            success = false;
            this.hoursTextView.setError("Hours Not Set!");
        }

        // Validate Hour correctness
        int fromHourIndex = getHourArrayIndex(fromHour);
        int toHourIndex = getHourArrayIndex(toHour);

        if( fromHourIndex >= toHourIndex ){
            this.hoursTextView.setError("Invalid Hours! Try again.");
            success = false;
        }

        // Get Cost
        int cost = getServiceCost();

        // Validate Inputs
        if( (service == null) ||(fromHour == null) ||(toHour == null) )
        {
            success = false;
        }

        if( success )
        {
            // TODO - Store to dataBase. -> Create a data class to store services.
        }

        // TODO - If saved to database is successful, close this window, else warn the user.

    }

    // Get Service
    private String getSelectedService()
    {
        String generalPracticeString = this.generalPracticeSpinner.getSelectedItem().toString();

        String firstElementAndPromptGeneralPractice =
                (getResources().getStringArray(R.array.general_practices_array))[0];

        if( generalPracticeString.contains(firstElementAndPromptGeneralPractice))
        {
            // General Practice not selected
            serviceTextView.setError("Select a General Practice!");
            return null;
        }else{
            serviceTextView.setError(null);
        }

        String selectedService = this.servicesSpinner.getSelectedItem().toString();

        if(selectedService.contains("Select a service"))
        {
            // Service not Selected
            serviceTextView.setError("Service not Selected!");
            return null;
        }

        return selectedService;
    }

    // Get Hour Range
    private String getSelectedFromHour(){
        String selectedItem = this.fromTimeSpinner.getSelectedItem().toString();
        String firstElementAndPromptBuessnessHours =
                (getResources().getStringArray(R.array.buisnessHours_array))[0];

        if(selectedItem.contains(firstElementAndPromptBuessnessHours))
        {
            this.hoursTextView.setError("Select From Hour!");
            return null;
        }
        return selectedItem;
    }

    private String getSelectedToHour(){
        String selectedItem = this.toTimeSpinner.getSelectedItem().toString();
        String firstElementAndPromptBuessnessHours =
                (getResources().getStringArray(R.array.buisnessHours_array))[0];

        if(selectedItem.contains(firstElementAndPromptBuessnessHours))
        {
            this.hoursTextView.setError("Select To Hour!");
            return null;
        }
        return selectedItem;
    }

    private int getHourArrayIndex( String hourString ){
        // TODO - Use this to validate correctly entered from and to hours.
        // TODO - Example from 3PM - 11AM is invalid.
        int hour = 0;
        if( hourString == null ){
            return 0;
        }

        try{
            hour = ((Number)NumberFormat.getInstance().parse(hourString)).intValue();

            // Increment PM by 12 Hours - Except for "12PM"
            if( hourString.contains("PM") && (hour != 12))
            {
                hour += 12;
            }

        }catch (NumberFormatException ex)
        {
        } catch (ParseException e) {
            this.hoursTextView.setError("Parsing Error");
        }
        return hour;
    }

    private int getServiceCost()
    {
        String costValue = this.costAmountEditText.getText().toString();
        if( (costValue == null) || costValue.trim().isEmpty() )
        {
            // No Value Selected
            costTextView.setError("Must set Price!");
            return 0;
        }
        else{
            costTextView.setError(null);
        }

        // TODO - Verify non negative value
        boolean isNegative = ((costValue.indexOf('-')) >=0)? true:false;

        if( isNegative )
        {
            costTextView.setError("Cost cannot be negative!");
            return 0;
        }else {
            costTextView.setError(null);
        }

        // Parse Cost
        int cost;
        try {
            cost = Integer.parseInt(costValue);
            costTextView.setError(null);
            return cost;
        }catch (NumberFormatException ex){
            costTextView.setError("Invalid and unknown error!");
            return 0;
        }
    }
}

