package testonefullgc;

import com.mysql.jdbc.ConnectionImpl;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.sql.Time;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author guxiang02
 * @Date 2020/5/19
 **/
public class ConcurrentHashMapTest {
    public static void main(String[] args) throws Exception{
//        ConcurrentHashMap<Long, ConnectionPhantomReference> longConnectionPhantomReferenceConcurrentHashMap = new ConcurrentHashMap<Long, ConnectionPhantomReference>();
//        for (int i = 0; i < 10000000; i++) {
//            longConnectionPhantomReferenceConcurrentHashMap.put((long) i,new ConnectionPhantomReference());
//            Thread.sleep(10);
//        }


        ConcurrentHashMap<ConnectionPhantomReferenceStatic, ConnectionPhantomReferenceStatic> longConnectionPhantomReferenceStaticConcurrentHashMap = new ConcurrentHashMap<ConnectionPhantomReferenceStatic, ConnectionPhantomReferenceStatic>();
        for (int i = 0; i < 10000000; i++) {
            ConnectionPhantomReferenceStatic connectionPhantomReferenceStatic = new ConnectionPhantomReferenceStatic();
            longConnectionPhantomReferenceStaticConcurrentHashMap.put(connectionPhantomReferenceStatic,connectionPhantomReferenceStatic);
            Thread.sleep(10);
        }
    }

    static class ConnectionPhantomReferenceStatic {
        public static byte[] bytes = new  byte[1024*100];
    }
}
