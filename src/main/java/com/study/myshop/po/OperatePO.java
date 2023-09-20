package com.study.myshop.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 功能操作
 *
 * @author msl
 * @date 2023/09/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatePO implements Serializable {

    private static final long serialVersionUID = 4596819056499365890L;

    /**
     * 操作编号
     */
    private Integer operateId;

    /**
     * 操作名称
     */
    private String operateName;

    /***
     * 操作URL
     */
    private String operateUrl;

}
