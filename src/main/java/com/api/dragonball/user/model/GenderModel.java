package com.api.dragonball.user.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="gender")
@NamedQuery(name="GenderModel.findAll", query="SELECT gender FROM GenderModel gender")
public class GenderModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gender_id")
    private Long id;

    @Column(name="gender_code")
    private String genderCode;

    @Column(name="gender_description")
    private String genderDescription;

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
