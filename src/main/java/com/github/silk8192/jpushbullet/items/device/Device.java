package com.github.silk8192.jpushbullet.items.device;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("app_version")
    @Expose
    private Integer appVersion;

    @SerializedName("created")
    @Expose
    private Double created;

    @SerializedName("iden")
    @Expose
    private String iden;

    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;

    @SerializedName("model")
    @Expose
    private String model;

    @SerializedName("modified")
    @Expose
    private Double modified;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("push_token")
    @Expose
    private String pushToken;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public Double getCreated() {
        return created;
    }

    public void setCreated(Double created) {
        this.created = created;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getModified() {
        return modified;
    }

    public void setModified(Double modified) {
        this.modified = modified;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    @Override
    public String toString() {
        return "Device{" +
                "active=" + active +
                ", appVersion=" + appVersion +
                ", created=" + created +
                ", iden='" + iden + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", modified=" + modified +
                ", nickname='" + nickname + '\'' +
                ", pushToken='" + pushToken + '\'' +
                '}';
    }

}