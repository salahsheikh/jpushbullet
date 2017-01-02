package com.github.silk8192.jpushbullet.items.ephemeral;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClipboardData {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("source_device_iden")
    @Expose
    private String sourceDeviceIden;
    @SerializedName("source_user_iden")
    @Expose
    private String sourceUserIden;
    @SerializedName("type")
    @Expose
    private String type;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
