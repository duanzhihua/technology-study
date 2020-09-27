package cn.guxiangfly.test1;

import cn.guxiangfly.aop.MathCalculator;
import cn.guxiangfly.aop.PlusCalculator;
import cn.guxiangfly.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {
	

	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
		
		//1、不要自己创建对象
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		
		mathCalculator.div(1, 1);

		
		applicationContext.close();
	}



	@Test
	public void test02(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

		PlusCalculator bean = applicationContext.getBean(PlusCalculator.class);
		bean.test_aop_01();

		applicationContext.close();
	}

}