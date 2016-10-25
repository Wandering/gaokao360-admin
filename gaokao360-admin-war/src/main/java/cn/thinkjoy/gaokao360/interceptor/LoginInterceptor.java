package cn.thinkjoy.gaokao360.interceptor;

import cn.thinkjoy.common.utils.UserContext;
import cn.thinkjoy.gaokao360.common.UserAreaContext;
import cn.thinkjoy.gaokao360.service.common.ex.IPermissionExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2016/2/18.
 */
public class LoginInterceptor  extends HandlerInterceptorAdapter {
    @Autowired
    private IPermissionExService permissionExService;
    public LoginInterceptor() { }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserAreaContext.removeCurrentUseraArea();
        try{
            if(getArea()!=null) {
                UserAreaContext.setCurrentUserArea(getArea());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return super.preHandle(request, response, handler);
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
