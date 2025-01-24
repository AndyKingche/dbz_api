package com.api.dragonball.user.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="\"role\"")
@NamedQuery(name="RoleModel.findAll", query="SELECT role FROM RoleModel role")
public class RoleModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;

    @Column(name="role_name")
    private String rolName;

    @Column(name="rol_description")
    private String rolDescription;

    @Column(name="rol_status")
    private Boolean rolStatus;
}
