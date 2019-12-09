package cn.guxiangfly.nettystudy.thrift;

import cn.guxiangfly.nettystudy.thrift.generated.PersonService;
import cn.guxiangfly.nettystudy.thrift.service.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 2019/11/19 15:22
 */
public class ThriftServer {
    public static void main(String[] args) throws Exception{
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg= new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        THsHaServer server = new THsHaServer(arg);
        System.out.println("Thrift server start :");
        server.serve();
    }
}
