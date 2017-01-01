package com.github.silk8192.jpushbullet.items.device;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Devices {

    @SerializedName("devices")
    @Expose
    private List<Device> devices = null;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

}