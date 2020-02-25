package com.oauth2.oauthsecurity.domain.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import javax.persistence.*;
import java.util.Date;

/**
 * 存储token值
 */
@Table(name = "oauth_code")
@Entity
@Getter
@Setter
@ToString
public class OauthcCode {
    @Id
    private String code;
    @Lob
    @Column(columnDefinition="text")
    private Authentication authentication;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date create_time;

}
