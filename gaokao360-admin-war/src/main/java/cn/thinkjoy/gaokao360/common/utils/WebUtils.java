package cn.thinkjoy.gaokao360.common.utils;

import cn.thinkjoy.common.exception.BizException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by liusven on 15/12/30.
 */
public class WebUtils {

    public static String saveContentAsHtml(HttpServletRequest req, String content){
        String result =null;
        String filename = "gk" + System.currentTimeMillis() + ".html";
        String path = req.getSession().getServletContext().getRealPath("/upload");
        String url = "http://cs-dev.thinkjoy.com.cn/rest/v1/uploadFile";
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
        if(Boolean.parseBoolean(data.get("result")+""))
        {
            Map<String, String> fileMap= (Map<String, String>)data.get("file");
            result = fileMap.get("fileUrl");
        }
        return result;
    }
}
