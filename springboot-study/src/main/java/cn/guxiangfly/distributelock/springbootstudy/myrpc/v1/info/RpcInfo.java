package cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.info;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 20:41
 */
@Data
public class RpcInfo implements Serializable {

    private static final long serialVersionUID = -7568090212395676650L;
    /**
     * 包名
     * 类名
     * 方法名
     * 参数
     */
    private String packageName;
    private String clazzName;
    private String methodName;
    private Object[] params;
}
