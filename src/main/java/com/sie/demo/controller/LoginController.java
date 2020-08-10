package com.sie.demo.controller;

import com.sie.demo.util.ResultJson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivan
 * @description:
 * @date 2020-07-30 14:11:19
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    @GetMapping("/login_page")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultJson loginPage(){
        return new ResultJson("请登录");
    }
}
