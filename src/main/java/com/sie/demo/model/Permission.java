package com.sie.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Permission implements Serializable {

    private Integer id;
    private Integer parentId;
    private String name;
    private Integer type;
    private String description;
    private Integer sort;
    private Integer status;
    private String url;
    private String icon;
    private String identification;
    private List<Permission> children;

    public Permission(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

