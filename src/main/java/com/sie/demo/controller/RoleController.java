package com.sie.demo.controller;


import com.sie.demo.model.Role;
import com.sie.demo.service.RoleService;
import com.sie.demo.util.query.BaseQueryParams;
import com.sie.demo.util.query.PageHelper;
import com.sie.demo.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/{id}")
    public ResultJson getRoleById(@PathVariable("id") Integer id){
        return new ResultJson(roleService.queryById(id));
    }

    @PostMapping("/create")
    public ResultJson createRole(@RequestBody Role role){
        return roleService.insert(role);
    }

    @PutMapping("/{id}")
    public ResultJson updateRole(@PathVariable("id") Integer id,@RequestBody Role role){
        return roleService.update(role);

    }

    @DeleteMapping("/{id}")
    public ResultJson deleteRoleById(@PathVariable("id") Integer id){
        return roleService.deleteById(id);
    }

    @DeleteMapping("/group")
    public ResultJson deleteRoles(@RequestBody Integer[] ids){
        return roleService.deleteRolesByIds(ids);
    }

    @PutMapping("/{id}/permissions")
    public ResultJson setRolePermission(@PathVariable("id") Integer roleId,@RequestBody Integer[] permissionIds){
        return roleService.setRolePermissions(roleId,permissionIds);
    }

    @GetMapping("/{id}/permissions")
    public ResultJson getRolePermission(@PathVariable("id") Integer roleId){
        return roleService.getRolePermissions(roleId);
    }

    @GetMapping("/query")
    public ResultJson queryRoles(BaseQueryParams params){
        return roleService.queryRoles(params);
    }
}
