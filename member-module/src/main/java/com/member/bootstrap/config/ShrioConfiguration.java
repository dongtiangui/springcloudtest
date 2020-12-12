package com.member.bootstrap.config;
import com.member.bootstrap.realms.JdbcRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ShrioConfiguration {

    @Autowired
    private EnterpriseCacheSessionDAO enterpriseCacheSessionDAO;

    @Value("${server.servlet.session.timeout}")
    private int tomcatTimeout;
    private static final String EHCACHE_CONFIG = "classpath:ehcache.xml";
    @Bean
    public SecurityManager securityManager(EhCacheManager ehCacheManager){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSessionManager(sessionManager());
        manager.setRealm(jdbcRealm());
        manager.setCacheManager(ehCacheManager);
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }
    @Bean
    public JdbcRealm jdbcRealm(){
        return new JdbcRealm();
    }
    @Bean
    public SessionManager sessionManager(){
        DefaultSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionValidationInterval(100000);
        manager.setDeleteInvalidSessions(true);
        manager.setGlobalSessionTimeout(tomcatTimeout*1000);
        manager.setSessionDAO(enterpriseCacheSessionDAO);
        manager.setSessionValidationScheduler(new ExecutorServiceSessionValidationScheduler());
        manager.setSessionValidationSchedulerEnabled(true);
        return manager;
    }

    @Bean
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(){
        EnterpriseCacheSessionDAO dao = new EnterpriseCacheSessionDAO();
        dao.setSessionIdGenerator(javaUuidSessionIdGenerator());
        dao.setActiveSessionsCacheName("shiro-activeSessionCache");
        return dao;
    }
    public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager manager = new EhCacheManager();
        manager.setCacheManagerConfigFile(EHCACHE_CONFIG);
        manager.setCacheManager(new CacheManager());
        return manager;
    }

    @Bean
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(simpleCookie());
        return manager;
    }
    public SimpleCookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setHttpOnly(true);
        cookie.setName("session.id");
        return cookie;
    }
//    过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/");
        filterFactoryBean.setSuccessUrl("");
        filterFactoryBean.setUnauthorizedUrl("");
        return filterFactoryBean;
    }
//    开启shrio注解权限控制
    @Bean
    public AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
