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
    public Role queryById(Integer id) {
        return this.roleDao.queryById(id);
    }

    @Override
    public ResultJson queryAllByLimit(Integer offset, Integer limit) {
        return new ResultJson(roleDao.getRoleCount(),roleDao.queryAllByLimit(offset,limit));
    }

    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getId());
    }


    @Override
    public boolean deleteById(Integer id) {
        return this.roleDao.deleteById(id) > 0;
    }

    @Override
    public ResultJson setRolePermissions(Integer roleId, Integer[] permissionIds) {
        roleDao.resetRolePermission(roleId);
        for (Integer permissionId : permissionIds) {
            roleDao.setRolePermission(roleId,permissionId);
        }
        return ResultJson.success();
    }

    @Override
    public ResultJson getRolePermissions(Integer roleId) {
        return new ResultJson(roleDao.getRolePermissions(roleId));
    }

    public ResultJson queryRoles(BaseQueryParams params){
        params.setOffset(PageHelper.countOffset(params.getPage(),params.getLimit()));
        List<List<?>> list = roleDao.queryRoles(params);
        Integer total = (Integer) list.get(1).get(0);
        return new ResultJson(total,list.get(0));
    }
}