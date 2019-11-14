package com.oauth2.oauthsecurity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_life_user")
@Cacheable
@ToString
@Getter
@Setter
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 217170257737503128L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String version;

//    实体加载方式 fetch = FetchType.LAZY
    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "User_role",joinColumns = {@JoinColumn(name = "userId",referencedColumnName = "userId")}
    ,inverseJoinColumns = {@JoinColumn(name = "roleId",referencedColumnName = "roleId")})
    //people中的roleId字段参考
    private List<RoleEntity> roleEntity;

    @Column
//    格式化日期
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 用户头像
     */
    @Column
    private String head_portrait;

    @Column
    private String mobile;

    @Column
    private String email;
    /**
     * 登录次数
     */
    @Column
    private Integer count;


}
