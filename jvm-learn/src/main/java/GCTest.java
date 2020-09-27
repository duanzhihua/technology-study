import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Author guxiang02
 * @Date 2020/9/23
 **/
public class GCTest {
    public static void main(String[] args) {
        System.out.println("why4");
        ArrayList<String> list = new ArrayList<String>();
        list.add("url1");
        list.add("url2");
        list.add("url3");

        WeakReference<ArrayList<String>> ref = new WeakReference<ArrayList<String>>(list);

        list = null;

        ArrayList<String> list2 = ref.get();
        System.out.println("before gc:"+list2);

        int[] data = new int[102500000];

        System.gc();

        ArrayList<String> list3 = ref.get();

        System.out.println("after gc:" + list3);

        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
    }
}
