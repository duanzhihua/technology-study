package cn.guxiangfly.distributelock.springbootstudy.myrpc.v1;

import cn.guxiangfly.distributelock.springbootstudy.myrpc.v1.request.ServerRequest;

import java.io.IOException;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/12 21:22
 */
public class TestPublish {
    public static void main(String[] args) throws IOException {
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.publishServer();
    }
}
