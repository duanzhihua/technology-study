package cn.guxiangfly.service;

import cn.guxiangfly.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author guxiang02
 * @Date 2019/12/18 21:36
 **/
@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
