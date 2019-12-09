package cn.guxiangfly.config;

import cn.guxiangfly.condition.LinuxCondition;
import cn.guxiangfly.condition.MyImportSelector;
import cn.guxiangfly.condition.WindowsCondition;
import cn.guxiangfly.pojo.Color;
import cn.guxiangfly.pojo.ColorFactoryBean;
import cn.guxiangfly.pojo.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/10/30 17:42
 */
@Conditional({WindowsCondition.class})
@Configuration
@Import({Color.class, MyImportSelector.class})
public class TestConfig {


/*
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean("person")
    public Person person(){
        return new Person(10,"guxiang");
    }
*/

    /**
     * 如果是 linux 注入 linus
     * 如果是 windows  注入 bill
     * @return
     */

    @Bean("bill")
    @Conditional(WindowsCondition.class)
    public Person person01(){
        return new Person(10,"bill");
    }

    @Bean("linus")
    @Conditional(LinuxCondition.class)
    public Person person02(){
        return new Person(10,"linus");
    }

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return  new ColorFactoryBean();
    }

}
