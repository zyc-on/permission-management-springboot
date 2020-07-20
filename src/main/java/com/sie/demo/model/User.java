package com.sie.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id")
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 2,max = 20,message = "用户名长度不合法（2-20字符）")
    private String username;

    @Length(min = 6,max = 20,message = "密码长度不合法（2-20位）")
    private String password;

    @NotBlank(message = "名字不能为空")
    private String name;

    @Range(min = 0,max = 1,message = "状态信息错误")
    private Integer gender;

    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式错误")
    private String tel;

    @Email(message = "邮箱格式错误")
    private String email;

    @Range(min = 0,max = 1,message = "状态信息错误")
    private Integer status;

    @Length(max = 128)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm",timezone = "GMT+8")
    private Date updateTime;

}
