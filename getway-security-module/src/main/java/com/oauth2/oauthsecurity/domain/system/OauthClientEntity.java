package com.oauth2.oauthsecurity.domain.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oauth_client_details")
@Getter
@Setter
@ToString
public class OauthClientEntity implements Serializable {
    private static final long serialVersionUID = -6841719090465570830L;
    @Id
    @Column(length = 64)
    private String client_id;
    @Column
    private String resource_ids;
    @Column
    private String client_secret;
    @Column
    private String scope;
    @Column
    private String authorized_grant_types;
    @Column
    private String web_server_redirect_uri;
    @Column
    private String authorities;
    @Column
    private Integer access_token_validity;
    @Column
    private Integer refresh_token_validity;
    @Column
    private String additional_information;
    @Column
    private String autoapprove;

}
