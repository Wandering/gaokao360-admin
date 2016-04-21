package cn.thinkjoy.gaokao360.common;


import cn.thinkjoy.common.utils.UserContext;
import cn.thinkjoy.gaokao360.service.common.ex.IPermissionExService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liusven on 16/1/6.
 */
@Component
@Aspect
public class SwitchDataSourceHandler {
    private String regularPackage="cn.thinkjoy.gaokao360.service.differentiation..*(..)";
    @Autowired
    private IPermissionExService permissionExService;
    @Before("execution(* cn.thinkjoy.gaokao360.service.differentiation..*(..))||execution(* cn.thinkjoy.gaokao360.service.baseservice..*(..))" +
            "")
    public void switchDB(JoinPoint jionpoint)
    {
        if(matchPackageType(jionpoint)){
            try {
                CustomerContextHolder.setContextType(UserAreaContext.getCurrentUserArea());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @After("execution(* cn.thinkjoy.gaokao360.service.differentiation..*(..))||execution(* cn.thinkjoy.gaokao360.service.baseservice..*(..))")
    public void switchDBBack(JoinPoint jionpoint)
    {
        CustomerContextHolder.clearContextType();
    }

//    @Before("execution(* cn.thinkjoy.common.service.impl.AbstractBaseService.insertMap(..))")
//    public void switchDBBack2323(JoinPoint jionpoint)
//    {
//        System.out.println(jionpoint+"切入点");
//    }
    /**
     * 正则匹配织入点
     * @return
     */
    public boolean matchPackageType(JoinPoint jionpoint){
        Pattern pattern = Pattern.compile(regularPackage);
        // 获取被调用的类名
        String targetClassName = jionpoint.getTarget().getClass().getName();
        Matcher matcher = pattern.matcher(targetClassName);
        if(matcher.find()){return true;}
        return false;
    }
}
