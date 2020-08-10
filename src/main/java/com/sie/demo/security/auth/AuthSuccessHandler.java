package com.sie.demo.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.demo.dto.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @description:
 * @date 2020-07-30 11:58:28
 */
@Slf4j
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
       response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());

//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenUtil.generateToken(loginUser);

//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        System.out.println(loginUser.getAuthorities());
//        System.out.println(loginUser.getPermissions());

        Map<String,Object> map = new HashMap<>();
        map.put("username",loginUser.getUsername());
        map.put("permissions",loginUser.getAuthorities());
        map.put("menu",loginUser.getPermissions());
        map.put("token",token);
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}
