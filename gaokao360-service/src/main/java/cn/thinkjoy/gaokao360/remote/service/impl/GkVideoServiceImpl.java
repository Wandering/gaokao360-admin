package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.gaokao360.domain.VideoClassify;
import cn.thinkjoy.gaokao360.domain.VideoCourse;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoClassifyService;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoCourseService;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoSectionService;
import cn.thinkjoy.zgk.common.QueryUtil;
import cn.thinkjoy.zgk.domain.GkVideo;
import cn.thinkjoy.zgk.domain.GkVideoInfo;
import cn.thinkjoy.zgk.remote.IGkVideoService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/1/4.
 */
@Component
@Scope("prototype")
@Service("GkVideoServiceImpl")
public class GkVideoServiceImpl extends BaseCommonService implements IGkVideoService {

    @Autowired
    IVideoClassifyService videoClassifyService;
    @Autowired
    IVideoCourseService videoCourseService;
    @Autowired
    IVideoSectionService videoSectionService;
    /**视频分类信息**/
    private static Map<Object,String> modalMap= null;

    /**视频统计**/
    private static Map<Object,String> teacherMap= Maps.newHashMap();

    /**视频对应教师**/
    private static Map<Object,Integer> countMap= Maps.newHashMap();
    /**
     * 获取热点摘要列表 四个
     * @return
     */
    @Override
    public BizData4Page getGkVideoList(Map<String, Object> conditions,Integer page,Integer rows) {
        List<VideoCourse> videoCourses = null;
        return doPage(conditions,videoCourseService.getDao(),page,rows);
    }
    /**
     * 获取详情
     * @return
     */
    @Override
    public GkVideoInfo getGkVideoInfo(String id){
        return videoCourse2GkVideoInfo((VideoCourse) videoCourseService.fetch(id));
    }
    @Override
    protected Object enhanceStateTransition(List conditions) {
        conditions=videoCourse2GkVideoInfo(conditions);
        return conditions;
    }



    private List<GkVideoInfo> videoCourse2GkVideoInfo(List<VideoCourse> policyInterpretations){
        if(policyInterpretations==null)return null;
        List<GkVideoInfo> gkVideoInfos = new ArrayList<>();
        for(VideoCourse policyInterpretation:policyInterpretations){
            gkVideoInfos.add(videoCourse2GkVideoInfo(policyInterpretation));
        }
        return gkVideoInfos;
    }

    private GkVideoInfo videoCourse2GkVideoInfo(VideoCourse videoCourse){
        if(videoCourse==null)return null;
        GkVideoInfo gkVideoInfo = new GkVideoInfo();
        gkVideoInfo.setId(videoCourse.getId());
        gkVideoInfo.setTitle(videoCourse.getTitle());
        gkVideoInfo.setFrontCover(videoCourse.getFrontCover());
        gkVideoInfo.setModalName(getModal(videoCourse.getClassifyId()));
        gkVideoInfo.setSubcontent(videoCourse.getSubcontent());
        gkVideoInfo.setSubInfo(videoCourse.getSubInfo());
        gkVideoInfo.setVideoCount(this.getCount(videoCourse.getId()));
        gkVideoInfo.setContent(videoCourse.getContent());
        return gkVideoInfo;
    }

    private List<GkVideoInfo> videoCourse2GkVideo(List<VideoCourse> policyInterpretations){
        if(policyInterpretations==null)return null;
        List<GkVideoInfo> gkVideoInfos = new ArrayList<>();
        for(VideoCourse policyInterpretation:policyInterpretations){
            gkVideoInfos.add(videoCourse2GkVideoInfo(policyInterpretation));
        }
        return gkVideoInfos;
    }

    private GkVideo videoCourse2GkVideo(VideoSection videoSection){
        if(videoSection==null)return null;
        GkVideo gkVideo = new GkVideo();
        gkVideo.setId(videoSection.getId());
        gkVideo.setFileUrl(videoSection.getFileUrl());
        gkVideo.setSectionSort(videoSection.getSectionSort());
        gkVideo.setTeacher(getTeacher(videoSection.getCourseId()));
        return gkVideo;
    }

    public String getModal(Object key) {
        if(modalMap==null){
            modalMap=Maps.newHashMap();
            List<VideoClassify> videoClassifies=videoClassifyService.findAll();
            if(videoClassifies!=null){
                for(VideoClassify videoClassify : videoClassifies){
                    modalMap.put(videoClassify.getId(),videoClassify.getClassifyName());
                }
            }
        }
        return modalMap.get(key);
    }

    public Integer getCount(Object key) {
        if(!countMap.containsKey(key)){
            Map<String,Object> map = new HashMap<>();
            QueryUtil.setMapOp(map,"courseId","=",key);
            Integer count = videoSectionService.count(map);
            countMap.put(key,count);
        }
        return countMap.get(key);
    }

    public String getTeacher(Object key) {
        if(!teacherMap.containsKey(key)){
            Map<String,Object> map = new HashMap<>();
            QueryUtil.setMapOp(map,"id","=",key);
            VideoCourse videoCourse = (VideoCourse)videoCourseService.fetch(map);
            teacherMap.put(key,videoCourse.getTeacher());
        }
        return teacherMap.get(key);
    }
}
