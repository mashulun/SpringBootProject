package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;


/**
 * 角色表role 对应的实体对象，必须实现接口：GrantedAuthority
 * 目的也是做到统一操作标准，实现方法，获取角色列表。
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePO implements GrantedAuthority,Serializable {

  private static final long serialVersionUID = 4000954968730015857L;

  /**
   * 角色编号
   */
  private Integer roleId;

  /**
   * 角色名称
   */
  private String roleName;

  /**
   * @return
   */
  @Override
  public String getAuthority() {
    return this.getRoleName();
  }

}
