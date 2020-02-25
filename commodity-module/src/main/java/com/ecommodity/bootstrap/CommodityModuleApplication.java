package com.ecommodity.bootstrap;
import com.ecommodity.bootstrap.util.JsonUtil;
import com.ecommodity.bootstrap.util.LoginResult;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@Controller
@EnableJpaRepositories
public class CommodityModuleApplication {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    @Qualifier(value = "clientRestTemplate")
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private RequestConfig config;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return restTemplate().getForEntity("http://oauth2-center/api/index",String.class).getBody();
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/login")
    public LoginResult user() {
//        System.out.println(map.get("name") + " , " + map.get("password"));
        LoginResult resultUtil = new LoginResult();
        resultUtil.setCaptcha("image('100x40', '#FFFFFF', '@word')");
        resultUtil.setMessage("成功");
        resultUtil.setLogin(true);
//        resultUtil.setName(map.get("name").toString());
        resultUtil.setToken("guid");
        resultUtil.setUid(UUID.randomUUID().toString());
        return resultUtil;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/user/navlist")
    public Object navlist() throws IOException {
        Object json = JsonUtil.readJsonFromClassPath("navlist.json", Object.class);
        System.out.println(json);
        return json;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getToken")
    public Map<String,Object> getToken() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","123");
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommodityModuleApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        template.setErrorHandler(new DefaultResponseErrorHandler());
        return template;
    }
}
