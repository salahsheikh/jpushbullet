package com.github.silk8192.jpushbullet.items.ephemeral;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationPush {

    @SerializedName("push")
    @Expose
    private NotificationData push;
    @SerializedName("type")
    @Expose
    private String type;

    public NotificationData getPush() {
        return push;
    }

    public void setPush(NotificationData push) {
        this.push = push;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}