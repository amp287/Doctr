package com.group.poop.doctr;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by mosborn1987 on 11/2/2017.
 */

public class UserProfile {

    // TODO - Need to add a Profile Image.

    // Email
    String email;

    // Password
    String password;

    // User Name
    private String firstName;
    private String middleName;
    private String lastName;

    // Member Variables
    private USER_TYPE user_type;
    private GENDER gender;
    private Date birthday;



    // Constructor
    public UserProfile( String email, String password, String fullName, USER_TYPE user_type, GENDER gender, Date birthday)
    {
        // TODO - Throw an exception if any of the strings are null or enums "UNKNOW"
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.gender = gender;
        this.birthday = validateBirthdate(birthday);
        parseFullName(fullName);
    }

    private void parseFullName( String fullName )
    {
        // TODO - Parse Out First, Middle and Last Name.
        fullName = fullName.trim();

        String[] splited = fullName.split("\\s+");

        // An Assumption is made that if there are two names,
        // then the first is the first name and second is the Last name.
        // Else if three names, first, middle and last.

        if(splited.length == 2)
        {
            this.firstName = splited[0];
            this.middleName = "";
            this.lastName = splited[1];

        }else if(splited.length == 3)
        {
            this.firstName = splited[0];
            this.middleName = splited[1];
            this.lastName = splited[2];
        }else {
            // TODO - Throw Exception!!
        }

    }

    public boolean isValid()
    {
        // TODO - Add Logic Here.
        return true;
    }

    // Enums
    public enum USER_TYPE{
        PATIENT,
        DOCTOR,
        UNKNOWN
    }

    public enum GENDER{
        MALE,
        FEMALE,
        UNKNOWN
    }

    public enum MONTHS{
        UNKNOWN,
        January,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December
    }

    public static GENDER getGender(String gender)
    {
        if(gender == null)
        {
            return GENDER.UNKNOWN;
        }else if(gender.equals("Male"))
        {
            return GENDER.MALE;
        }else if( gender.equals("Female"))
        {
            return GENDER.FEMALE;
        }
        else {
            // TODO - Throw an exception here!
            return GENDER.UNKNOWN;
        }
    }

    public static USER_TYPE getUserType(String userType)
    {
        if(userType == null)
        {
            return USER_TYPE.UNKNOWN;
        }else if(userType.equals("Patient"))
        {
            return UserProfile.USER_TYPE.PATIENT;
        }else if( userType.equals("Doctor"))
        {
            return UserProfile.USER_TYPE.DOCTOR;
        }
        else {
            // TODO - Throw an exception here!
            return USER_TYPE.UNKNOWN;
        }
    }

    private Date validateBirthdate(Date birthdate)
    {
        // TODO - Validate or Throw Exception!
        return birthdate;
    }

    public static Date getBirthdate(String year, String month, String day ) {
        int intYear;
        int intMonth;
        int intDay;

        try {
            intYear = Integer.parseInt(year);
            intMonth = getMonthNumber(month);
            intDay = Integer.parseInt(day);

            return getBirthdate(intYear, intMonth, intDay);
        }catch (NumberFormatException ex)
        {
            return null;
        }
    }

    private static int getMonthNumber(String monthName) {
        int i = 0;

        for (MONTHS month : MONTHS.values()) {
            String monthString = month.name().toString();
            if(monthName.contains(monthString) )
            {
                return month.ordinal();
            }
        }

        return MONTHS.UNKNOWN.ordinal();
    }

    public static Date getBirthdate(int year, int month, int day) {
        // TODO - Validate the input parameters!!

        // all int should be greater than 0
        if (year > 0 && month > 0 && day > 0) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month-1 , day, 0, 0, 0);

            return cal.getTime(); // this returns a Date
        } else {
            // TODO - Throw an exception!
            // TODO - OR set birthday to a default value...
            return null;
        }
    }

    public static boolean validEmailAddress(String email)
    {
        if(email == null || email.trim().isEmpty())
        {
            return false;
        }

        if(!email.contains("@"))
        {
            return false;
        }

        // TODO - Write More Logic (e.g. contains(".gmail"...)
        return true;//?
    }

    public static boolean validPassword(String password)
    {
        if(password == null || password.trim().isEmpty())
        {
            return false;
        }

        if(password.length() > 4 ){
            return true;
        }
        return false;
    }

    public static boolean validFullName(String name)
    {
        // TODO - Make sure that there are at least two valid names.
        if(name == null || name.trim().isEmpty())
        {
            return false;
        }

        // TODO - Add more logic here.

        return true;
    }

}
