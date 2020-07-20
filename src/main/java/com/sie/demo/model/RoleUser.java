package com.sie.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleUser implements Serializable {
    private Integer userId;
    private Integer roleId;
}
