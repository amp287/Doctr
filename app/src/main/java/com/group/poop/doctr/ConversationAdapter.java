package com.group.poop.doctr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AMP on 11/28/2017.
 */

//used to populate conversation_list
public class ConversationAdapter extends ArrayAdapter<Conversation> {
    private String userNameToShow;
    private String lastMessage;
    private Date time;

    public ConversationAdapter(Context context, ArrayList<Conversation> metaData, String userNameToShow, String lastMessage, long time) {
        super(context, R.layout.conversation, metaData);
        this.userNameToShow = userNameToShow;
        this.lastMessage = lastMessage;
        this.time = new Date(time);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Conversation metaData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.conversation, parent, false);
        }

        TextView user = (TextView) convertView.findViewById(R.id.conversation_user);
        TextView time = (TextView) convertView.findViewById(R.id.conversation_time);
        TextView message = (TextView) convertView.findViewById(R.id.conversation_last_message);

        user.setText(userNameToShow);
        time.setText(time.toString());
        message.setText(lastMessage);

        return convertView;
    }
}