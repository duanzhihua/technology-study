package cn.guxiangfly.test1;

import cn.guxiangfly.config.TestConfig;
import cn.guxiangfly.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/10/30 17:40
 */
public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
        Person bean = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        System.out.println("=============");
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        Object colorFactoryBean = annotationConfigApplicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean);
        Object colorFactoryBean2 = annotationConfigApplicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean2);
    }
}
