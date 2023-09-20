package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminPO implements Serializable {

    private static final long serialVersionUID = -3624702394501215159L;
    /**
     *用户编号
     */
    private Integer adminId;


    /**
     *账户名称
     */
    private String adminName;


    /**
     *账户密码
     */
    private String adminPass;

    /**
     *用户昵称
     */
    private String adminNickname;

    /**
     * 用户状态：1启用
     */
    private Integer adminStatus;
}
