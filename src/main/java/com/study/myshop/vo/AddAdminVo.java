package com.study.myshop.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-27 16:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAdminVo implements Serializable {

    private static final long serialVersionUID = 5245509024535223908L;

    /**
     * 账户名称
     */
    private String adminName;


    /**
     * 账户密码
     */
    private String adminPass;

    /**
     * 用户昵称
     */
    private String adminNickname;


    /***
     * 角色多选
     */
    private Integer[] roles;

}
