package com.sie.demo.service;

import com.sie.demo.model.Permission;
import com.sie.demo.util.ResultJson;


public interface PermissionService {

    Permission queryById(Integer id);

    ResultJson queryAllByLimit(Integer offset, Integer limit);

    Permission insert(Permission permission);


    Permission update(Permission permission);

    boolean deleteById(Integer id);

    ResultJson getAllPermissions();

    ResultJson getRootPermissions();

    ResultJson getChildPermissions(Integer parentId);

}