package com.oauth2.oauthsecurity.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter",
        urlPatterns = "*.admin",dispatcherTypes = DispatcherType.FORWARD)
@Component
@Order(3)
public class CharsetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        context.getAttribute("");
    }
}
