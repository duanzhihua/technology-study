package cn.guxiangfly.distributelock.springbootstudy.myrpc.bean;

import lombok.Data;

/**
 * @Author guxiang02
 * @Date 2020/9/27
 **/
@Data
public class Result {
    Integer code;
    String msg;
    Object data;


    public static Result  success(Object o){
        Result result = new Result();
        result.setCode(100);
        result.setMsg("success");
        result.setData(o);
        return result;
    }

    public static Result  error(String msg){
        Result result = new Result();
        result.setCode(100);
        result.setMsg(msg);
        return result;
    }
}
