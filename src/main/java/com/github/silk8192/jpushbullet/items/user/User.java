package com.github.silk8192.jpushbullet.items.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("created")
    @Expose
    private double created;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_normalized")
    @Expose
    private String emailNormalized;
    @SerializedName("iden")
    @Expose
    private String iden;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("max_upload_size")
    @Expose
    private double maxUploadSize;
    @SerializedName("modified")
    @Expose
    private double modified;
    @SerializedName("name")
    @Expose
    private String name;

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailNormalized() {
        return emailNormalized;
    }

    public void setEmailNormalized(String emailNormalized) {
        this.emailNormalized = emailNormalized;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(double maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public double getModified() {
        return modified;
    }

    public void setModified(double modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
