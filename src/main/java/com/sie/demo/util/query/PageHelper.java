package com.sie.demo.util.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageHelper implements Serializable {
    private Integer page;
    private Integer limit;
    private Integer offset;

    public PageHelper(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

    public void countOffset(){
        if (this.page==null||this.limit==null){
            this.offset = 0;
            return;
        }
        this.offset = (this.page-1) * limit;
    }

    public static int countOffset(Integer page,Integer limit){
        if (page==null||limit==null)
            return 0;
        return (page-1)*limit;
    }
}
