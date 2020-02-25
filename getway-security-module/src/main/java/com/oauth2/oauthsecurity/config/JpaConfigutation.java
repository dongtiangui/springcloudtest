package com.oauth2.oauthsecurity.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.oauth2.**.dao")
@EntityScan(basePackages = "com.oauth2.**.domain")
public class JpaConfigutation {

    @Bean
    public PersistenceExceptionTranslationPostProcessor
    persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
