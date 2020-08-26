package classloadstudy;

/**
 * @Author guxiang02
 * @Date 2020/5/18
 **/
public class SingleTonTest {

    public static SingleTonTest singleTonTest = new SingleTonTest();
    public static int count1;
    public static int count2 = 0;


    private SingleTonTest(){
        count1++;
        count2++;
    }

    public static SingleTonTest getSingleTonTest(){
        return singleTonTest;
    }
}