package com.sie.demo.controller;

import com.sie.demo.model.User;
import com.sie.demo.service.UserService;
import com.sie.demo.util.ResultJson;
import com.sie.demo.util.query.PageHelper;
import com.sie.demo.util.query.UserQueryParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


//    @GetMapping("/list")
//    public ResultJson getUsers(Integer page,Integer limit){
//        PageHelper ph = new PageHelper(page,limit);
//        ph.countOffset();
//       return userService.getUsersByPage(ph.getOffset(),ph.getLimit());
//    }

    @PostMapping("/create")
    public ResultJson createUser(@RequestBody User user){
        userService.insertUser(user);
        return new ResultJson("创建用户成功");
    }

    @GetMapping("/{id}")
    public ResultJson getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResultJson updateUser(@PathVariable("id") int id,@RequestBody User user){
        userService.updateUser(user);
        return new ResultJson("更新成功");
    }

    @DeleteMapping("/{id}")
    public ResultJson deleteUserById(@PathVariable("id") int id){
        userService.deleteById(id);
        return new ResultJson("删除成功");
    }

    @GetMapping("/list")
    public ResultJson search(UserQueryParams params){
        System.out.println(params.getBeginDate());
        System.out.println(params.getEndDate());
        params.setOffset(PageHelper.countOffset(params.getPage(),params.getLimit()));
        return userService.getUsers(params);
    }

}
