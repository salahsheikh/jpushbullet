package com.github.silk8192.jpushbullet.items.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentPush {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("created")
    @Expose
    private Double created;
    @SerializedName("modified")
    @Expose
    private Double modified;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("dismissed")
    @Expose
    private Boolean dismissed;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("sender_name")
    @Expose
    private String senderName;
    @SerializedName("channel_iden")
    @Expose
    private String channelIden;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("url")
    @Expose
    private String url;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getCreated() {
        return created;
    }

    public void setCreated(Double created) {
        this.created = created;
    }

    public Double getModified() {
        return modified;
    }

    public void setModified(Double modified) {
        this.modified = modified;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDismissed() {
        return dismissed;
    }

    public void setDismissed(Boolean dismissed) {
        this.dismissed = dismissed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getChannelIden() {
        return channelIden;
    }

    public void setChannelIden(String channelIden) {
        this.channelIden = channelIden;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
