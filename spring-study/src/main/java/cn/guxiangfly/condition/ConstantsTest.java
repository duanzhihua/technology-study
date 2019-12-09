package cn.guxiangfly.condition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/2 20:15
 */
public class ConstantsTest {
    public static final String AA = "aa";
    public static final List<String>  LIST= new ArrayList<>();
    static {
        System.out.println("ConstantsTest1 加载了");
        LIST.add("aaa");
        LIST.add("ccccc");
        LIST.add("bbb");
    }
}
