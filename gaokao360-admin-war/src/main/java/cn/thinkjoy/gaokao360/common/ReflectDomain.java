package cn.thinkjoy.gaokao360.common;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yyp on 15-8-28.
 */
public class ReflectDomain <T>{
    Class clz;
    public ReflectDomain(T t){
        this.clz = t.getClass();
    }
    public Object getAllField(Map<String,Object> map) throws Exception{
        T tt = (T)clz.newInstance();
        Field[] fields =  clz.getDeclaredFields();
        for(Field field:fields){
            String fieldType=field.getGenericType().toString();
            if(map.get(field.getName())!=null){
                clz.getMethod(getSetMethod(field.getName()),new Class[]{Class.forName(fieldType.substring(6,fieldType.length()))}).invoke(tt, map.get(field.getName()));
            }

        }
        if(clz.getSuperclass()!=null && clz.getSuperclass().getName()!="Object") {
            Field[] superFields = clz.getSuperclass().getDeclaredFields();
            for (Field field : superFields) {
                String fieldType = field.getGenericType().toString();
                if (map.get(field.getName()) != null) {
                    clz.getMethod(getSetMethod(field.getName()), new Class[]{Class.forName(fieldType.substring(6, fieldType.length()))}).invoke(tt, map.get(field.getName()));
                }
            }
        }
        if(clz.getSuperclass()!=null && clz.getSuperclass().getSuperclass()!=null && clz.getSuperclass().getSuperclass().getName()!="Object") {
            Field[] superSuperFields = clz.getSuperclass().getDeclaredFields();
            for (Field field : superSuperFields) {
                String fieldType = field.getGenericType().toString();
                if (map.get(field.getName()) != null) {
                    clz.getMethod(getSetMethod(field.getName()), new Class[]{Class.forName(fieldType.substring(6, fieldType.length()))}).invoke(tt, map.get(field.getName()));
                }
            }
        }
        return tt;
    }

    public String getSetMethod(String name){
        return "set"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
    }
    public String getGetMethod(String name){
        return "set"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
    }

}
