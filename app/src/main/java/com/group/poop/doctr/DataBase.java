package com.group.poop.doctr;

/**
 * Created by mosborn1987 on 11/2/2017.
 */

public class DataBase {

    // Constructor
    public DataBase()
    {

    }

    public boolean registerUserProfile(UserProfile up)
    {
        // TODO - Register User Profile Logic.
        return true;
    }

    public boolean deleteUserProfile(UserProfile up)
    {
        // TODO - Write the logic to delete and verify deletion.
        return true;
    }

    public boolean emailExistInDatabase(String email )
    {
        String e = "mosborn1987@gmail.com";
        return email.equals(e);
    }

    public boolean emailAndPasswordAreValid(String email, String password){
        String e = "mosborn1987@gmail.com";
        String p = "Words123!";

        return e.equals(email) && p.equals(password);
    }

    public credentialStatus validateCredentials(String email, String password)
    {
        // TODO - Database implementation code needs to be written here
        // TODO - Validate email and password from an actual database.
        boolean emailExist = emailExistInDatabase(email);
        boolean passwordCorrect = emailAndPasswordAreValid(email, password);

        if( (email == null) || (password == null))
        {
            return credentialStatus.nullStringReference;
        }

        if( !emailExist )
        {
            return credentialStatus.emailDoesNotExistInDataBase;
        }

        if( emailExist && !passwordCorrect )
        {
            return credentialStatus.emailExistPasswordWrong;
        }

        if( emailExist && passwordCorrect )
        {
            return credentialStatus.validCredentials;
        }

        return credentialStatus.unKnownError;
    }

    public enum credentialStatus
    {
        emailDoesNotExistInDataBase,
        emailExistPasswordWrong,
        validCredentials,
        nullStringReference,
        unKnownError
    }

}
