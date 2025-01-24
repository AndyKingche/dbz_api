package com.api.dragonball.user.dto;

public class GenderDTO {

    private Long id;
    private String genderCode;
    private String genderDescription;

    public GenderDTO() {
    }

    public GenderDTO(Long id, String genderCode, String genderDescription) {
        this.id = id;
        this.genderCode = genderCode;
        this.genderDescription = genderDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }
}
