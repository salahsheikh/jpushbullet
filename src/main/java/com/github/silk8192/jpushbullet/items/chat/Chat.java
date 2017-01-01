package com.github.silk8192.jpushbullet.items.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chat {

    @SerializedName("active")
    @Expose
    private boolean active;
    @SerializedName("created")
    @Expose
    private double created;
    @SerializedName("iden")
    @Expose
    private String iden;
    @SerializedName("modified")
    @Expose
    private double modified;
    @SerializedName("with")
    @Expose
    private With with;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCreated() {
        return created;
    }

    public void setCreated(double created) {
        this.created = created;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public double getModified() {
        return modified;
    }

    public void setModified(double modified) {
        this.modified = modified;
    }

    private With getWith() {
        return with;
    }

    private void setWith(With with) {
        this.with = with;
    }

    public String getWithEmail() {
        return this.with.getEmail();
    }

    public void setWithEmail(String email) {
        this.with.setEmail(email);
    }

    public String getWithEmailNormalized() {
        return this.with.getEmailNormalized();
    }

    public void setWithEmailNormalized(String emailNormalized) {
        this.with.setEmailNormalized(emailNormalized);
    }

    public String getWithIden() {
        return this.with.getIden();
    }

    public void setWithIden(String iden) {
        this.with.setIden(iden);
    }

    public String getImageUrl() {
        return this.with.getImageUrl();
    }

    public void setWithImageUrl(String imageUrl) {
        this.with.setImageUrl(imageUrl);
    }

    public String getWithName() {
        return this.with.getName();
    }

    public void setWithName(String name) {
        this.with.setName(name);
    }

    public String getWithType() {
        return this.with.getType();
    }

    public void setWithType(String type) {
        this.with.setType(type);
    }

    private class With {

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
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("type")
        @Expose
        private String type;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

}