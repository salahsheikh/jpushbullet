package com.github.silk8192.jpushbullet.items.chat;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chats {

    @SerializedName("chats")
    @Expose
    private List<Chat> chats = null;

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

}