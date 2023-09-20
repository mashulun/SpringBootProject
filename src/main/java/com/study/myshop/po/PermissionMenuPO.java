package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * permissionMenu
 *权限菜单关联
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionMenuPO implements Serializable {

    private static final long serialVersionUID = -5193285911324644283L;

    /***
     * 权限编号
     */
    private Integer permissionId;

    /**
     * 菜单编号
     */
    private Integer menuId;
}
