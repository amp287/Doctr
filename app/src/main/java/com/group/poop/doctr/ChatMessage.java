package com.group.poop.doctr;

import java.util.Date;

/**
 * Created by amp on 11/24/17.
 */

public class ChatMessage {

    private String chatId;
    private String content;
    private String to;
    private String from;
    private long timeStamp;

    public ChatMessage(String chatId, String content, String to, String from){
        this.chatId = chatId;
        this.content = content;
        this.to = to;
        this.from = from;
        this.timeStamp = new Date().getTime();
    }

    public ChatMessage(){

    }

    //getters and setters needed by Firebase
    public String getChatId(){return chatId;}

    public String getMessageContent(){return content;}

    public String getMessageSender(){return from;}

    public String getMessageReciever(){return to;}

    public long getMessageTime(){return timeStamp;}

    public void setChatId(String id){chatId = id;}

    public void setMessageContent(String content){this.content = content;}

    public void setMessageSender(String userId) {this.from = userId;}

    public void setMessageReciever(String userId){this.to = userId;}

    public void setMessageTime(long time){this.timeStamp = time;}
}
