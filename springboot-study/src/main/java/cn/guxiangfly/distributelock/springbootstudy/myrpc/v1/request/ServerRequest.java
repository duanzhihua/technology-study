package cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 21:18
 */
public class ServerRequest {

    public void publishServer() throws IOException{
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        while (true){
            System.out.println("wait conn");
            Socket accept = serverSocket.accept();
            executorService.execute(new ServerHandler(accept));
            System.out.println("success conn");
        }
    }

}
