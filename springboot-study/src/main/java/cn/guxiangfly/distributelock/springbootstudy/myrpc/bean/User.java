package cn.guxiangfly.distributelock.springbootstudy.myrpc.bean;

import java.io.Serializable;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 20:50
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1557677039349530215L;

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
