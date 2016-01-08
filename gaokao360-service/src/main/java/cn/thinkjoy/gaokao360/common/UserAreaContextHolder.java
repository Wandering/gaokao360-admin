package cn.thinkjoy.gaokao360.common;

import cn.thinkjoy.common.utils.UserContext;
import cn.thinkjoy.gaokao360.service.common.ex.IPermissionExService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2016/1/7.
 */
@Component
@Aspect
public class UserAreaContextHolder {

    @Autowired
    private IPermissionExService permissionExService;

    @After("execution(void cn.thinkjoy.common.managerui.iauth.client.token.storage.RedisTokenStore.store(String,cn.thinkjoy.common.managerui.iauth.core.token.Token))")
    public void setUserArea(JoinPoint jionpoint)
    {
        try{
            Object id = UserContext.getCurrentUser().getId();
            String area= permissionExService.getUserAreaByUserId(id);
            UserAreaContext.setCurrentUserArea("jay",area);
        }catch (Exception e){
            System.out.println("出错了！！！！");
        }
    }

}
