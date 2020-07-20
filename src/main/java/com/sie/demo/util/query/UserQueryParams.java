package com.sie.demo.util.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author Ivan
 * @description: 用户查询参数
 * @date 2020-07-16 14:32:45
 */
@Data
public class UserQueryParams extends BaseQueryParams {

    @Length(min = 1,message = "username属性不合法")
    private String username;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "创建时间不合法")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "创建时间不合法")
    private Date endDate;
}
