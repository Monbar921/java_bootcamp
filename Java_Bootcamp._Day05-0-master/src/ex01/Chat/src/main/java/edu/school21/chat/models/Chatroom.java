package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private long id;
    private String name;
    private User owner;
    private List<Message> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && Objects.equals(name, chatroom.name) && Objects.equals(owner, chatroom.owner) && Objects.equals(messages, chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messages);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ",name=“" + name + "”" +
                ",creator=" + owner +
                ",messages=" + messages +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
