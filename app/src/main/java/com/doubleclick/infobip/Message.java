package com.doubleclick.infobip;

public class Message {

    private Content content;
    private String from;
    private String to;

    public Message(Content content, String from, String to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }
}
