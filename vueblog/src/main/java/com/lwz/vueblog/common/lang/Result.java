package com.lwz.vueblog.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lw中
 * @date 2020/6/20 8:58
 *
 * @Data：用于自动生成get、set方法，简化代码编写量
 *
 * 用于返回操作结果信息，对操作结果进行封装
 */

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object Data;

    /**
     * @Pram  控制类获取到的信息
     * @Return 调用下面的success方法，返回操作结果代码，操作结果信息，操作结果数据
     * */
    public static Result succ(Object data) {
        return succ(200, "操作成功", data);
    }
    public static Result succ(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    /**
     * @Pram  msg
     * @Return 调用下面的fail方法，返回操作结果代码，操作结果信息，操作结果数据
     * */
    public static Result fail(String msg) {
        return fail(400, msg, null);
    }
    /**
     * @Pram  msg, data
     * @Return 调用下面的success方法，返回操作结果代码，操作结果信息，操作结果数据
     * */
    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }
    public static Result fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
