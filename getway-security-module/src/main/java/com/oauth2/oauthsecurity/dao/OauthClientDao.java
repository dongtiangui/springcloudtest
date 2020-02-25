package com.oauth2.oauthsecurity.dao;

import com.oauth2.oauthsecurity.domain.system.OauthClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDao extends JpaRepository<OauthClientEntity,Long> {
}
