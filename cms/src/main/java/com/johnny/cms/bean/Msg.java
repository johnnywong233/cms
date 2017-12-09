package com.johnny.cms.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Johnny
 * Date: 2017/12/3
 * Time: 上午12:26
 */
@Data
public class Msg {
    //状态码   1-成功    0-失败
    private int code;

    private String msg;

    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg("处理失败");
        return result;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }
}
