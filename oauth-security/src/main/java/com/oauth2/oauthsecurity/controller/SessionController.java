//package com.oauth2.oauthsecurity.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.session.SessionInformation;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//@ServerEndpoint(value = "/websocket/{ip}")
//@Slf4j
//public class SessionController {
//
////   总人数
//    private static AtomicInteger count = new AtomicInteger(0);
//
//    private String IP;
//
////    @Autowired
////    private SessionRegistry sessionRegistry;
//// session集合
//
//    private static Map<String,Object> session = new ConcurrentHashMap<>();
//
//    public Map<String,Object> sessionInfo(){
//       List<SessionInformation> sessions = sessionRegistry.getAllSessions(sessionRegistry.getAllPrincipals(), true);
//
//       return session;
//   }
//   @OnOpen
//   public void open(){
//
//   }
//
//   @OnClose
//   public void close(){
//
//   }
//
//   @OnMessage
//   public void onMessage(String message, Session session){
//
//
//   }
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("webSocket发生错误！IP：{}",IP);
//        error.printStackTrace();
//    }
//}
