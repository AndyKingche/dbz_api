package com.api.dragonball.user.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="\"user\"")
@NamedQuery(name="UserModel.findAll", query="SELECT user FROM UserModel user")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="identification")
    private String userIdentification;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_lastname")
    private String userLastname;

    @Column(name="user_email")
    private String userEmail;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_address")
    private String userAddress;

    @Column(name="user_status")
    private Boolean userStatus;

    @ManyToOne
    @JoinColumn(name = "user_genderid")
    private GenderModel gender;

    @ManyToOne
    @JoinColumn(name = "user_roleid")
    private RoleModel rol;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserIdentification() {
        return userIdentification;
    }

    public void setUserIdentification(String userIdentification) {
        this.userIdentification = userIdentification;
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
