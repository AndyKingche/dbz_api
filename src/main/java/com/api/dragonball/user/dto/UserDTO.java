package com.api.dragonball.user.dto;

import com.api.dragonball.user.model.GenderModel;
import com.api.dragonball.user.model.RoleModel;

public class UserDTO {
    private Long id;
    private String identificacion;
    private String userName;
    private String userLastname;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private Boolean userStatus;
    private GenderModel gender;
    private RoleModel rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public GenderModel getGender() {
        return gender;
    }

    public void setGender(GenderModel gender) {
        this.gender = gender;
    }

    public RoleModel getRol() {
        return rol;
    }

    public void setRol(RoleModel rol) {
        this.rol = rol;
    }
}

