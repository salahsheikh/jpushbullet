package com.shakethat.jpushbullet.net;

/**
 * {
 * "iden": "ubdpjAkaGXvUl2",
 * "type": "link",
 * "active": true,
 * "dismissed": false,
 * "created": 1.39934925E9,
 * "modified": 1.39934925E9,
 * "title": "Pushbullet",
 * "body": "Documenting our API",
 * "url": "http://docs.pushbullet.com",
 * "target_device_iden": "ubddjAy95rgBxc",
 * "sender_iden": "ubd",
 * "sender_email": "ryan@pushbullet.com",
 * "sender_email_normalized": "ryan@pushbullet.com",
 * "receiver_iden": "ubd",
 * "receiver_email": "ryan@pushbullet.com",
 * "receiver_email_normalized": "ryan@pushbullet.com"
 * }
 * Created by Goat on 9/14/2014.
 */
public class Push {

    private String iden;
    private String type;
    private Boolean active;
    private Boolean dismissed;
    private Number created;
    private Number modified;
    private String title;
    private String body;
    private String url;
    private String target_device_iden;
    private String sender_iden;
    private String sender_email;
    private String sender_email_normalized;
    private String receiver_iden;
    private String receiver_email;
    private String receiver_email_normalized;

    public String getSender_email() {
        return sender_email;
    }

    public void setSender_email(String sender_email) {
        this.sender_email = sender_email;
    }

    public String getSender_iden() {
        return sender_iden;
    }

    public void setSender_iden(String sender_iden) {
        this.sender_iden = sender_iden;
    }

    public String getReceiver_iden() {
        return receiver_iden;
    }

    public void setReceiver_iden(String receiver_iden) {
        this.receiver_iden = receiver_iden;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public Number getCreated() {
        return created;
    }

    public void setCreated(Number created) {
        this.created = created;
    }

    public Number getModified() {
        return modified;
    }

    public void setModified(Number modified) {
        this.modified = modified;
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

    public String getTarget_device_iden() {
        return target_device_iden;
    }

    public void setTarget_device_iden(String target_device_iden) {
        this.target_device_iden = target_device_iden;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReceiver_email(String receiver_email) {
        this.receiver_email = receiver_email;
    }
}
