package cn.guxiangfly.distributelock.springbootstudy.helper;

import cn.guxiangfly.distributelock.springbootstudy.myrpc.bean.User;
import org.springframework.stereotype.Service;

/**
 * @Author guxiang02
 * @Date 2020/9/27
 **/
@Service
public class CacheHelper {
    public User get(String key){
        return new User();
    }


    public void setUserToCache(String key, User user,Integer expireTime){
        return;
    }
}
