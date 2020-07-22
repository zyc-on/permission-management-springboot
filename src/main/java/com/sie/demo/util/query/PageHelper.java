package com.sie.demo.util.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageHelper implements Serializable {

    public static int countOffset(Integer page,Integer limit){
        if (page==null||limit==null)
            return 0;
        return (page-1)*limit;
    }
}
