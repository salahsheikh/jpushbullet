package com.github.silk8192.jpushbullet.items.push;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileUploadRequest {

    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("file_type")
    @Expose
    private String fileType;
    @SerializedName("file_url")
    @Expose
    private String fileUrl;
    @SerializedName("upload_url")
    @Expose
    private String uploadUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

}