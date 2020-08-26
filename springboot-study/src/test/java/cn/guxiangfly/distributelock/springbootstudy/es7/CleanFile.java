package cn.guxiangfly.distributelock.springbootstudy.es7;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CleanFile{
   public static String filePath  = ElasticSearchApplicationTest.PRE_FILE + "product.json";
   public static String filePath_new  = ElasticSearchApplicationTest.PRE_FILE + "product_new2.json";

    public static void main(String[] args)  {
        try {
            FileOutputStream out = new FileOutputStream(filePath_new);
            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
            BufferedWriter bufWrite = new BufferedWriter(outWriter);


            ArrayList<String> strings = new ArrayList<>();

            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
                strings.add(str);
            }

            for (int i = 0; i < strings.size(); i++) {
                str = strings.get(i);
                String nextLine = "";
                if (i == strings.size()-1){
                     nextLine = "";
                }else {
                    nextLine = strings.get(i+1);
                }

                if (strategyIsWrite(strings.get(i))){

                    if (nextLine.trim().contains("}") ){

                        if (str == null){
                            str = "";
                        }

                        if (str.trim().endsWith(",")){
                            str = str.replace(",","");
                        }else if (!str.endsWith("\"")){
                            str = str + "\"";
                        }

                    }
                    bufWrite.write(str + "\r\n");
                }
            }

            bufWrite.close();
            outWriter.close();
            out.close();


        } catch (IOException e) {
        }
    }


    public static  boolean strategyIsWrite(String str){
        if (str.contains("]")
        || str.contains("[")
        || str.contains("}")
        || str.contains("{")
        ){
            return true;
        }

        if (!str.contains(":")){
            return false;
        }

        return true;
    }

}