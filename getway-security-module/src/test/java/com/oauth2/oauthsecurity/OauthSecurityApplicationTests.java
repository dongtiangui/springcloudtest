package com.oauth2.oauthsecurity;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.wsdl.BindingOperation;
import javax.xml.namespace.QName;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OauthSecurityApplicationTests {

    @Test
    public void contextLoads() {

        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://127.0.0.1:8054/webservice/webservice?wsdl");
        Object[] result = null;
        try {
            //如果有命名空间的话
            QName operationName = new QName("http://rxjava.hikconsul.consul.hik.com/","say"); //如果有命名空间需要加上这个，第一个参数为命名空间名称，第二个参数为WebService方法名称
            result = client.invoke(operationName,"11111222");//后面为WebService请求参数数组
      System.out.println("22222");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}
