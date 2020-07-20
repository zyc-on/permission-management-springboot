package com.sie.demo.controller;

import com.sie.demo.model.Permission;
import com.sie.demo.service.PermissionService;
import com.sie.demo.util.query.PageHelper;
import com.sie.demo.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("menu")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/list")
    public ResultJson getPermissions(Integer page,Integer limit){
        PageHelper ph = new PageHelper(page,limit);
        ph.countOffset();
        return permissionService.queryAllByLimit(ph.getOffset(),limit);
    }

    @GetMapping("/mapper")
    public ResultJson getIdAndNameMapper(){
        return permissionService.getIdAndNameMapper();
    }

    @GetMapping("/{id}")
    public ResultJson getPermissionById(@PathVariable("id") Integer id){
        return new ResultJson(permissionService.queryById(id));
    }

    @PutMapping("/{id}")
    public ResultJson updatePermission(@PathVariable("id") Integer id,@RequestBody Permission permission){
        permissionService.update(permission);
        return new ResultJson("更新成功");
    }

    @DeleteMapping("/{id}")
    public ResultJson deletePermissionById(@PathVariable("id") Integer id){
        permissionService.deleteById(id);
        return new ResultJson("删除成功");
    }

    @PostMapping("/create")
    public ResultJson createPermission(@RequestBody Permission permission){
        permissionService.insert(permission);
        return new ResultJson("创建成功");
    }

    @GetMapping("/test")
    public ResultJson test(){
        return permissionService.getPermissionTree();
    }

    @GetMapping("/root")
    public ResultJson getRootPermissions(){
        return permissionService.getRootPermissions();
    }

    @GetMapping("/children")
    public ResultJson getChildPermissions(Integer parentId){
        return permissionService.getChildPermissions(parentId);
    }
}
