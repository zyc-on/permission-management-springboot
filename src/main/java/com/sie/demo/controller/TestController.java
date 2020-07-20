package com.sie.demo.controller;

import com.sie.demo.model.User;
import com.sie.demo.util.ResultJson;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
@Validated
public class TestController {

    @GetMapping("/test1")
    public ResultJson getUsers(){
        User u1 = new User();
        u1.setUsername("Mike");
        User u2 = new User();
        u2.setUsername("Mark");
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        return new ResultJson("成功",list);
    }

    @GetMapping("/test2")
    public ResultJson getUser(){
        User u1 = new User();
        u1.setUsername("Mike");
        return ResultJson.success();
    }

    @GetMapping("/3")
    public ResultJson testParam(@RequestParam("email") @Email(message = "邮箱格式错误") String email){
        System.out.println(email);
        return ResultJson.success();
    }

    @PostMapping("/4")
    public ResultJson testParam2(@RequestBody @Validated User user){
        System.out.println(user.getUsername());
        return ResultJson.success();
    }

//    @GetMapping("/4")
//    public ResultJson test4(QueryParams queryParams){
//        System.out.println(queryParams.toString());
//        return new ResultJson(queryParams);
//    }
}
