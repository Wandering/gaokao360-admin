package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.cloudstack.cache.RedisRepositoryFactory;
import cn.thinkjoy.common.managerui.domain.UserDatagroup;
import cn.thinkjoy.common.utils.UserContext;

/**
 * Created by admin on 2016/1/7.
 */
public class UserAreaContext {
//    RedisRepositoryFactory
    public static String getCurrentUserArea(){
//        return UserContext.getCurrentUser();
        return null;
    }

    public static void setCurrentUserArea(String area,String key){
        try {
            RedisRepositoryFactory.getRepository("zgk",key,area);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 应该显示调用
     */
    public static void removeCurrentUserArea() {

//        RedisRepositoryFactory.remove();
    }


}
