package com.integral.boot.configuration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.destination.DestinationResolver;
import javax.jms.*;
import static com.integral.boot.configuration.JmsUtil.*;
@Configuration
public class JmsTemplateConfiguration {
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }
    @Bean(name = "topic")
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }
    private final ObjectProvider<DestinationResolver> destinationResolver;
    private final ObjectProvider<MessageConverter> messageConverter;
    @Autowired
    public JmsTemplateConfiguration(ObjectProvider<DestinationResolver> destinationResolver,
                                    ObjectProvider<MessageConverter> messageConverter) {
        this.destinationResolver = destinationResolver;
        this.messageConverter = messageConverter;
    }
    @Bean(name = "jmsQueueTemplate")
    public JmsTemplate jmsQueueTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(isPubSubDomain);
        DestinationResolver destinationResolver = this.destinationResolver.getIfUnique();
        if (destinationResolver != null) { jmsTemplate.setDestinationResolver(destinationResolver); }
        MessageConverter messageConverter =  this.messageConverter.getIfUnique();
        if (messageConverter != null) { jmsTemplate.setMessageConverter(messageConverter); }
        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return jmsTemplate;
    }
    @Bean(name = "jmsTopicTemplate")
    public JmsTemplate jmsTopicTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(isPubSubDomain);
        //deliveryMode, priority, timeToLive 的开关，要生效，必须配置为true，默认false
        jmsTemplate.setExplicitQosEnabled(true);
        //DeliveryMode.NON_PERSISTENT=1:非持久 ; DeliveryMode.PERSISTENT=2:持久
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        //默认不开启事务
        System.out.println("是否开启事务"+jmsTemplate.isSessionTransacted());
        //如果session带有事务，并且事务成功提交，则消息被自动签收。如果事务回滚，则消息会被再次传送。
        //jmsTemplate.setSessionTransacted(true);
        //不带事务的session的签收方式，取决于session的配置。
        //默认消息确认方式为1，即AUTO_ACKNOWLEDGE
        System.out.println("是否消息确认方式"+jmsTemplate.getSessionAcknowledgeMode());
        //消息的应答方式，需要手动确认，此时SessionTransacted必须被设置为false，且为Session.CLIENT_ACKNOWLEDGE模式
        //Session.AUTO_ACKNOWLEDGE  消息自动签收
        //Session.CLIENT_ACKNOWLEDGE  客户端调用acknowledge方法手动签收
        //Session.DUPS_OK_ACKNOWLEDGE 不必必须签收，消息可能会重复发送
        jmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return jmsTemplate;
    }
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }
}
