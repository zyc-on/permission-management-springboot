package com.sie.demo.service;


import com.sie.demo.model.Role;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.BaseQueryParams;


public interface RoleService {


    ResultJson queryById(Integer id);

    ResultJson queryAllByLimit(Integer offset, Integer limit);

    ResultJson insert(Role role);

    ResultJson update(Role role);

    ResultJson deleteById(Integer id);

    ResultJson deleteRolesByIds(Integer[] ids);

    ResultJson setRolePermissions(Integer roleId, Integer[] permissionIds);

    ResultJson getRolePermissions(Integer roleId);

    ResultJson queryRoles(BaseQueryParams params);

}