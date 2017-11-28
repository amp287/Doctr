package com.group.poop.doctr;

import java.util.Date;

/**
 * Created by amp on 11/24/17.
 */

public class ChatMessage {

    private String content;
    private String author;
    private String authorUID;
    private long timeStamp;

    public ChatMessage(String content, String author, String authorUID){
        this.content = content;
        this.author = author;
        this.authorUID = authorUID;
        this.timeStamp = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageContent(){return content;}

    public String getMessageAuthor(){return author;}

    public long getMessageTime(){return timeStamp;}

    public String getAuthorUID(){return authorUID;}

}
