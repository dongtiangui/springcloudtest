package com.alipay.demo;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**定义 jaxrs根路径
 * @author alipay.demo
 *
 */
@Component
@ApplicationPath("/")
public class JaxrsApplication extends javax.ws.rs.core.Application {
}
