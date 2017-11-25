package com.group.poop.doctr;

/**
 * Created by amp on 11/24/17.
 */

public class ChatMetaData {
    private String lastMessage;
    private long lastTimeStamp;

    public ChatMetaData(String lastMsg, long lastTimeStamp){
        lastMessage = lastMsg;
        this.lastTimeStamp = lastTimeStamp;
    }

    public ChatMetaData(){

    }
}
