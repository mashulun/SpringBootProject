package com.study.myshop.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 22:28
 */
@Component
public class LoginErrorHandler implements AuthenticationFailureHandler {
    /**
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param e e
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //登录失败,重定向到登录
        httpServletResponse.sendRedirect("/admin/login");
    }
}
