package com.study.myshop.service.Impl;

import com.study.myshop.po.AdminPO;
import com.study.myshop.repository.AdminMapper;
import com.study.myshop.repository.RoleMapper;
import com.study.myshop.service.IAdminService;
import com.study.myshop.vo.AddAdminVo;
import com.study.myshop.vo.AdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-27 10:27
 */

@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private RoleMapper roleMapper;

    /***
     * 获取员工的数据数量- 支持keyWords
     * @param keyWords 检索关键字
     * @return 数量
     */
    @Override
    public Integer getAdminCount(String keyWords) {
        return  adminMapper.selectAdminCount(keyWords);
    }

    /***
     * 获取用户列表
     * @param keyWords 关键字
     * @return list
     */
    @Override
    public List<AdminPO> getAdminList(String keyWords,Integer page,Integer rows) {
        List<AdminPO> list = adminMapper.selectAllAdmin(keyWords,page,rows);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(admin -> admin.setAdminPass(null));
        }
        return list;
    }

    /**
     * @param id 用户id
     * @return Boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeAdminByAdminId(Integer id) {
        boolean deleteByAdminId = adminMapper.deleteByAdminId(id);
        if (deleteByAdminId) {
            deleteByAdminId = roleMapper.deleteRoleByAdminId(id);
        }
        return deleteByAdminId;
    }

    /**
     * 添加用户信息(增加权限)
     *
     * @param adminVo adminVo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAdminInfo(AddAdminVo adminVo) {
        adminMapper.insertAdminByAddAdminVo(adminVo);
        if (adminVo.getRoles() != null && adminVo.getRoles().length > 0) {
            adminMapper.insertAdminRoleAddByAddAdmin(adminVo);
        }
    }

    /****
     * 获取用户信息
     * @param adminId 用户id
     * @return AdminVo
     */
    @Override
    public AdminVo getAdmin(Integer adminId) {
        return adminMapper.selectAdminVo(adminId);
    }

    /**
     * @param adminVo adminVo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void putAdminByAdminVo(AddAdminVo adminVo) {
        boolean b = adminMapper.updateAdminByAddAdminVo(adminVo);
        if (b||(adminVo.getRoles() != null && adminVo.getRoles().length > 0)) {
            adminMapper.deleteAdminRoleByAdminId(adminVo.getAdminId());
            adminMapper.insertAdminRoleAddByAddAdmin(adminVo);
        }
    }
}