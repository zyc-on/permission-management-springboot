package com.sie.demo.util.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * @author Ivan
 * @description: 基本查询参数
 * @date 2020-07-16 14:00:31
 */

@Data
public class BaseQueryParams {

    @Min(value = 1,message = "page参数不合法")
    private Integer page;

    @Min(value = 1,message = "page参数不合法")
    private Integer limit;

    private Integer offset;


    @Length(min = 1,message = "name属性不合法")
    private String name;

    @Range(min = 0,max = 1,message = "status属性不合法")
    private Integer status;
}
