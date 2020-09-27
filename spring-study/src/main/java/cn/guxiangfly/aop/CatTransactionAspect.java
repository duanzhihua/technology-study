package cn.guxiangfly.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author guxiang02
 * @Date 2020/9/25
 **/
@Aspect
public class CatTransactionAspect {

    @Pointcut("@annotation(cn.guxiangfly.aop.CatTransaction)")
    public void pointCut() {

    }

    @Around(value="pointCut()&& @annotation(catTransaction)")
    public void logAround(ProceedingJoinPoint joinPoint, CatTransaction catTransaction){

        String name = joinPoint.getSignature().getName();
        System.out.println("=ProceedingJoinPoint==start:" +name);
        try {
            joinPoint.proceed();
        }catch (Throwable e){
            throw new RuntimeException(e);
        }
        System.out.println("=ProceedingJoinPoint==end:" +name);
    }
}
