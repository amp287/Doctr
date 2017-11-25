package com.group.poop.doctr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by amp on 11/24/17.
 */

public class ChatPage extends AppCompatActivity {

    private DataBase dataBase;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() == null){
            //TODO: go to login activity
        }



    }

}
