package com.oauth2.oauthsecurity.dao;

import com.oauth2.oauthsecurity.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
