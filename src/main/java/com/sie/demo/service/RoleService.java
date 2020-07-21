package com.sie.demo.service;


import com.sie.demo.model.Role;
import com.sie.demo.util.ResultJson;


public interface RoleService {


    Role queryById(Integer id);

    ResultJson queryAllByLimit(Integer offset, Integer limit);

    Role insert(Role role);

    Role update(Role role);

    boolean deleteById(Integer id);

    ResultJson setRolePermissions(Integer roleId, Integer[] permissionIds);

    ResultJson getRolePermissions(Integer roleId);

}