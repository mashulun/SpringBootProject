package com.study.myshop.service.impl;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (b){
            if ((adminVo.getRoles() != null && adminVo.getRoles().length > 0)) {
                adminMapper.deleteAdminRoleByAdminId(adminVo.getAdminId());
                adminMapper.insertAdminRoleAddByAddAdmin(adminVo);
            }
        }
    }

    /***
     * 获取分页数据(包含列表、当前页、总页数)
     * 将分页计算逻辑从Controller移至Service层
     * @param keyWords 检索关键字
     * @param page 当前页码
     * @return 分页结果Map
     */
    @Override
    public Map<String, Object> getAdminPageData(String keyWords, Integer page) {
        // 页数校验
        if (page == null || page < 1) {
            page = 1;
        }
        // 每页条数
        int pageSize = 3;
        // 查询总数
        int count = adminMapper.selectAdminCount(keyWords);
        // 计算总页数，防止count为0时除零
        int allPage = (count == 0) ? 1 : (count / pageSize + (count % pageSize > 0 ? 1 : 0));
        // 防止page超过总页数或低于1
        if (page > allPage) {
            page = allPage;
        }
        if (page < 1) {
            page = 1;
        }
        // 计算起始位置并查询列表
        int start = (page - 1) * pageSize;
        List<AdminPO> adminList = adminMapper.selectAllAdmin(keyWords, start, pageSize);
        if (!CollectionUtils.isEmpty(adminList)) {
            adminList.forEach(admin -> admin.setAdminPass(null));
        }
        // 组装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("adminList", adminList);
        result.put("page", page);
        result.put("allPage", allPage);
        result.put("keyWords", keyWords);
        return result;
    }
}