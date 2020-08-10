package com.sie.demo.service.impl;

import com.sie.demo.dao.PermissionDao;
import com.sie.demo.model.Permission;
import com.sie.demo.service.PermissionService;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.PageHelper;
import com.sie.demo.util.query.PermissionQueryParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;


    @Override
    public ResultJson queryById(Integer id) {
        return new ResultJson(permissionDao.queryById(id));
    }


    @Override
    public ResultJson queryAllByLimit(Integer offset, Integer limit) {
        return new ResultJson(permissionDao.getPermissionsCount(), permissionDao.queryAllByLimit(offset, limit));
    }

    @Override
    public ResultJson insert(Permission permission) {
        this.permissionDao.insert(permission);
        return ResultJson.success();
    }

    @Override
    public ResultJson update(Permission permission) {
        this.permissionDao.update(permission);
        return ResultJson.success();
    }

    @Override
    public ResultJson deleteById(Integer id) {
        permissionDao.deleteById(id);
        return ResultJson.success();
    }

    public ResultJson deletePermissionsByIds(Integer[] ids) {
        for (Integer id : ids) {
            permissionDao.deleteById(id);
        }
        return ResultJson.success();
    }


    @Override
    public ResultJson getRootPermissions() {
        return new ResultJson(permissionDao.getRootPermissions());
    }

    @Override
    public ResultJson getChildPermissions(Integer parentId) {
        return new ResultJson(permissionDao.getChildPermissions(parentId));
    }

    @Override
    public ResultJson queryPermissions(PermissionQueryParams params) {
        params.setOffset(PageHelper.countOffset(params.getPage(), params.getLimit()));
        List<List<?>> list = permissionDao.queryPermissions(params);
        Integer total = (Integer) list.get(1).get(0);
        return new ResultJson(total, list.get(0));
    }

    @Override
    public ResultJson getPermissionTree() {
        return new ResultJson(permissionDao.getPermissionTree());
    }

    @Override
    public ResultJson updateStatus(Integer id, Integer status) {
        permissionDao.updateStatus(status,id);
        return new ResultJson("更新状态成功");
    }

    @Override
    public ResultJson getAsideMenus() {
        return new ResultJson(permissionDao.getAsideMenus());
    }


}