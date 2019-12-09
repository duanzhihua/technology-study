package cn.guxiangfly.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/10/30 21:01
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean b = beanDefinitionRegistry.containsBeanDefinition("cn.guxiangfly.pojo.Blue");
        boolean a = beanDefinitionRegistry.containsBeanDefinition("cn.guxiangfly.pojo.Yellow");

        if (a&&b){
            
        }
    }
}
