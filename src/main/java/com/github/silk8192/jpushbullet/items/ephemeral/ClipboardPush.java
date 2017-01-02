package com.github.silk8192.jpushbullet.items.ephemeral;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClipboardPush {

    @SerializedName("push")
    @Expose
    private ClipboardData push;
    @SerializedName("type")
    @Expose
    private String type;

    public ClipboardData getPush() {
        return push;
    }

    public void setPush(ClipboardData push) {
        this.push = push;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
