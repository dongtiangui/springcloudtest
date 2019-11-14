package com.alipay.demo.controller.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.spi.HttpResponse;

import com.alipay.demo.config.RestConstants;
import com.alipay.demo.pojo.UserInfo;

/**
 * @author alipay.demo
 *
 */
@Path("/users")
public interface UserController {

	/**
	 * 获取用户信息
	 * @param authCode authCode
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(RestConstants.DEFAULT_CONTENT_TYPE)
	UserInfo authUser(@QueryParam("authcode") String authCode, @Context HttpResponse response) throws Exception;

}
