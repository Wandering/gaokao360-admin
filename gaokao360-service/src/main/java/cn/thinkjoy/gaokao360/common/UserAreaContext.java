package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.cloudstack.cache.IRedisRepository;
import cn.thinkjoy.cloudstack.cache.RedisRepositoryFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by admin on 2016/1/7.
 */
@Component
public class UserAreaContext {
    private static IRedisRepository store;

    @PostConstruct
    private void initStore()
    {
        try {
            store = RedisRepositoryFactory.getRepository("zgk", "user", "area");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentUserArea(String key) {
        return store.get(key) + "";
    }

    public static void setCurrentUserArea(String key, String area) {
        store.set(key, area);
    }

}
