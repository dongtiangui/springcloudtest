package com.oauth2.oauthsecurity.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "t_life_permission")
@Entity
public class PermissionEntity implements Serializable {

    private static final long serialVersionUID = 7779609709399345126L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long permissionId;

    @Column
    private String permission_name;

    @Column
    private String permission_description;

    @Column(columnDefinition = "enum('menu','button','link')")
    private String permission_type;

    @Column
    private String permission_url;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permission_role",joinColumns = {@JoinColumn(name = "role_name")}
            ,inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<RoleEntity> roleEntities;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_description() {
        return permission_description;
    }

    public void setPermission_description(String permission_description) {
        this.permission_description = permission_description;
    }

    public String getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(String permission_type) {
        this.permission_type = permission_type;
    }

    public String getPermission_url() {
        return permission_url;
    }

    public void setPermission_url(String permission_url) {
        this.permission_url = permission_url;
    }

    public List<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(List<RoleEntity> roleEntities) {
        this.roleEntities = roleEntities;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "permissionId=" + permissionId +
                ", permission_name='" + permission_name + '\'' +
                ", permission_description='" + permission_description + '\'' +
                ", permission_type='" + permission_type + '\'' +
                ", permission_url='" + permission_url + '\'' +
                ", roleEntities=" + roleEntities +
                '}';
    }
}
