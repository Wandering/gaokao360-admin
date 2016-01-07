package cn.thinkjoy.gaokao360.common;


/**
 * Created by liusven on 16/1/6.
 */
public class SwitchDataSourceHandler {

    public void switchDB()
    {
        CustomerContextHolder.setContextType("gd");
    }

    public void switchDBBack()
    {
        CustomerContextHolder.clearContextType();
    }
}
