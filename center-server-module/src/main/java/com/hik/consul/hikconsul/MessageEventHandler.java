package com.hik.consul.hikconsul;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class MessageEventHandler {

    @Autowired
    public SocketIOServer socketIOServer;

    public ConcurrentHashMap<String,SocketIOClient> concurrentHashMap = new ConcurrentHashMap<>();

    @OnConnect
    public void conn(SocketIOClient client){
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        //存储SocketIOClient，用于发送消息
        concurrentHashMap.put(mac, client);
        //回发消息
        client.sendEvent("message", "onConnect back");
        log.info("客户端:" + client.getSessionId() + "已连接,mac=" + mac);
    }
    @OnDisconnect
    public void OnDisconnect(SocketIOClient client){
        log.info("客户端:" + client.getSessionId() + "断开连接");
    }

    @OnEvent("messageevent")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Message data){
        log.info("发来消息：" + data);
        //回发消息
        client.sendEvent("messageevent", "我是服务器都安发送的信息");
        //广播消息
        sendBroadcast();
    }
    public void sendBroadcast() {
        for (SocketIOClient client : concurrentHashMap.values()) {
            if (client.isChannelOpen()) {
                client.sendEvent("Broadcast", "当前时间" + System.currentTimeMillis());
            }
        }

    }
}
