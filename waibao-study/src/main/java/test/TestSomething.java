package test;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author guxiang02
 * @Date 2020/6/10
 **/
public class TestSomething {

    public static String filePathProd  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/在线poiId-线上.txt";
    public static String filePathTest  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/在线poiId-测试.txt";


    public static void main(String[] args)  {
        try {


            ArrayList<String> poiId_test = new ArrayList<String>();
            ArrayList<String> poiId_prod = new ArrayList<String>();

            BufferedReader in = new BufferedReader(new FileReader(filePathProd));
            String str;
            while ((str = in.readLine()) != null) {
             //   System.out.println(str);
                poiId_prod.add(str);
            }
            in.close();


            BufferedReader in2 = new BufferedReader(new FileReader(filePathTest));
            while ((str = in2.readLine()) != null) {
              //  System.out.println(str);
                poiId_test.add(str);
            }
            in2.close();

            System.out.println("=========diff==========");

            Sets.SetView<String> difference = Sets.difference(Sets.newHashSet(poiId_prod), Sets.newHashSet(poiId_test));
            System.out.println(difference.size());

            for (String s : difference) {
                System.out.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
