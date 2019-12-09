package cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 22:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcClazz {
    
}
