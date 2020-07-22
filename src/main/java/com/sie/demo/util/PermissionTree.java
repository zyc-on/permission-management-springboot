package com.sie.demo.util;

import com.sie.demo.model.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ivan
 * @description: 返回权限树的数据结构
 * @date 2020-07-22 17:25:19
 */
@Data
public class PermissionTree implements Serializable {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer type;
    private Integer status;
    private String url;
    private List<Permission> children;
}
