package com.sie.demo.util.query;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * @author Ivan
 * @description: 菜单查询参数
 * @date 2020-07-16 14:38:58
 */
@Data
public class PermissionQueryParams extends BaseQueryParams {
    @Range(min = 0,max = 2,message = "type参数不合法")
    private Integer type;
}
