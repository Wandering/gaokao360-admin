package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.common.domain.view.BizData4Page;
import cn.thinkjoy.common.exception.BizException;
import cn.thinkjoy.gaokao360.common.ERRORCODE;
import cn.thinkjoy.gaokao360.dao.IVideoCourseDAO;
import cn.thinkjoy.gaokao360.domain.VideoClassify;
import cn.thinkjoy.gaokao360.domain.VideoCourse;
import cn.thinkjoy.gaokao360.domain.VideoSection;
import cn.thinkjoy.gaokao360.dto.VideoCourseDTO;
import cn.thinkjoy.gaokao360.dto.VideoSectionDTO;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoClassifyService;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoCourseService;
import cn.thinkjoy.gaokao360.service.differentiation.IVideoSectionService;
import cn.thinkjoy.gaokao360.service.differentiation.ex.IVideoSectionExService;
import cn.thinkjoy.zgk.common.QueryUtil;
import cn.thinkjoy.zgk.domain.GkVideo;
import cn.thinkjoy.zgk.domain.GkVideoInfo;
import cn.thinkjoy.zgk.dto.GkVideoDTO;
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
    @Autowired
    IVideoSectionExService videoSectionExService;
    /**视频分类信息**/
    private Map<Object,String> modalMap= null;


    /**
     * 获取视频列表
     * @return
     */
    @Override
    public BizData4Page getGkVideoList(Map<String, Object> conditions,Integer page,Integer rows) {
        if(conditions.containsKey("isIgnore") && conditions.get("isIgnore").equals(true)){
            setIsIgnore(true);
        }
        return doPage(conditions,videoCourseService.getDao(),page,rows);
    }
    public void hitInc(Object id){
        VideoCourse videoCourse=(VideoCourse)videoCourseService.fetch(id);
        if(videoCourse==null){
            throw new BizException(ERRORCODE.RESOURCEISNULL.getCode(),ERRORCODE.RESOURCEISNULL.getMessage());
        }else {
            if(videoCourse.getHit() == null){
                videoCourse.setHit(1L);
            }else {
                videoCourse.setHit(videoCourse.getHit() + 1);
            }
            videoCourseService.update(videoCourse);
        }
    }
    /**
     * 获取详情
     * @return
     */
    @Override
    public GkVideoDTO getGkVideoInfo(String id){
        return video2videoDTO((VideoCourse)videoCourseService.fetch(id));
    }

    @Override
    protected Object enhanceStateTransition(List conditions) {
        conditions=video2videoDTO(conditions);
        return conditions;
    }


    private List<GkVideoDTO> video2videoDTO(List<VideoCourse> videoCourses){
        if(videoCourses==null)return null;
        List<GkVideoDTO> gkVideoDTOs = new ArrayList<>();
        for(VideoCourse v:videoCourses){
            gkVideoDTOs.add(video2videoDTO(v));
        }
        return gkVideoDTOs;
    }


    private GkVideoDTO video2videoDTO(VideoCourse v){
        if(v==null)return null;
            GkVideoDTO gkVideoDTO=new GkVideoDTO();
            gkVideoDTO.setId(v.getId());
            gkVideoDTO.setGkVideoInfo(videoCourse2GkVideoInfo(v));
        if(isIgnore()) {
            gkVideoDTO.setGkVideos(getVideoListById(v.getId()));
        }
        return gkVideoDTO;
    }

    private List<GkVideoInfo> videoCourse2GkVideoInfo(List<VideoCourse> videoCourses){
        if(videoCourses==null)return null;
        List<GkVideoInfo> gkVideoInfos = new ArrayList<>();
        for(VideoCourse policyInterpretation:videoCourses){
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
        gkVideoInfo.setTeacher(videoCourse.getTeacher());
        if(isIgnore()) {
            gkVideoInfo.setSubcontent(videoCourse.getSubcontent());
            gkVideoInfo.setHit(videoCourse.getHit());
            gkVideoInfo.setContent(videoCourse.getContent());
        }
        return gkVideoInfo;
    }

    private List<GkVideo> videoSection2GkVideo(List<VideoSectionDTO> videoSectionDTOs){
        if(videoSectionDTOs==null)return null;
        List<GkVideo> gkVideoInfos = new ArrayList<>();
        for(VideoSectionDTO v:videoSectionDTOs){

            gkVideoInfos.add(videoSection2GkVideo(v));
        }
        return gkVideoInfos;
    }



    private GkVideo videoSection2GkVideo(VideoSectionDTO videoSection){
        if(videoSection==null)return null;
        GkVideo gkVideo = new GkVideo();
        gkVideo.setId(videoSection.getId());
        gkVideo.setFileUrl(videoSection.getFileUrl());
        gkVideo.setName(videoSection.getSectionName());
        gkVideo.setSectionSort(Integer.parseInt(videoSection.getSectionSort()));
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

    private List<GkVideo> getVideoListById(Object id){
        List<VideoSectionDTO> list=videoSectionExService.getVideoSectionByCourseId(id);
        return videoSection2GkVideo(list);
    }


}
