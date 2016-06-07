package cn.thinkjoy.zgk.annotation;


import cn.thinkjoy.zgk.cloudstack.ISimpleCloud;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liusven on 16/1/6.
 */
@Component
@Aspect
public class AreaHandle {

    public static String ADMIN ="zgkadmin";
    public static String FRONT ="zgkfront";
    @Autowired
    private ISimpleCloud simpleCloud;
//    @Pointcut("execution(* cn.thinkjoy.zgk.remote..*(..))")
//    private void fromAnalysisRequestT(){}

    /**
     * 将对象转为requestT参数   在对象出来之后反转为reqest
     * @param jionpoint
     * @return
     */
    @Around("execution(* cn.thinkjoy.zgk.remote..*(..))")
    public Object switchDB(ProceedingJoinPoint jionpoint)throws Throwable
    {
        String methodName = jionpoint.getSignature().getName();
        //无论是否是白名单执行清理数据库操作
        simpleCloud.clearArea();
        //通过白名单判断是否注入省份字段
        if(simpleCloud.hasWhiteList(methodName)) {
            try {
                if (ADMIN.equals(simpleCloud.getCloudProduct())) {
                    return adminHandle(jionpoint);
                } else if (FRONT.equals(simpleCloud.getCloudProduct())) {
                    return frontHandle(jionpoint);
                } else {
                    return jionpoint.proceed();
                }
            } catch (NullPointerException e) {
                throw new Exception("simpleCloud接口没有实现类");
            }
        }else {
            return jionpoint.proceed();
        }
    }


    private Object adminHandle(ProceedingJoinPoint jionpoint) throws Throwable{
        Object[] objects=jionpoint.getArgs();
        try {
            Map<String,Object> beforeMap = (Map<String,Object>) objects[0];
            try {
                simpleCloud.setArea( beforeMap.get("area").toString());
            }catch (NullPointerException e){
                simpleCloud.setArea(null);
            }

        }catch (ClassCastException e){
            LogFactory.getLog(this.getClass()).debug("map转换异常");
        }catch (NullPointerException e){
            throw new Exception("simpleCloud接口没有实现类");
        }finally {
            try {
                return jionpoint.proceed();
            }catch (Throwable throwable){
                throw throwable;
            }
        }
    }

    private Object frontHandle(ProceedingJoinPoint jionpoint) throws Throwable{
        Object[] objects=jionpoint.getArgs();
        try{
            Map<String,Object> beforeMap = (Map<String,Object>) objects[0];
            if(simpleCloud.getCloudArea()!=null) {
                beforeMap.put("area", simpleCloud.getCloudArea());
            }
        }catch (ClassCastException e){
            LogFactory.getLog(this.getClass()).debug("map转换异常");
        }catch (NullPointerException e){
            throw new Exception("simpleCloud接口没有实现类");
        }finally {
            try {
                return jionpoint.proceed();
            }catch (Throwable throwable){
                throw throwable;
            }
        }

    }
}