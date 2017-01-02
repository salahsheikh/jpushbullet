package com.github.silk8192.jpushbullet.items.ephemeral;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SMSData {

    @SerializedName("conversation_iden")
    @Expose
    private String conversationIden;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("source_user_iden")
    @Expose
    private String sourceUserIden;
    @SerializedName("target_device_iden")
    @Expose
    private String targetDeviceIden;
    @SerializedName("type")
    @Expose
    private String type;

    public String getConversationIden() {
        return conversationIden;
    }

    public void setConversationIden(String conversationIden) {
        this.conversationIden = conversationIden;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSourceUserIden() {
        return sourceUserIden;
    }

    public void setSourceUserIden(String sourceUserIden) {
        this.sourceUserIden = sourceUserIden;
    }

    public String getTargetDeviceIden() {
        return targetDeviceIden;
    }

    public void setTargetDeviceIden(String targetDeviceIden) {
        this.targetDeviceIden = targetDeviceIden;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}