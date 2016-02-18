//package cn.thinkjoy.gaokao360.common;
//
//import cn.thinkjoy.common.utils.UserContext;
//import cn.thinkjoy.gaokao360.service.common.ex.IPermissionExService;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * Created by admin on 2016/1/7.
// */
//@Component
//@Aspect
//public class UserAreaContextHandler {
//
//    @Autowired
//    private IPermissionExService permissionExService;
//    /**用户所属区域**/
//    @After("execution(* cn.thinkjoy.common.managerui.iauth.client.token.storage.UserStore.postpone(**))")
//    public void setUserArea(JoinPoint jionpoint)
//    {
//        try{
//            if(getArea()!=null) {
//                UserAreaContext.removeCurrentUseraArea();
//                UserAreaContext.setCurrentUserArea(getArea());
//                System.out.println(getArea()+"=================");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @After("execution(void cn.thinkjoy.common.managerui.iauth.client.token.storage.RedisTokenStore.removeToken(**))")
//    public void clearUserArea(JoinPoint jionpoint)
//    {
//         UserAreaContext.removeCurrentUseraArea();
//    }
//
//    private String getArea() throws Exception{
//            try {
//                Object id = UserContext.getCurrentUser().getId();
//                String area = permissionExService.getUserAreaByUserId(id);
//                return area;
//            }catch (Exception e){
//                throw new Exception("当前用户为空");
//            }
//    }
//
//}
