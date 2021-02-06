package cn.guxiangfly.distributelock.springbootstudy.lambdatest;

/**
 * @Author guxiang02
 * @Date 2021/1/30
 **/
public class Demo {

    public static void show(MyFunctionalInterface myFunctionalInterface){
        myFunctionalInterface.method();
    }

    public static void main(String[] args) {
        show(new MyFunctionalInterface() {
            @Override
            public void method() {
                System.out.println("good");
            }
        });

        show(()->System.out.println("good2"));


    }
}
