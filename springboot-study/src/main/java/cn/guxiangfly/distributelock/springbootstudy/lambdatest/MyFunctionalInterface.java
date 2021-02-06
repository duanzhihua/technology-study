package cn.guxiangfly.distributelock.springbootstudy.lambdatest;

/**
 * @Author guxiang02
 * @Date 2021/1/30
 * FunctionalInterface
 *      检测是否是是一个函数式接口
 *          检测是否有且仅有一个抽象方法
 **/
@FunctionalInterface
public interface MyFunctionalInterface {

    //定义一个抽象方法
    public abstract void method();

   // public void method2();
}
