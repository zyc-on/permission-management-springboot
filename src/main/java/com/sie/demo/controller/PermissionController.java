package com.sie.demo.controller;

import com.sie.demo.model.Permission;
import com.sie.demo.service.PermissionService;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.PermissionQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("menu")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

//    @PreAuthorize("hasAuthority('sys:permission:query')")
    @GetMapping("/{id}")
    public ResultJson getPermissionById(@PathVariable("id") Integer id) {
        return permissionService.queryById(id);
    }

//    @PreAuthorize("hasAuthority('sys:permission:query')")
    @PutMapping("/{id}")
    public ResultJson updatePermission(@PathVariable("id") Integer id, @RequestBody Permission permission) {
        return permissionService.update(permission);
    }

    @PreAuthorize("hasAuthority('sys:permission:delete')")
    @DeleteMapping("/{id}")
    public ResultJson deletePermissionById(@PathVariable("id") Integer id) {
        return permissionService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('sys:permission:delete')")
    @DeleteMapping("/group")
    public ResultJson deletePermissions(@RequestBody Integer[] ids) {
        return permissionService.deletePermissionsByIds(ids);
    }

//    @PreAuthorize("hasAuthority('sys:permission:query')")
    @PostMapping("/create")
    public ResultJson createPermission(@RequestBody Permission permission) {
        return permissionService.insert(permission);
    }

    @GetMapping("/root")
    public ResultJson getRootPermissions() {
        return permissionService.getRootPermissions();
    }

    @GetMapping("/children")
    public ResultJson getChildPermissions(@RequestParam Integer parentId) {
        return permissionService.getChildPermissions(parentId);
    }

    @GetMapping("/query")
    public ResultJson queryPermissions(PermissionQueryParams params) {
        return permissionService.queryPermissions(params);
    }

    @GetMapping("/tree")
    public ResultJson getPermissionTree() {
        return permissionService.getPermissionTree();
    }

    @GetMapping("/aside")
    public ResultJson getAsideMenus(){return permissionService.getAsideMenus();}
}
