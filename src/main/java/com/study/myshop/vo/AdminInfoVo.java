package com.study.myshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 用户界面显示
 * @author msl
 * @version 1.0
 * @create 2023-09-26 14:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoVo implements Serializable {


    private static final long serialVersionUID = -6173988219063532337L;

    private String nickName;

     private List<String> roles  = new ArrayList<>();
}
