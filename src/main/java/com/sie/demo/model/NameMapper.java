package com.sie.demo.model;

import lombok.Data;

@Data
public class NameMapper {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private String name;
}
