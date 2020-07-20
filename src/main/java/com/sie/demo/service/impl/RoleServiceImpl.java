package com.sie.demo.service.impl;


import com.sie.demo.dao.RoleDao;
import com.sie.demo.model.Role;
import com.sie.demo.service.RoleService;
import com.sie.demo.util.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;



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
}