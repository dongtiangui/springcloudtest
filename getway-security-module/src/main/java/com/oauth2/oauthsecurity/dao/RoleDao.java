package com.oauth2.oauthsecurity.dao;

import com.oauth2.oauthsecurity.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity,Long> {
}
