package com.github.silk8192.jpushbullet.items.push;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Push {

    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created")
    @Expose
    private double created;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("dismissed")
    @Expose
    private boolean dismissed;
    @SerializedName("iden")
    @Expose
    private String iden;
    @SerializedName("modified")
    @Expose
    private double modified;
    @SerializedName("receiver_email")
    @Expose
    private String receiverEmail;
    @SerializedName("receiver_email_normalized")
    @Expose
    private String receiverEmailNormalized;
    @SerializedName("receiver_iden")
    @Expose
    private String receiverIden;
    @SerializedName("sender_email")
    @Expose
    private String senderEmail;
    @SerializedName("sender_email_normalized")
    @Expose
    private String senderEmailNormalized;
    @SerializedName("sender_iden")
    @Expose
    private String senderIden;
    @SerializedName("sender_name")
    @Expose
    private String senderName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isDismissed() {
        return dismissed;
    }

    public void setDismissed(boolean dismissed) {
        this.dismissed = dismissed;
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

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverEmailNormalized() {
        return receiverEmailNormalized;
    }

    public void setReceiverEmailNormalized(String receiverEmailNormalized) {
        this.receiverEmailNormalized = receiverEmailNormalized;
    }

    public String getReceiverIden() {
        return receiverIden;
    }

    public void setReceiverIden(String receiverIden) {
        this.receiverIden = receiverIden;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderEmailNormalized() {
        return senderEmailNormalized;
    }

    public void setSenderEmailNormalized(String senderEmailNormalized) {
        this.senderEmailNormalized = senderEmailNormalized;
    }

    public String getSenderIden() {
        return senderIden;
    }

    public void setSenderIden(String senderIden) {
        this.senderIden = senderIden;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}