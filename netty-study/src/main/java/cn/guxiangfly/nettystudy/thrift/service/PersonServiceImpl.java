package cn.guxiangfly.nettystudy.thrift.service;

import cn.guxiangfly.nettystudy.thrift.generated.DataException;
import cn.guxiangfly.nettystudy.thrift.generated.Person;
import cn.guxiangfly.nettystudy.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @author guxiang (guxiang@rd.netease.com)
 * @date 11/25/2019 11:28 AM
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUserName(String username) throws DataException, TException {
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("get client params:");
        System.out.println(person.getAge());
        System.out.println(person.getUsername());
        System.out.println(person.isMarried());
    }
}
