package com.sie.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;


@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -10138650389951617L;
    
    private Integer id;
    private Integer code;
    private String name;
    private Integer status;
    
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiredDate;


}