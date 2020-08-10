package com.sie.demo.service;

import com.sie.demo.model.Permission;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.PermissionQueryParams;


public interface PermissionService {

    ResultJson queryById(Integer id);

    ResultJson queryAllByLimit(Integer offset, Integer limit);

    ResultJson insert(Permission permission);


    ResultJson update(Permission permission);

    ResultJson deleteById(Integer id);

    ResultJson deletePermissionsByIds(Integer[] ids);

    ResultJson getRootPermissions();

    ResultJson getChildPermissions(Integer parentId);

    ResultJson queryPermissions(PermissionQueryParams params);

    ResultJson getPermissionTree();

    ResultJson updateStatus(Integer id,Integer status);

    ResultJson getAsideMenus();
}