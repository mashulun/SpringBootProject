package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * menu
 *菜单表
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuPO implements Serializable {

    private static final long serialVersionUID = 4358139596003633053L;

    /***
     *菜单编号
     */
    private Integer menuId;

    /***
     *菜单名称
     */
    private String menuName;

    /***
     *菜单URL
     */
    private String menuUrl;

    /***
     *父级菜单编号
     */
    private Integer pid;
}
