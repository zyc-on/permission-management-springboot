package com.sie.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermission implements Serializable {
    private Integer roleId;
    private Integer permissionId;
}
