package cn.guxiangfly.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 12/29/2019 11:30 PM
 */
public class PlusCalculator {

    @Autowired
    PlusCalculator plusCalculator;



    @CatTransaction
    public void test_aop_01() {
        System.out.println("test_aop_01");
        test_aop_02();
        plusCalculator.test_aop_03();
    }


    @CatTransaction
    public void test_aop_02() {
        System.out.println("test_aop_02");

    }

    @CatTransaction
    public void test_aop_03() {
        System.out.println("test_aop_03");
        test_aop_02();
    }
}
