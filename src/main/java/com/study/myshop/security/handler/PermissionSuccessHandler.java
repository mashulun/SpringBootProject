package com.study.myshop.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-23 22:39
 */
public class PermissionSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //认证成功
        //根据角色查询菜单,将菜单放入session中,页面就可以看了
    }
}
