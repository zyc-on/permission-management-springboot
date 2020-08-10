package com.sie.demo.security;

import com.sie.demo.security.auth.AuthFailureHandler;
import com.sie.demo.security.auth.AuthSuccessHandler;
import com.sie.demo.security.auth.TokenAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @author Ivan
 * @description:
 * @date 2020-07-30 09:40:13
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        http.cors();

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().cacheControl();

        http.formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler);

        http.authorizeRequests()
                .antMatchers("/login", "/login_page", "/user/password/reset/3")
                .permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class);



//        http.csrf().disable();
//        http.cors();
//        http.authorizeRequests()
//                //处理跨域请求中的Preflight请求
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers("/login","/login_page","/user/password/reset/3","/user/query")
//                .permitAll()
//                .anyRequest().authenticated();
//
//        http.formLogin()
//                .loginPage("/login_page")
//                .loginProcessingUrl("/login")
//                .successHandler(authSuccessHandler)
//                .failureHandler(authFailureHandler);
    }
}
