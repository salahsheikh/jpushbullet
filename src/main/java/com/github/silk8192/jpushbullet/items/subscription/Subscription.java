package com.github.silk8192.jpushbullet.items.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscription {

    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("channel")
    @Expose
    private Channel channel;
    @SerializedName("created")
    @Expose
    private double created;
    @SerializedName("iden")
    @Expose
    private String iden;
    @SerializedName("modified")
    @Expose
    private double modified;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public double getModified() {
        return modified;
    }

    public void setModified(double modified) {
        this.modified = modified;
    }

}