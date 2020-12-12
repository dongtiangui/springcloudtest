package com.hik.consul.hikconsul;

import com.hik.consul.hikconsul.rxjava.WebServiceInterface;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://rxjava.hikconsul.consul.hik.com/", //wsdl命名空间
        serviceName = "WebServiceInterface",                 //portType名称 客户端生成代码时 为接口名称
        endpointInterface = "com.hik.consul.hikconsul.rxjava.WebServiceInterface")//指定发布webservcie的接口类，此类也需要接入@WebService注解
@Service
public class WebServiceInterfaceImpl implements WebServiceInterface {

    @Override
    public String say(@WebParam(name="name")String name) {
        System.out.println("欢迎你"+name);
        return "欢迎你"+name;
    }
}
