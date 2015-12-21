package cn.thinkjoy.test1;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;

/**
 * Created by admin on 2015/12/19.
 */
public class Test {
    static  String str = "[{id:1,sectionName:123,sectionSort:123,isAccept:123,fileUrl:123},{id:1,sectionName:123,sectionSort:123,isAccept:123,fileUrl:123}]";
    public static void main(String[] args){
        try {
            Object obj = JSON.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
