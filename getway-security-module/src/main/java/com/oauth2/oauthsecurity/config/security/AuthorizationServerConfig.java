//package com.oauth2.oauthsecurity.config.security;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.util.Assert;
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.concurrent.TimeUnit;
//@Configuration
////认证服务端
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    @Qualifier("jdbcClientDetailsService")
//    private ClientDetailsService clientDetailsService;
//    @Autowired
//    @Qualifier("springSecurityUser")
//    private UserDetailsService customUserDetailsService;
//    @Resource
//    private DataSource dataSource;
//    @Autowired
//    @Qualifier("jdbcTokenStore")
//    private TokenStore tokenStore;
//
//    @Autowired
//    private AuthorizationCodeServices authorizationCodeServices;
//
//    /**
//     * 配置授权服务器的安全，意味着实际上是/oauth/token端点。
//     * /oauth/authorize端点也应该是安全的
//     * 默认的设置覆盖到了绝大多数需求，所以一般情况下你不需要做任何事情。
//     * 配置token的安全性以及访问权限
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//        oauthServer
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()")
////                让/oauth/token 支持client_id的方式进行
//                .allowFormAuthenticationForClients();
//    }
//
//
//    /**
//     * 配置ClientDetailsService
//     * 注意，除非你在下面的configure(AuthorizationServerEndpointsConfigurer)中指定了一个AuthenticationManager，否则密码授权方式不可用。
//     * 至少配置一个client，否则服务器将不会启动。
//     * 配置客户端详情信息
//     * 从数据库中拿出client_ID 等等
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
//    }
//
//    /**
//     * 该方法是用来配置Authorization Server endpoints的一些非安全特性的，
//     * 比如token存储、token自定义、授权类型等等的
//     * 默认情况下，你不需要做任何事情，除非你需要密码授权，那么在这种情况下你需要提供一个AuthenticationManager
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.authenticationManager(authenticationManager);
//        endpoints.tokenStore(tokenStore);
//        endpoints.userDetailsService(customUserDetailsService);
////        token是否重复使用
//        endpoints.reuseRefreshTokens(true);
//        endpoints.authorizationCodeServices(authorizationCodeServices);
//        // 配置TokenServices参数
//        endpoints.tokenServices(defaultTokenServices(endpoints));
//    }
//
//    public DefaultTokenServices defaultTokenServices(AuthorizationServerEndpointsConfigurer endpoints){
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setReuseRefreshToken(true);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天(int) TimeUnit.DAYS.toSeconds(30)
//        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
//        return tokenServices;
//    }
//
//    @Bean
//    public TokenStore jdbcTokenStore() {
//        Assert.state(dataSource != null, "DataSource must be provided");
//        return new JdbcTokenStore(dataSource);
//    }
//
//    @Bean
//    public JdbcClientDetailsService jdbcClientDetailsService(){
//        Assert.state(dataSource!=null,"DataSource must be provided");
//        return new JdbcClientDetailsService(dataSource);
//    }
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
//}
