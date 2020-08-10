package com.sie.demo.service.impl;


import com.sie.demo.dao.RoleDao;
import com.sie.demo.model.Role;
import com.sie.demo.service.RoleService;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.BaseQueryParams;
import com.sie.demo.util.query.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public ResultJson queryById(Integer id) {
        return new ResultJson(roleDao.queryById(id));
    }

    @Override
    public ResultJson queryAllByLimit(Integer offset, Integer limit) {
        return new ResultJson(roleDao.getRoleCount(), roleDao.queryAllByLimit(offset, limit));
    }

    @Override
    public ResultJson insert(Role role) {
        roleDao.insert(role);
        return new ResultJson("创建角色成功");
    }

    @Override
    public ResultJson update(Role role) {
        roleDao.update(role);
        return new ResultJson("更新角色成功");
    }


    @Override
    public ResultJson deleteById(Integer id) {
        this.roleDao.deleteById(id);
        return new ResultJson("删除角色成功");
    }

    public ResultJson deleteRolesByIds(Integer[] ids) {
        for (Integer id : ids) {
            roleDao.deleteById(id);
        }
        return new ResultJson("删除角色成功");
    }

    @Override
    public ResultJson setRolePermissions(Integer roleId, Integer[] permissionIds) {
        roleDao.resetRolePermission(roleId);
        for (Integer permissionId : permissionIds) {
            roleDao.setRolePermission(roleId, permissionId);
        }
        return new ResultJson("设置角色权限成功");
    }

    @Override
    public ResultJson getRolePermissions(Integer roleId) {
        return new ResultJson(roleDao.getRolePermissions(roleId));
    }

    public ResultJson queryRoles(BaseQueryParams params) {
        params.setOffset(PageHelper.countOffset(params.getPage(), params.getLimit()));
        List<List<?>> list = roleDao.queryRoles(params);
        Integer total = (Integer) list.get(1).get(0);
        return new ResultJson(total, list.get(0));
    }

    @Override
    public ResultJson updateStatus(Integer id, Integer status) {
        roleDao.updateStatus(status,id);
        return new ResultJson("更新状态成功");
    }
}