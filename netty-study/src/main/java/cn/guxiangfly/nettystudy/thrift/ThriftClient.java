package cn.guxiangfly.nettystudy.thrift;

import cn.guxiangfly.nettystudy.thrift.generated.Person;
import cn.guxiangfly.nettystudy.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 11/25/2019 8:19 PM
 */
public class ThriftClient {
    public static void main(String[] args) {
        TFramedTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TCompactProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);


        try {
            transport.open();
            Person person = client.getPersonByUserName("张三");
            System.out.println(person.toString());

            Person person2 = new Person();
            person2.setAge(1);
            person2.setMarried(false);
            person2.setUsername("guxiang");

            client.savePerson(person2);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            transport.close();
        }
    }
}
