package cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.request;

import cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.info.RpcInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 21:34
 */
public class ServerHandler implements Runnable {

    private Socket socket;
    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcInfo rpcInfo = (RpcInfo) objectInputStream.readObject();
            Class<?> clazz = Class.forName(rpcInfo.getPackageName() + "." + rpcInfo.getClazzName());

            Class[] paramsClasses = new Class[rpcInfo.getParams().length];
            for (int i = 0; i < paramsClasses.length; i++) {
                paramsClasses[i] = rpcInfo.getParams()[i].getClass();
            }
            Method method = clazz.getMethod(rpcInfo.getMethodName(), paramsClasses);
            method.invoke(clazz.newInstance(),rpcInfo.getParams());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
