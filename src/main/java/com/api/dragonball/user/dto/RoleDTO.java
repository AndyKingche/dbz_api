package com.api.dragonball.user.dto;;

public class RoleDTO {
    private Long id;
    private String rolName;
    private String rolDescription;
    private Boolean rolStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getRolDescription() {
        return rolDescription;
    }

    public void setRolDescription(String rolDescription) {
        this.rolDescription = rolDescription;
    }

    public Boolean getRolStatus() {
        return rolStatus;
    }

    public void setRolStatus(Boolean rolStatus) {
        this.rolStatus = rolStatus;
    }
}
