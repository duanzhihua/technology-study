package cn.guxiangfly.distributelock.springbootstudy.controller;

import cn.guxiangfly.distributelock.springbootstudy.helper.CacheHelper;
import cn.guxiangfly.distributelock.springbootstudy.lock.RedisLock;
import cn.guxiangfly.distributelock.springbootstudy.myrpc.bean.Result;
import cn.guxiangfly.distributelock.springbootstudy.myrpc.bean.User;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guxiang02
 * @Date 2020/9/27
 **/
@RestController
public class RedisCacheController {


    @Autowired
    CacheHelper cacheHelper;

    @Autowired
    RedisLock redisLock;


    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.0001);


    @GetMapping("/getUserById")
    public Result getUserById(Integer id) {

        // 布隆过滤器过滤 解决缓存穿透问题
        if (!bloomFilter.mightContain(id)) {
            return Result.error("无数据");
        }

        //查询缓存中是否有数据,如果有数据就返回数据。
        User user = cacheHelper.get(String.valueOf(id));
        if (user != null) {
            return Result.success(user);
        }


        // 使用分布式锁解决 缓存击穿问题
        redisLock.lock(String.valueOf(id));
        try {

            //查询缓存中是否有数据,如果有数据就返回数据。
            User user2 = cacheHelper.get(String.valueOf(id));
            if (user2 != null) {
                return Result.success(user2);
            }
            //2.缓存中没有数据，就去db中查询
            User userInDdById = getUserInDdById(id);
            if (userInDdById != null) {
                cacheHelper.setUserToCache(String.valueOf(id), user, 30);
                return Result.success(userInDdById);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisLock.unLock(String.valueOf(id));
        }


        return Result.error("无数据");
    }


    public User getUserInDdById(Integer id) {
        return new User();
    }
}
