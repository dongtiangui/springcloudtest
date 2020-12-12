package com.oauth2.oauthsecurity.filter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 网关过滤器
 */
@Slf4j
public class AccessFilter extends ZuulFilter {
  /**
   * pre：可以在请求被路由之前调用
   * route：在路由请求时候被调用
   * post：在route和error过滤器之后被调用
   * error：处理请求时发生错误时被调用
   *
   * @return
   */
  @Override
  public String filterType() { return "pre"; }
    /**
     * 过滤级别 数字大 越低
     * @return
     */
    @Override
    public int filterOrder() { return 0; }
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        return httpServletRequest.getRequestURI().contains("api");
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse httpServletResponse = ctx.getResponse();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        if (httpServletRequest.getParameter("Token") ==null){
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
        }
        try {
            ctx.getResponse().getWriter().write("token is empty");
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("success！");
        return null;
    }
}
