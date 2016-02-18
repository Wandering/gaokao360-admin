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
    @Before("execution(* cn.thinkjoy.gaokao360.service.differentiation..*(..))||execution(* cn.thinkjoy.common.service..*(..))" +
            "")
    public void switchDB(JoinPoint jionpoint)
    {
        System.out.println("当前类："+this.getClass().getName()+"当前线程="+Thread.currentThread().getId()+"当前切入点"+jionpoint+"访问清理，这里清理状态");
        CustomerContextHolder.clearContextType();
        if(matchPackageType(jionpoint)){
            try {
                System.out.println("当前类："+this.getClass().getName()+"当前线程="+Thread.currentThread().getId()+"当前切入点"+jionpoint);
                CustomerContextHolder.setContextType(UserAreaContext.getCurrentUserArea());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @After("execution(* cn.thinkjoy.gaokao360.service.differentiation..*(..))||execution(* cn.thinkjoy.common.service..*(..))")
    public void switchDBBack(JoinPoint jionpoint)
    {
        System.out.println("当前类："+this.getClass().getName()+"当前线程="+Thread.currentThread().getId()+"当前切入点"+jionpoint+"清理前");
        if(matchPackageType(jionpoint)&&jionpoint.getSignature().getName().equals("getDao")){
            System.out.println("当前类："+this.getClass().getName()+"当前线程="+Thread.currentThread().getId()+"当前切入点"+jionpoint+"未清理"+CustomerContextHolder.getContextType()+"Area："+UserAreaContext.getCurrentUserArea());
            return;
        }
        System.out.println("当前类："+this.getClass().getName()+"当前线程="+Thread.currentThread().getId()+"当前切入点"+jionpoint+"清理后");
        CustomerContextHolder.clearContextType();
    }

    @Before("execution(* cn.thinkjoy.gaokao360.service.common..*(..)))||execution(* cn.thinkjoy.common.managerui.service..*(..)))")
    public void switchDBclear(JoinPoint jionpoint)
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

    private String getArea() throws Exception{
        try {
            Object id = UserContext.getCurrentUser().getId();
            String area = permissionExService.getUserAreaByUserId(id);
            return area;
        }catch (Exception e){
            throw new Exception("当前用户为空");
        }
    }
}
