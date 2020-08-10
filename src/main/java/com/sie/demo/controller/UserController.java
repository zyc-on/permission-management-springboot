package com.sie.demo.controller;

import com.sie.demo.model.User;
import com.sie.demo.service.UserService;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.PageHelper;
import com.sie.demo.util.query.UserQueryParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:user:create')")
    @PostMapping("/create")
    public ResultJson createUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("/{id}")
    public ResultJson getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @PutMapping("/{id}")
    public ResultJson updateUser(@PathVariable("id") int id,@RequestBody User user){
        return userService.updateUser(user);
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{id}")
    public ResultJson deleteUserById(@PathVariable("id") int id){
        return userService.deleteById(id);
    }

    @DeleteMapping("/group")
    public ResultJson deleteUsers(@RequestBody Integer[] ids){
        return userService.deleteUsersByIds(ids);
    }

    @GetMapping("/list")
    public ResultJson search(UserQueryParams params){
        params.setOffset(PageHelper.countOffset(params.getPage(),params.getLimit()));
        return userService.getUsers(params);
    }

    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("query")
    public ResultJson queryUsers(UserQueryParams params){
        return userService.queryUsers(params);
    }


    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("/{id}/roles")
    public ResultJson getUserRoles(@PathVariable("id") Integer userId){
        return userService.getUserRoles(userId);
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @PutMapping("/{id}/roles")
    public ResultJson setUserRoles(@PathVariable("id") Integer userId,@RequestBody Integer[] roleIds){
        return userService.setUserRoles(userId,roleIds);
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @PutMapping("/status")
    public ResultJson updateStatus( Integer status, Integer id){
        return userService.updateStatus(status,id);
    }

    @PutMapping("/password")
    public ResultJson updatePassword(){
        return ResultJson.success();
    }

    @PutMapping("/password/reset/{id}")
    public ResultJson resetPassword(@PathVariable("id") Integer id){
        return userService.resetPassword(id);
    }


}
