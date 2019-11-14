package com.oauth2.oauthsecurity.domain;

import lombok.Data;

import java.util.List;

@Data
/*
  login success return
 */
public class LoginSuccessVO {

    private Long userId;

    private String username;

    private List<String> roles;
}
