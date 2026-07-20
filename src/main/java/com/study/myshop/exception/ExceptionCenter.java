package com.study.myshop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理中心
 * @author msl
 * @version 1.0
 * @create 2023-10-07 16:26
 */
@ControllerAdvice
public class ExceptionCenter {

    @ExceptionHandler(AddAdminException.class)
    public ModelAndView handleAddAdminException(AddAdminException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/addAdminError");
        return modelAndView;
    }
}
