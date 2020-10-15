package test;

import com.google.common.collect.Sets;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author guxiang02
 * @Date 2020/6/10
 **/
public class TestSomething {

    public static String filePathProd  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/考核目标-old.txt";
    public static String filePathTest  = "/Users/mtdp/dev/ideaworkspace/guxiangwork/technology-study/waibao-study/src/main/resources/考核目标-new.txt";


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


            System.out.println("=========diff2==========");

            getDifferent(poiId_test,poiId_prod);
            System.out.println(difference.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDifferent(List<String> prelist, List<String> curlist) {
        List<String> diff = new ArrayList<String>();

        Map<String,Integer> map = new HashMap<String,Integer>(curlist.size());
        for (String stu : curlist) {
            map.put(stu, 1);
        }
        for (String stu : prelist) {
            if(map.get(stu)!=null)
            {
                map.put(stu, 2);
                continue;
            }
            diff.add(stu);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue()==1)
            {
                diff.add(entry.getKey());
            }
        }
        for(String stu:diff){
            System.out.println(" the differ "+stu);
        }
        return diff;

    }
}
