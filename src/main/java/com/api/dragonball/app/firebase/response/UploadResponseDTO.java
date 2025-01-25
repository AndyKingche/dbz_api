package com.api.dragonball.app.firebase.response;

public class UploadResponseDTO {
    private String link;
    private boolean status;

    public UploadResponseDTO() {
    }

    public UploadResponseDTO(String link, boolean status) {
        this.link = link;
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
