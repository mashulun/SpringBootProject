package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 角色
 *
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePO implements Serializable {

  private static final long serialVersionUID = 4000954968730015857L;

  /**
   * 角色编号
   */
  private Integer roleId;

  /**
   * 角色名称
   */
  private String roleName;
}
