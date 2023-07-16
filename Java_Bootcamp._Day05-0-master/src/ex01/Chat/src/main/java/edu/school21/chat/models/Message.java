package edu.school21.chat.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String text;
    private Timestamp dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(author, message.author) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, dateTime);
    }

    @Override
    public String toString() {
        return "Message : {" +
                "\n  id=" + id +
                ",\n  author=" + author +
                ",\n  room=" + room +
                ",\n  text=“" + text + "”" +
                ",\n  dateTime=" + new SimpleDateFormat("MM/dd/yy HH:mm").format(dateTime)+
                "\n}";
    }

    public Message(long id){
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }
}
