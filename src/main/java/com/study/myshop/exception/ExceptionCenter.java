package com.study.myshop.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理中心
 * @author msl
 * @version 1.0
 * @create 2023-10-07 16:26
 */
public class ExceptionCenter implements HandlerExceptionResolver {
    /**
     * @param httpServletRequest  httpServletRequest
     * @param httpServletResponse httpServletResponse
     * @param o o
     * @param e e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof AddAdminException){
            modelAndView.setViewName("error/addAdminError");
        }
        return modelAndView;
    }
}
