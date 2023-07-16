package edu.school21.chat.models;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> socializedRooms;
}
