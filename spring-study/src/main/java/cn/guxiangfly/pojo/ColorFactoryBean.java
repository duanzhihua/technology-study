package cn.guxiangfly.pojo;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/8 17:51
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean -- getObject");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}