/*
package com.group.poop.doctr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

//import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


/**
 * Created by amp on 11/24/17.
 */
/*
public class ChatPage extends AppCompatActivity {

    private DataBase dataBase;

    private String chatId;

    private FirebaseAuth mAuth;

    private DatabaseReference ref;

    private FirebaseUser user;

    //private FirebaseListAdapter<ChatMessage> adapter;

    private ArrayList<ChatMessage> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String recipient = "DUMMY";

        setContentView(R.layout.activity_chat_page);
        mAuth = FirebaseAuth.getInstance();

        if((user = mAuth.getCurrentUser()) == null){
            //TODO: go to login activity
        }

        Intent intent = getIntent();

        chatId = intent.getStringExtra("CHAT_ID");

        ref = FirebaseDatabase.getInstance().getReference().child("Messages");

        messages = new ArrayList<ChatMessage>();

        Query messages = ref.orderByChild("timeStamp");

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.send);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);

                ref.push().setValue(new ChatMessage(
                chatId, input.getText().toString(), recipient, user.getUid()
                ));

                // Clear the input
                input.setText("");
            }
        });
        displayMessages();
    }

    private void displayMessages() {
        ListView messages = (ListView)findViewById(R.id.messages_list);

        adapter =  new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.message_test, ref){
            @Override
            protected void populateView(View v, ChatMessage model, int position){
                TextView msg = (TextView)v.findViewById(R.id.message_text);
                TextView from = (TextView)v.findViewById(R.id.message_user);
                TextView time = (TextView)v.findViewById(R.id.message_time);

                msg.setText(model.getMessageContent());
                from.setText(model.getMessageContent());
                time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        messages.setAdapter(adapter);
    }

}
*/