package com.oauth2.oauthsecurity.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o){
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSONString(o, SerializerFeature.WRITE_MAP_NULL_FEATURES));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("error",e);
        }
    }
}
