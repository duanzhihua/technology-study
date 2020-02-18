package cn.guxiangfly.test1;

import cn.guxiangfly.config.MainConfigAutowired;
import cn.guxiangfly.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author guxiang02
 * @Date 2019/12/19 09:30
 **/
public class IOCTest_Autowired {

    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfigAutowired.class);
        BookService bookService = ctx.getBean(BookService.class);
        System.out.println(bookService);
        ctx.close();
    }
}
