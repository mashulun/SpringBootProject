package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * permission operate
 * 权限操作
 *
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionOperatePO implements Serializable {

    private static final long serialVersionUID = -2599859488165215822L;
    /***
     * 权限编号
     */
    private Integer permissionId;

    /**
     * 操作编号
     */
    private Integer operateId;
}
