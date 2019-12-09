package cn.guxiangfly.distributelock.springbootstudy.myrpc.v1;

import cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.info.RpcInfo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 21:16
 */
public class TestSocketClient {
    public static void main(String[] args) throws IOException {

        RpcInfo rpcInfo = new RpcInfo();
        rpcInfo.setPackageName("cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.dao");
        rpcInfo.setClazzName("UserDaoImpl");
        rpcInfo.setMethodName("queryOrder");
        Object[] object = new Object[]{"guxiangv1"};
        rpcInfo.setParams(object);
        Socket socket = new Socket("127.0.0.1",6666);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(rpcInfo);
        objectOutputStream.flush();
        objectOutputStream.close();

    }
}
