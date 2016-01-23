//package cn.thinkjoy;
//
//import cn.thinkjoy.zgk.domain.BaseDomain;
//
///**
// * Created by admin on 2015/12/9.
// */
//public class Test {
//
//    public static void main(String[] args){
//        BaseDomain baseDomain = new BaseDomain();
//        baseDomain.setId(1);
//        Long start=System.currentTimeMillis();
//        for(long i=0;i<500000;i++){
//            baseDomain.getId();
//        }
//        Long now=System.currentTimeMillis();
//        System.out.println("直接调用："+(now-start));
//        start=System.currentTimeMillis();
//        for(long i=0;i<500000;i++){
//            Class clz=baseDomain.getClass();
//            try {
//                clz.getMethod("getId");
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//        now=System.currentTimeMillis();
//        System.out.println("反射调用："+(now-start));
//        start=System.currentTimeMillis();
//        Class clz1=baseDomain.getClass();
//        for(long i=0;i<500000;i++){
//            try {
//                clz1.getMethod("getId");
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//        now=System.currentTimeMillis();
//        System.out.println("反射调用2："+(now-start));
//    }
//
//}
