package cn.thinkjoy.gaokao360.common.utils;

import cn.thinkjoy.common.exception.BizException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liusven on 15/12/30.
 */
public class WebUtils {

    public static Map<String, Object> saveContentAsHtml(String path, String content){
        String result =null;
        String filename = "gk" + System.currentTimeMillis() + ".html";
        String url = "http://cs-pro.qtonecloud.cn/rest/v1/uploadFile";
        try {
            try {
                FileOutputStream os = new FileOutputStream(path + "/" + filename);
                os.write(content.getBytes("UTF-8"));
                os.close();
            } catch (BizException e) {
                throw new BizException("", e.getLocalizedMessage());
            }catch (IOException e1)
            {}
            FileSystemResource resource = new FileSystemResource(new File(path + "/" + filename));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
            RestTemplate template = new RestTemplate();
            //这里大家可以用其他的httpClient均可以
            param.add("productCode", "gk360");
            param.add("file", resource);
            param.add("bizSystem", "gk360");
            param.add("spaceName ", "gk360");
            param.add("userId ", "gk360");
            param.add("dirId ", "0");

            template.getMessageConverters().add(new FastJsonHttpMessageConverter());
            result = template.postForObject(url, param, String.class);
        }finally {
            File file = new File(path + "/" + filename);
            if(file.exists()){
                file.delete();
            }
        }
        Map<String, Object> obj = (Map<String, Object>)JSON.parse(result);
        Map<String,Object> data = (Map<String, Object>)obj.get("bizData");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(Boolean.parseBoolean(data.get("result")+""))
        {
            resultMap= (Map<String, Object>)data.get("file");
        }
        return resultMap;
    }

    public static String delFileUrl(Object id){
        String result =null;
        String url = "http://cs-pro.qtonecloud.cn/rest/v1/delFile?fileId="+id;
        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new FastJsonHttpMessageConverter());
            result = template.getForObject(url, String.class);
        }catch (Exception ex){}
        return result;
    }

}
