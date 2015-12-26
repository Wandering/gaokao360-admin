package cn.thinkjoy.gaokao360.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 2015-09-17.
 * 与反射有关的工具方法  目前有list排序，obj转map map转obj
 * objectRemoveRepeat(List list) 对list集合进行去重
 * List objectSort(List list,String key)对象排序
 * void removeListNull(List list) 删除List中的空值
 * boolean isExistKeyInObj(Object o,String key) 判断key在对象中是否存在
 * boolean isObjNull(Object o) 判断对象是不是null
 * Map<String,Object> getMap(Object obj) throws Exception 将对象转换成map
 * Object getObj(Map<String,Object> map,Object obj) throws Exception 将map转换成对象（把值赋到对象相应的key里）
 * void quickSortByList(List list, int lo0, int hi0,String key) 将list按照key排序 如果key不存在会报异常 上层抽象为objectSort key为空不会排序但也不会炮异常
 */
public class DomainReflex {


    /**
     * 将父类对象的值赋给子类对象
     * @param o 父类对象
     * @param dto 子类对象
     * @return 子类对象
     * @throws Exception 获取方法异常，赋值异常
     */
    public static Object ObjToDTO(Object o,Object dto) throws Exception {
        Class clz=o.getClass();
        Class clzDto=dto.getClass();
        do{
            Field[] fields =  clz.getDeclaredFields();
            for(Field field : fields){
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if(isStatic)continue;
                String key = field.getName();
                Object value = clz.getMethod(getGetMethod(field.getName())).invoke(o);
                setValueToObj(clz, field, getParamType(field),dto,value);
            }
            clz=clz.getSuperclass();
        }while (clz!=null && clz.getName()!="Object");
        return dto;
    }

