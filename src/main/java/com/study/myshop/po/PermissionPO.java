package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限（菜单）
 *
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionPO implements Serializable {

    private static final long serialVersionUID = 8770129387017341444L;

    /**
     * 权限编号
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 父级权限编号
     */
    private Integer pid;
}
