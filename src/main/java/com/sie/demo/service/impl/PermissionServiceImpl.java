package com.sie.demo.service.impl;

import com.sie.demo.dao.PermissionDao;
import com.sie.demo.model.Permission;
import com.sie.demo.service.PermissionService;
import com.sie.demo.util.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;


    @Override
    public Permission queryById(Integer id) {
        return this.permissionDao.queryById(id);
    }


    @Override
    public ResultJson queryAllByLimit(Integer offset, Integer limit) {
        return new ResultJson(permissionDao.getPermissionsCount(), permissionDao.queryAllByLimit(offset, limit));
    }

    @Override
    public Permission insert(Permission permission) {
        this.permissionDao.insert(permission);
        return permission;
    }

    @Override
    public Permission update(Permission permission) {
        this.permissionDao.update(permission);
        return this.queryById(permission.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.permissionDao.deleteById(id) > 0;
    }

    @Override
    public ResultJson getIdAndNameMapper() {
        return new ResultJson(permissionDao.getIdAndNameMapper());
    }

    @Override
    public ResultJson getPermissionTree() {
        return new ResultJson(permissionDao.getPermissionTree());
    }
}