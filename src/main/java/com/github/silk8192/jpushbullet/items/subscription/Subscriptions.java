package com.github.silk8192.jpushbullet.items.subscription;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscriptions {

    @SerializedName("subscriptions")
    @Expose
    private List<Subscription> subscriptions = null;

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

}