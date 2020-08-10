package com.sie.demo.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @description:
 * @date 2020-08-05 16:24:52
 */
@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (!StringUtils.isEmpty(header)) {
            String token = header.replace("Bearer", "").trim();

            boolean expired = false;
            try {
                expired = this.tokenUtil.isTokenExpired(token);
            } catch (Exception e) {
                new Throwable("令牌已过期，请重新登录。" + e.getMessage());
            }

            if (!expired) {
                //通过令牌获取用户名称
                String username = tokenUtil.getUsernameFromToken(token);

                //判断用户不为空，且SecurityContextHolder授权信息还是空的
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //通过用户信息得到UserDetails
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    //验证令牌有效性
                    boolean validate = false;
                    try {
                        validate = tokenUtil.validateToken(token, userDetails);
                    } catch (Exception e) {
                        new Throwable("验证token无效:" + e.getMessage());
                    }
                    if (validate) {
                        // 将用户信息存入 authentication，方便后续校验
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );
                        //
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }
}
