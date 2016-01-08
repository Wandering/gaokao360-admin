package cn.thinkjoy.gaokao360.common;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @Before("execution(* cn.thinkjoy.gaokao360.service.differentiation..*(..))||execution(* cn.thinkjoy.common.service..*(..))")
    public void switchDB(JoinPoint jionpoint)
    {
        if(matchPackageType(jionpoint)){
            CustomerContextHolder.setContextType(UserAreaContext.getCurrentUserArea("jay"));
        }
    }

    @After("execution(* cn.thinkjoy.gaokao360.service.differentiation..*(..))||execution(* cn.thinkjoy.common.service..*(..))")
    public void switchDBBack()
    {
        CustomerContextHolder.clearContextType();
    }

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
