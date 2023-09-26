package com.study.myshop.service.Impl;

import com.study.myshop.po.MenuPO;
import com.study.myshop.repository.MenuMapper;
import com.study.myshop.service.IIndex;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-26 11:58
 */
@Service
public class IndexService  implements IIndex {


    @Resource
    private MenuMapper menuMapper;

    /***
     *
     * @param userName 用户名
     * @return list
     */
    @Override
    public List<MenuPO> getMenuListByAdminName(String userName) {
        return menuMapper.selectMenuListByAdminName(userName);
    }
}
