package com.oauth2.oauthsecurity.filter;
import com.alibaba.fastjson.JSON;
import com.oauth2.oauthsecurity.domain.LoginSuccessVO;
import com.oauth2.oauthsecurity.domain.UserDTO;
import com.oauth2.oauthsecurity.domain.UserEntity;
import com.oauth2.oauthsecurity.form.LoginForm;
import com.oauth2.oauthsecurity.util.JwtTokenUtil;
import com.oauth2.oauthsecurity.util.ResponseUtil;
import com.oauth2.oauthsecurity.util.ResultUtil;
import com.oauth2.oauthsecurity.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    //    请求登录
//    方法将表单请求的信息（用户、密码等信息）赋值给UsernamePasswordAuthenticationToken（authRequest）
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginForm loginForm = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
            logger.info("loginForm"+loginForm.getUsername());
            checkForm(loginForm,response);
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(loginForm,userEntity);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(JSON.toJSONString(userEntity),loginForm.getPassword(),new LinkedList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            ResponseUtil.write(response,ResultUtil.error("认证失败"));
        }
        return null;
    }

//    认证成功
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDTO userDTO = (UserDTO) authResult.getPrincipal();
        if (jwtTokenUtil==null){
            JwtTokenUtil jwtTokenUtil = (JwtTokenUtil) SpringUtils.getBean("jwtTokenUtil");
        }
        assert jwtTokenUtil != null;
        String token = jwtTokenUtil.createToken(userDTO);
        response.addHeader(jwtTokenUtil.getTokenHeader(), jwtTokenUtil.getTokenPrefix() + token);
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        BeanUtils.copyProperties(userDTO, loginSuccessVO);
        ResponseUtil.write(response, ResultUtil.success(loginSuccessVO));
    }

//    不成功
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.write(response, ResultUtil.error(failed.getMessage()));
    }
    private void checkForm(LoginForm loginForm, HttpServletResponse response){
        if (StringUtils.isBlank(loginForm.getUsername())) {
            ResponseUtil.write(response, ResultUtil.error("用户不能为空"));
            return;
        }
        if (StringUtils.isBlank(loginForm.getPassword())) {
            ResponseUtil.write(response, ResultUtil.error("密码不能为空"));
        }
    }
}
