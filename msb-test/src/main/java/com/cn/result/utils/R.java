package com.cn.result.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: R
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:25
 */
public class R extends HashMap<Object, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        this.put("code", 0);
        this.put("msg", "操作成功");
    }

    public static R error() {
        return error(1, "操作失败");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(Object key, Object value) {
        super.put(key, value);
        return this;
    }
}
