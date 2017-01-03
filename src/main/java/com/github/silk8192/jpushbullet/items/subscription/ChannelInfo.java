package com.github.silk8192.jpushbullet.items.subscription;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelInfo {

    @SerializedName("iden")
    @Expose
    private String iden;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("subscriber_count")
    @Expose
    private Integer subscriberCount;
    @SerializedName("recent_pushes")
    @Expose
    private List<RecentPush> recentPushes = null;

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(Integer subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public List<RecentPush> getRecentPushes() {
        return recentPushes;
    }

    public void setRecentPushes(List<RecentPush> recentPushes) {
        this.recentPushes = recentPushes;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
            "iden='" + iden + '\'' +
            ", name='" + name + '\'' +
            ", tag='" + tag + '\'' +
            ", description='" + description + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", subscriberCount=" + subscriberCount +
            ", recentPushes=" + recentPushes +
            '}';
    }
}
