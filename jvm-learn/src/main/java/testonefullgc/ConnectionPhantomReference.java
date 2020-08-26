package testonefullgc;

import com.mysql.jdbc.ConnectionImpl;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.sql.SQLException;

public class ConnectionPhantomReference {
    private byte[] bytes = new  byte[1024*10];

}