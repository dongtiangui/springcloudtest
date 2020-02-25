package com.oauth2.oauthsecurity.domain.system;

import com.oauth2.oauthsecurity.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 授权记录表
 */
@Table(name = "oauth_approvals")
@Entity
@Getter
@Setter
@ToString
public class OauthApprovals implements Serializable {
    private static final long serialVersionUID = -7659888395149364703L;
    @Id
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity userId;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private OauthClientEntity client_id;
    @Column
    private String scope;
    @Column
    private Boolean status;
    @Column
    private Date expireSat;
    @Column
    private Date lastModifiedat;
}
