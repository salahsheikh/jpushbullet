package com.github.silk8192.jpushbullet.items.ephemeral;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SMSPush {

    @SerializedName("push")
    @Expose
    private SMSData push;
    @SerializedName("type")
    @Expose
    private String type;

    public SMSData getPush() {
        return push;
    }

    public void setPush(SMSData push) {
        this.push = push;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}