package com.oauth2.oauthsecurity.apidoc;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.LinkedList;
import java.util.List;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){

//        规范前台请求参数
        ParameterBuilder parameterBuilder = new ParameterBuilder();

        parameterBuilder.name("sessionId")
                .description("登录令牌")
                .required(false)
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .build();

        List<Parameter> parameters = new LinkedList<>();

        parameters.add(parameterBuilder.build());

       return new Docket(DocumentationType.SWAGGER_2)
               .globalOperationParameters(parameters)
               .globalResponseMessage(RequestMethod.GET,responseMessages())
               .apiInfo(userApi())
               .select()
               .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
               .paths(PathSelectors.any())
               .build();
    }

    private ApiInfo userApi(){
        Contact contact = new Contact("dong","192.168.0.1","1056976753@qq.com");
        return new ApiInfo(
                "用户中心",//大标题
                "REST风格API",//小标题
                "0.1",//版本
                "www.baidu.com",
                contact,//作者
                "下面是api",//链接显示文字
                ""//网站链接
        );
    }
    private List<ResponseMessage> responseMessages(){
        List<ResponseMessage> response = new LinkedList<>();
        ResponseMessage error500 = new ResponseMessageBuilder()
                .code(500)
                .message("系统错误！管理员正在维护")
                .responseModel(new ModelRef("string"))
                .build();
        ResponseMessage error404 = new ResponseMessageBuilder()
                .code(404)
                .message("页面找不到")
                .responseModel(new ModelRef("string"))
                .build();
        response.add(error500);
        response.add(error404);
        return response;
    }
}
