package classloadstudy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Author guxiang02
 * @Date 2020/5/19
 **/
public class GCRootTest {
    public static void main(String[] args) {
        List<Object> numList = new ArrayList<>();
        Date birth = new Date();
        for (int i = 0; i < 100; i++) {
            numList.add(String.valueOf(i));
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("数据添加完成，请操作");
        new Scanner(System.in).next();
        numList= null;
        birth = null;
        System.out.println("numList birth 已经置空");
        new Scanner(System.in).next();
        System.out.println("结束");
    }
}
