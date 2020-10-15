package test;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author guxiang02
 * @Date 2020/6/10
 **/
public class TestSomethingDealCTNotParse {

    public static String filePathProd  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/a2.txt";
    public static String filePathTest  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/在线poiId-测试.txt";


    public static void main(String[] args)  {
        try {


            ArrayList<String> poiId_test = new ArrayList<String>();
            ArrayList<String> poiId_prod = new ArrayList<String>();

            BufferedReader in = new BufferedReader(new FileReader(filePathProd));
            String str;
            while ((str = in.readLine()) != null) {
             //   System.out.println(str);
                int i = str.indexOf("dealId=");
                String substring = str.substring(i+7);
                poiId_prod.add(substring);
            }
            in.close();


            List<String> collect = poiId_prod.stream().distinct().collect(Collectors.toList());
            System.out.println(collect);
            System.out.println(collect.size());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
