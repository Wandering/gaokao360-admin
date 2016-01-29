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

    private String area;
//    @Pointcut("execution(* cn.thinkjoy.zgk.remote..*(..))")
//    private void fromAnalysisRequestT(){}

    /**
     * 将对象转为requestT参数   在对象出来之后反转为reqest
     * @param jionpoint
     * @return
     */
    @Around("execution(* cn.thinkjoy.zgk.remote..*(..))")
    public Object switchDB(ProceedingJoinPoint jionpoint)throws Exception,Throwable
    {
        String methodName = jionpoint.getSignature().getName();
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

    private boolean hasArea(Map<String,Object> map){
        boolean boo=false;
        boo= map.containsKey("area");
        if(boo)setArea(map);
        return boo;
    }

    private void setArea(Map map){
        try {
            setArea(map.get("area").toString());
        }catch (NullPointerException e){

        }
    }

    private Object adminHandle(ProceedingJoinPoint jionpoint) throws Throwable{
        Object[] objects=jionpoint.getArgs();
        try {
            Map<String,Object> beforeMap = (Map<String,Object>) objects[0];
            this.hasArea(beforeMap);
            simpleCloud.setArea(this.area);
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
            String area=simpleCloud.getCloudArea();
            if(area!=null) {
                beforeMap.put("area", area);
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
