package com.github.silk8192.jpushbullet.items.push;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pushes {

    @SerializedName("pushes")
    @Expose
    private List<Push> pushes = null;

    public List<Push> getPushes() {
        return pushes;
    }

    public void setPushes(List<Push> pushes) {
        this.pushes = pushes;
    }

}