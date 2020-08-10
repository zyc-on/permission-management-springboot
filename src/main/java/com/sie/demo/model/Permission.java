package com.sie.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {

    private Integer id;
    private Integer parentId;
    private String parentName;
    private String name;
    private Integer type;
    private String description;
    private Integer sort;
    private Integer status;
    private String url;
    private String icon;
    private String identification;
    private String component;
}

