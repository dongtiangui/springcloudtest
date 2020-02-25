package com.oauth2.oauthsecurity.dao;

import com.oauth2.oauthsecurity.domain.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<PermissionEntity,Long> {
}
