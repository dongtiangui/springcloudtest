package com.oauth2.oauthsecurity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "t_life_role")
@Entity
@Getter
@Setter
@ToString
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -5815490134357192001L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column
    private String role_name;

    @Column
    private String createTime;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_user",joinColumns = {@JoinColumn(name = "userId")}
    ,inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<UserEntity> userEntities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "permissionId")}
            ,inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<PermissionEntity> permissionEntityList;

}
