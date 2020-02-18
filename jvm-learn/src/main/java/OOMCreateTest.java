import java.util.HashMap;

/**
 * @Author guxiang02
 * @Date 2019/12/25 19:13
 **/
public class OOMCreateTest {

    public static void main(String[] args) throws InterruptedException {
        HashMap map = new HashMap();
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            map.put(i,new byte[1024*1024]);
        }
    }
}
