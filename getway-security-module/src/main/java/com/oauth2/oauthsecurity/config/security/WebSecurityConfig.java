package com.oauth2.oauthsecurity.config.security;
import com.oauth2.oauthsecurity.filter.HttpSessionEventPublisherLocal;
import com.oauth2.oauthsecurity.securityrealm.SpringSecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import static java.nio.charset.StandardCharsets.*;

/**
 * spring security 配置
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.IGNORED_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HttpSessionEventPublisherLocal httpSessionEventPublisherLocal;

    @Resource
    private DataSource dataSource;
    private final SpringSecurityUser springSecurityUser;
    public WebSecurityConfig(SpringSecurityUser springSecurityUser) {
        this.springSecurityUser = springSecurityUser;
    }
    //    密码加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(springSecurityUser).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置spring security 限制策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        每个配置项使用and进行连接
//        禁止匿名访问
//        结局frame不显示的问题
        http.headers().frameOptions().disable();
        http.requestCache().requestCache(new HttpSessionRequestCache());
        http.authorizeRequests()
//                对所以 / index home.html 不用认证即可授权
                .antMatchers("/oauth/**","/login", "/logout").permitAll()
                .antMatchers("/static/**", "/v2/**","/swagger-ui.html").permitAll()
                .antMatchers("/error/**").permitAll()
//                其它访问都要授权
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll().successHandler(successHandler())
//                规定表单参数
                .failureHandler(FailureHandler())
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler())
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(securityContextLogoutHandler())
                .and()
//                spring security 对于session的创建策略
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                maxSessionsPreventsLogin(true) 同一账号不可重复登录  为false 则顶出
                .maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/login").sessionRegistry(sessionRegistry()).and()
//                拒绝访问返回
                .and().exceptionHandling().accessDeniedPage("/deny")
//                采用持久化的方式实现token 记住我
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(persistentTokenRepository()).userDetailsService(springSecurityUser)
//        禁用csrf攻击 csrf攻击又称跨站伪造攻击
                .and()
                .csrf().disable();
    }
//静态资源
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assets/**");
        web.httpFirewall(defaultHttpFirewall());
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public DefaultHttpFirewall defaultHttpFirewall(){
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
//        允许使用反斜杠作为URL
        firewall.setAllowUrlEncodedSlash(true);
//        允许URL使用分号
        return firewall;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//     登入处理
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
    return new SavedRequestAwareAuthenticationSuccessHandler() {
      //            用于重定向登录成功的
      private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
      private RequestCache requestCache = new HttpSessionRequestCache();

      @Override
      public void onAuthenticationSuccess(
          HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //              拿到主体
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        try {
          User user = (User) authentication.getPrincipal();
          request.getSession().setAttribute("user", user);
          super.onAuthenticationSuccess(request, response, authentication);
          // 登录成功后重定向路由
          // 如果是要跳转到某个页面的
        } catch (Exception e) {
          logger.error(e);
        }
      }
    };
    }
//  认证失败处理
    public SimpleUrlAuthenticationFailureHandler FailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler(){
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                this.setDefaultFailureUrl("/login?error=true");
                super.onAuthenticationFailure(request, response, exception);
            }
        };
    }
//    登出处理

    @Bean
    public SimpleUrlLogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleUrlLogoutSuccessHandler() {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                super.handle(request, response, authentication);
            }
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                try {
                    User user = (User) authentication.getPrincipal();
                    logger.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                } catch (Exception e) {
                    logger.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                }
                response.sendRedirect("/login");
            }
        };
    }

//    登出的处理会话控制
    public SecurityContextLogoutHandler securityContextLogoutHandler(){
        return new SecurityContextLogoutHandler(){
            @Override
            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                if (this.isInvalidateHttpSession()){
                    HttpSession session = request.getSession(true);
                    if (session != null){
                        session.invalidate();
                    }
                }
                SecurityContextHolder.clearContext();
            }
        };
    }

    //    跨域处理
    @Bean
    @Order(1)
    public FilterRegistrationBean<CorsFilter> filterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedOrigin("*");
        source.registerCorsConfiguration("/**", configuration);
        return new FilterRegistrationBean<>(new CorsFilter(source));
    }

    @Bean
    public FilterRegistrationBean<Filter> localCharacterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(String.valueOf(UTF_8));
        return new FilterRegistrationBean<>(filter);
    }

//    持久化token
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
//        如果setCreateTableOnStartup为真 则在数据库中创建出token表
        repository.setCreateTableOnStartup(false);
        return repository;
    }
}
