package com.oauth2.oauthsecurity.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * session监听器
 */
@WebListener
@Configuration
public class HttpSessionEventPublisherLocal extends HttpSessionEventPublisher {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("session创建"+event.getSession().getId());
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        simpMessagingTemplate.convertAndSend("/topic/getResponse",session.getId());
    }
}
