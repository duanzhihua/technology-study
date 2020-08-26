package classloadstudy;

import org.junit.Test;

public class ClassLoaderTest{

    @Test
    public void testLoad() throws Exception{
        SingleTonTest singleTonTest = SingleTonTest.getSingleTonTest();
        System.out.println(SingleTonTest.count1);
        System.out.println(SingleTonTest.count2);
    }
}