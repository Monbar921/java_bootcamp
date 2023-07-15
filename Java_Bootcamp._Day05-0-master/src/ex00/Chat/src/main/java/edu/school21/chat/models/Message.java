package edu.school21.chat.models;

import java.sql.Timestamp;

public class Message {
    private int id;
    private String author;
    private Chatroom room;
    private String text;
    private Timestamp dateTime;
}
