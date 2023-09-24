package com.study.myshop.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 22:30
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param authentication authentication
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 登录成功
        httpServletResponse.sendRedirect("/");
    }
}
