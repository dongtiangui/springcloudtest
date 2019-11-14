package com.alipay.demo.config;

import javax.ws.rs.core.MediaType;

/**
 * @author alipay.demo
 *
 */
public class RestConstants {
	
	 /***
     * accessToken key in Http Header
     */
    public static final String ACCESS_TOKEN_KEY = "accessToken";

    /**
     * userId key in Http Header
     */
    public static final String USER_ID_KEY      = "userId";
    
    /**
     * response encoding
     */
    public static final String DEFAULT_CHARSET      = "UTF-8";
    /**
     * Content-Type
     */
    public static final String DEFAULT_CONTENT_TYPE = MediaType.APPLICATION_JSON + ";"
                                                      + MediaType.CHARSET_PARAMETER + "="
                                                      + DEFAULT_CHARSET;

}
