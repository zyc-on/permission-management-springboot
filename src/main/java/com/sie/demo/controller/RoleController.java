package com.sie.demo.controller;


import com.sie.demo.model.Role;
import com.sie.demo.service.RoleService;
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

    @GetMapping("/list")
    public ResultJson getRoles(Integer page,Integer limit){
        PageHelper ph = new PageHelper(page,limit);
        ph.countOffset();
        return roleService.queryAllByLimit(ph.getOffset(),limit);
    }

    @GetMapping("/{id}")
    public ResultJson getRoleById(@PathVariable("id") Integer id){
        return new ResultJson(roleService.queryById(id));
    }

    @PostMapping("/create")
    public ResultJson createRole(@RequestBody Role role){
        roleService.insert(role);
        return new ResultJson("创建角色成功");
    }

    @PutMapping("/{id}")
    public ResultJson updateRole(@PathVariable("id") Integer id,@RequestBody Role role){
        roleService.update(role);
        return new ResultJson("更新角色成功");
    }

    @DeleteMapping("/{id}")
    public ResultJson deleteRoleById(@PathVariable("id") Integer id){
        roleService.deleteById(id);
        return new ResultJson("删除角色成功");
    }

    @PutMapping("/{roleId}/permission")
    public ResultJson setRolePermission(@PathVariable("roleId") Integer roleId,@RequestBody Integer[] permissionIds){
        return roleService.setRolePermissions(roleId,permissionIds);
    }

}
