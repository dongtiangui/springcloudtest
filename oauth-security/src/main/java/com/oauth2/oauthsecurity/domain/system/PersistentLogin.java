package com.oauth2.oauthsecurity.domain.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "persistent_logins")
@Entity
@Getter
@Setter
@ToString
public class PersistentLogin {
    @Id
    private String series;

    @Column
    private String username;

    @Column
    private String token;

    @Column
    @DateTimeFormat(pattern = "yyyy:MM:dd:mm:ss")
    private Date last_used;
}
