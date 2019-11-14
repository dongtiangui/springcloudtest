package com.member.bootstrap.model.system;

import org.apache.shiro.session.Session;

import java.io.Serializable;

public class SessionModel implements Serializable {
    private static final long serialVersionUID = 8034642447355518269L;

    private Long sessionId;

    private Session session;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