    /**
     * 将map参数填写到对象中
     * @param clz
     * @param field
     * @param fieldclz
     * @param obj
     * @throws Exception
     */
    private static void setValueToObj(Class clz,Field field,Class fieldclz,Object obj,Object value) throws Exception {
        String name = field.getName();
        if(value==null){
            return;
        }else if(INTEGER.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Integer.valueOf(value.toString()));
        }else if(STRING.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, String.valueOf(value.toString()));
        }else if(LONG.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Long.valueOf(value.toString()));
        }else if(BYTE.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Byte.valueOf(value.toString()));
        }else if(FLOAT.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Float.valueOf(value.toString()));
        }else if(DOUBLE.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Double.valueOf(value.toString()));
        }else if(OBJECT.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, value.toString());
        }else{
            setValueToObjExt(clz, field, fieldclz, obj, value);
        }

    }
    protected static void setValueToObjExt(Class clz,Field field,Class fieldclz,Object obj,Object value){
        return;
    }
    public static List objectRemoveRepeat(List list){
        if(list==null)return null;
        if(list.size()==0)return list;
        for (int i = 0; i < list.size(); i++)  //外循环是循环的次数
        {
            for (int j = list.size() - 1 ; j > i; j--)  //内循环是 外循环一次比较的次数
            {

                if (list.get(i).hashCode() == list.get(j).hashCode())
                {
                    list.remove(j);
                }

            }
        }
        return list;
    }

    public static List listReversal(List list){
        List list1 = new ArrayList();
        if(list!=null && list.size()!=0){
            for(int i = list.size()-1; i>=0;i--){
                list1.add(list.get(i));
            }
        }
        return list1;
    }

    /**
     * list排序算法 对数据进行排序运算任意对象，只能对long/int/double/float排序
     * @param list 数据集合
     * @param key 需要排序的列
     * @return
     */
    public static List objectSort(List list,String key){
        if(isListEmptyOrNull(list))return list;
        removeListNull(list);
        if(!isExistKeyInObj(list.get(0),key))return list;
        quickSortByList(list, 0, list.size()-1,key,true);
        return list;
    }

    /**
     * list排序算法 对数据进行排序运算任意对象，只能对long/int/double/float排序
     * @param list 数据集合
     * @param key 需要排序的列
     * @return
     */
    public static List objectSort(List list,String key,boolean transfer){
        if(isListEmptyOrNull(list))return list;
        removeListNull(list);
        if(!isExistKeyInObj(list.get(0),key))return list;
        quickSortByList(list, 0, list.size()-1,key,transfer);
        return list;
    }
    /**
     * 删除List中的空值
     * @param list
     */
    public static void removeListNull(List list){
        if(isListEmptyOrNull(list))return;
        for(int i=0;i<list.size();i++){
            if(isObjNull(list.get(i)))
                list.remove(i);
        }
    }

    /***
     * 判断key是否存在于对象中
     * @param o
     * @param key
     * @return
     */
    public static boolean isExistKeyInObj(Object o,String key){
        Class clz=o.getClass();
        do{
            Field[] fields =  clz.getDeclaredFields();
            for(Field field : fields){
                if(key.equals(field.getName()))
                    return true;

            }
            clz=clz.getSuperclass();
        }while (clz!=null && clz.getName()!="Object");
        return false;
    }

    /**
     * 判断对象是不是null
     * @param o
     * @return
     */
    public static boolean isObjNull(Object o){
        if(o==null)
            return true;
        return false;
    }

    public static boolean isListEmptyOrNull(List list){
        if(list==null || list.size()==0)return true;
        return false;
    }
    /**
     * 把object转换成map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String,Object> getMap(Object obj) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Class clz=obj.getClass();
        do{
            Field[] fields =  clz.getDeclaredFields();
            for(Field field : fields){
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if(isStatic)continue;
                String key = field.getName();
                Object value = clz.getMethod(getGetMethod(field.getName())).invoke(obj);
                map.put(key,value);
            }
            clz=clz.getSuperclass();
        }while (clz!=null && clz.getName()!="Object");
        return map;
    }

    /**
     * 把map转换成obj，以对象为准
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object getObj(Map<String,Object> map,Object obj) throws Exception {
        Class clz=obj.getClass();
        do{
            Field[] fields =  clz.getDeclaredFields();
            for(Field field : fields){
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                if(isStatic)continue;
                if (map.containsKey(field.getName())){
                    if(map.get(field.getName())!=null)
                        setObjectValue(clz,field ,getParamType(field),obj,map);
                }
            }
            clz=clz.getSuperclass();
        }while (clz!=null && clz.getName()!="Object");
        return obj;
    }
    private static String getSetMethod(String name){
        return "set"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
    }
    private static String getGetMethod(String name){
        return "get"+name.substring(0,1).toUpperCase()+name.substring(1, name.length());
    }

    private static Class getParamType(Field field) throws ClassNotFoundException {
        String fieldType =null;
        Type type=field.getGenericType();
        if("T".equals(type.toString())) // 如果是泛型参数的类型  当前只能判断T
        {
            //定位Object类型
            return Object.class;
        }else {
            fieldType = field.getGenericType().toString();
        }
        String[] str = fieldType.split(" ");
        Class clz = Class.forName(str[1]);

        return clz;
    }

    /**
     * 将map参数填写到对象中
     * @param clz
     * @param field
     * @param fieldclz
     * @param obj
     * @param map
     * @throws Exception
     */
    private static void setObjectValue(Class clz,Field field,Class fieldclz,Object obj,Map<String,Object> map) throws Exception {
        String name = field.getName();
        if(INTEGER.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Integer.valueOf(getMapValue(field,map).toString()));
        }else if(STRING.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, String.valueOf(getMapValue(field,map).toString()));
        }else if(LONG.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Long.valueOf(getMapValue(field,map).toString()));
        }else if(BYTE.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Byte.valueOf(getMapValue(field,map).toString()));
        }else if(FLOAT.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Float.valueOf(getMapValue(field,map).toString()));
        }else if(DOUBLE.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, Double.valueOf(getMapValue(field,map).toString()));
        }else if(OBJECT.equals(fieldclz.getName())){
            clz.getMethod(getSetMethod(name), new Class[]{fieldclz}).invoke(obj, getMapValue(field,map).toString());
        }else{
            setObjectValueExt(clz, field, fieldclz, obj, map);
        }

    }

    private static Object getMapValue(Field field,Map<String,Object> map){
        return map.get(field.getName());
    }
    /**
     * 扩展非基本类型
     * @param clz
     * @param field
     * @param fieldclz
     * @param obj
     * @param map
     */
    protected static void setObjectValueExt(Class clz,Field field,Class fieldclz,Object obj,Map<String,Object> map){
        return;
    }


    /**快速排序方法（列表）*/
    /**
     *
     * @param list
     * @param lo0
     * @param hi0
     * @param key
     * @param transfer
     */
    public static void quickSortByList(List list, int lo0, int hi0,String key,boolean transfer) {
        int lo = lo0;
        int hi = hi0;
        if (lo >= hi)
            return;

        //transfer确定指针方向的逻辑变量
        while (lo != hi) {
            if (comparativeSize(list.get(lo), list.get(hi),key)) {
                //交换
                Object temp = list.get(lo);
                list.set(lo, list.get(hi));
                list.set(hi, temp);
                //决定下标移动，还是上标移动
                transfer = (transfer == true) ? false : true;
            }

            //将指针向前或者向后移动
            if(transfer)
                hi--;
            else
                lo++;
        }

        //将数组分开两半，确定每个数字的正确位置
        lo--;
        hi++;
        quickSortByList(list, lo0, lo,key,transfer);
        quickSortByList(list, hi, hi0,key,transfer);
    }

    /**
     *  得到执行结果
     * @param obj
     * @param key
     * @return
     */
    private static Object getObjMethod(Object obj,String key){
        Class clz=obj.getClass();
        Object value =null;
        try {
            value =clz.getMethod(getGetMethod(key)).invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private static boolean comparativeSize(Object o1,Object o2,String key){
        boolean flag=false;
        if(INTEGER.equals(getObjMethod(o1,key).getClass().getName())){
            flag=(Integer)getObjMethod(o1,key) > (Integer)getObjMethod(o2,key);
        }else if(LONG.equals(getObjMethod(o1,key).getClass().getName())){
            flag=(Long)getObjMethod(o1,key) > (Long)getObjMethod(o2,key);
        }else if(DOUBLE.equals(getObjMethod(o1,key).getClass().getName())){
            flag=(Double)getObjMethod(o1,key) > (Double)getObjMethod(o2,key);
        }else if(FLOAT.equals(getObjMethod(o1,key).getClass().getName())){
            flag=(Float)getObjMethod(o1,key) > (float)getObjMethod(o2,key);
        }else{
            //调用扩展
            flag=querySortEx(getObjMethod(o1,key),getObjMethod(o2,key));
        }
        return flag;
    }

    /**
     *  扩展排序
     * @param obj1
     * @param obj2
     * @return
     */
    protected static boolean querySortEx(Object obj1,Object obj2){
        return false;
    }

    private static String INTEGER = "java.lang.Integer";
    private static String STRING = "java.lang.String";
    private static String LONG = "java.lang.Long";
    private static String BYTE = "java.lang.Byte";
    private static String FLOAT = "java.lang.Float";
    private static String DOUBLE = "java.lang.Double";
    private static String OBJECT = "java.lang.Object";

}
