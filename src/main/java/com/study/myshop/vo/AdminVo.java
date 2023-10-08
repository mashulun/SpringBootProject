package com.study.myshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-10-07 17:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVo implements Serializable {

    private static final long serialVersionUID = 646699171989313654L;


    /**
     *用户编号
     */
    private Integer adminId;

    /**
     * 账户名称
     */
    private String adminName;


    /**
     * 用户昵称
     */
    private String adminNickname;


    /***
     * 角色组合
     */
    private List<AdminRoleVo> adminRoleVoList;

}
