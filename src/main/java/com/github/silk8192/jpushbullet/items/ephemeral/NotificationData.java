package com.github.silk8192.jpushbullet.items.ephemeral;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationData {

    @SerializedName("application_name")
    @Expose
    private String applicationName;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("client_version")
    @Expose
    private Integer clientVersion;
    @SerializedName("dismissable")
    @Expose
    private Boolean dismissable;
    @SerializedName("has_root")
    @Expose
    private Boolean hasRoot;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("notification_id")
    @Expose
    private String notificationId;
    @SerializedName("notification_tag")
    @Expose
    private Object notificationTag;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("source_device_iden")
    @Expose
    private String sourceDeviceIden;
    @SerializedName("source_user_iden")
    @Expose
    private String sourceUserIden;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(Integer clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Boolean getDismissable() {
        return dismissable;
    }

    public void setDismissable(Boolean dismissable) {
        this.dismissable = dismissable;
    }

    public Boolean getHasRoot() {
        return hasRoot;
    }

    public void setHasRoot(Boolean hasRoot) {
        this.hasRoot = hasRoot;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Object getNotificationTag() {
        return notificationTag;
    }

    public void setNotificationTag(Object notificationTag) {
        this.notificationTag = notificationTag;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSourceDeviceIden() {
        return sourceDeviceIden;
    }

    public void setSourceDeviceIden(String sourceDeviceIden) {
        this.sourceDeviceIden = sourceDeviceIden;
    }

    public String getSourceUserIden() {
        return sourceUserIden;
    }

    public void setSourceUserIden(String sourceUserIden) {
        this.sourceUserIden = sourceUserIden;
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