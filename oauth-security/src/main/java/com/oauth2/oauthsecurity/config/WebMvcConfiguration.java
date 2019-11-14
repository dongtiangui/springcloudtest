package com.oauth2.oauthsecurity.config;

import org.springframework.web.servlet.config.annotation.*;

public class WebMvcConfiguration extends WebMvcConfigurationSupport {

// 自定义 URL匹配规则 PathMatchConfigurer
//    setUseTrailingSlashMatch 设置 是否包含斜杠
//    setUseSuffixPatternMatch 设置 是否包含后缀

//    配置静态资源 addResourceHandler springboot 访问的控制器路径
//     addResourceLocations 静态资源的文件路径

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true).setUseSuffixPatternMatch(false);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
// 跨域访问 addCorsMappings
//    只有经过认证的 才能经过跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**")
                .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                .allowedOrigins("*")
                .maxAge(1000000)
                .allowCredentials(true);
    }
// 添加 无逻辑判断 控制器 页面的转换关系
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("").setViewName("");
    }
}
