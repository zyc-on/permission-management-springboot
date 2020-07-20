package com.sie.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultJson {

    private String message;
    private Integer total;
    private Object data;

    public ResultJson(String message, Integer total, Object data) {
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public ResultJson(Integer total, Object data) {
        this.total = total;
        this.data = data;
    }

    public ResultJson(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResultJson(String message) {
        this.message = message;
    }

    public ResultJson(Object data) {
        this.data = data;
    }

    public static ResultJson success(){
        return new ResultJson("操作成功！");
    }
    public static ResultJson failure(){return new ResultJson("失败，请重试！");}
}
