package com.study.myshop.vo;

import com.study.myshop.po.MenuPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单的vo对象,提供菜单列表使用
 *
 * @author msl
 * @version 1.0
 * @create 2023-09-26 11:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo implements Serializable {

    private static final long serialVersionUID = 7813547832366628141L;

    /***
     * 主菜单信息
     */
    private MenuPO menu;

    /**
     * 子菜单信息,一个集合,参照原始表
     */
    private List<MenuPO> menus = new ArrayList<>();

}
