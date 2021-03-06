package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.common.ServiceImplMaps;
import cn.thinkjoy.gaokao360.domain.Major;
import cn.thinkjoy.gaokao360.domain.MajorDetail;
import cn.thinkjoy.gaokao360.domain.MajorEmployment;
import cn.thinkjoy.gaokao360.domain.MajoredCategory;
import cn.thinkjoy.gaokao360.service.common.ex.IMajoredCategoryExService;
import cn.thinkjoy.gaokao360.service.common.ex.IMajoredExService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorExService;
import cn.thinkjoy.zgk.dto.CategoryMajoredDTO;
import cn.thinkjoy.zgk.dto.MajoredCategoryRemoteDTO;
import cn.thinkjoy.zgk.dto.MajoredQueryDTO;
import cn.thinkjoy.zgk.remote.IMajoredService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zuohao on 16/1/23.
 */
@Service("remoteMajoredServiceImpl")
public class MajoredServiceImpl implements IMajoredService {

    @Autowired
    private IUniversityMajorExService universityMajorExService;
    @Autowired
    private IMajoredCategoryExService majoredCategoryExService;
    @Autowired
    private ServiceImplMaps serviceImplMaps;
    @Autowired
    private IMajoredExService majoredExService;

    @Override
    public List getMajorOpenUniversityList(int majoredId,int offset,int rows,String orderBy,String sortBy){
        Map<String,Object> map= Maps.newHashMap();
        map.put("majorId", majoredId);
        return universityMajorExService.getMajorOpenUniversityList(map,offset,rows,orderBy,sortBy);
    }

    @Override
    public MajoredCategoryRemoteDTO getCategoryMajoredList(long categoryId){
        Map<String,Object> map= Maps.newHashMap();
        map.put("categoryId",categoryId);
        List<CategoryMajoredDTO> categoryMajoredDTOList = majoredCategoryExService.getCategoryMajoredList(map);

        MajoredCategoryRemoteDTO majoredCategoryRemoteDTO=new MajoredCategoryRemoteDTO();//大门类
        majoredCategoryRemoteDTO.setName(categoryMajoredDTOList.get(0).getDisciplineCategoriesName());
        majoredCategoryRemoteDTO.setId(categoryId);
        List<String> mcList= Lists.newArrayList();
        for (CategoryMajoredDTO categoryMajoredDTO:categoryMajoredDTOList){
            //判断专业门类是否已存在
            if (mcList.contains(categoryMajoredDTO.getMajorCategoryName())){//如果有
                for (MajoredCategoryRemoteDTO majoredCategoryRemoteDTO1:majoredCategoryRemoteDTO.getChildList()){
                    if (majoredCategoryRemoteDTO1.getName().equals(categoryMajoredDTO.getMajorCategoryName())){
                        majoredCategoryRemoteDTO1.setMajoredNumber(majoredCategoryRemoteDTO1.getMajoredNumber()+1);

                        MajoredCategoryRemoteDTO majoredCategoryRemoteDTO2=new MajoredCategoryRemoteDTO();
                        majoredCategoryRemoteDTO2.setId(categoryMajoredDTO.getMajoredId());
                        majoredCategoryRemoteDTO2.setName(categoryMajoredDTO.getMajoredName());
                        majoredCategoryRemoteDTO1.getChildList().add(majoredCategoryRemoteDTO2);
                        break;
                    }
                }
            }else {
                mcList.add(categoryMajoredDTO.getMajorCategoryName());//记录专业门类
                MajoredCategoryRemoteDTO majoredCategoryRemoteDTO1=new MajoredCategoryRemoteDTO();//专业门类
                majoredCategoryRemoteDTO1.setId(categoryMajoredDTO.getMajorCategoryId());//专业门类Id
                majoredCategoryRemoteDTO1.setName(categoryMajoredDTO.getMajorCategoryName());//专业门类Name
                majoredCategoryRemoteDTO1.setMajoredNumber(1);

                MajoredCategoryRemoteDTO majoredCategoryRemoteDTO2=new MajoredCategoryRemoteDTO();
                majoredCategoryRemoteDTO2.setId(categoryMajoredDTO.getMajoredId());
                majoredCategoryRemoteDTO2.setName(categoryMajoredDTO.getMajoredName());
                majoredCategoryRemoteDTO1.getChildList().add(majoredCategoryRemoteDTO2);
                majoredCategoryRemoteDTO.getChildList().add(majoredCategoryRemoteDTO1);
            }
        }
        return majoredCategoryRemoteDTO;
    }

    @Override
    public MajoredCategoryRemoteDTO getMajoredCategory(long scienceId){
        Map<String,Object> map= Maps.newHashMap();
        map.put("scienceId",scienceId);
        List<MajoredCategoryRemoteDTO> majoredCategoryRemoteDTOList = majoredCategoryExService.getMajoredCategory(map);
        MajoredCategoryRemoteDTO majoredCategoryRemoteDTO1=new MajoredCategoryRemoteDTO();
        majoredCategoryRemoteDTO1.setId(scienceId);
        if (scienceId==1){
            majoredCategoryRemoteDTO1.setName("本科");
        }else {
            majoredCategoryRemoteDTO1.setName("专科");
        }
        majoredCategoryRemoteDTO1.setChildNumber(0);
        for (MajoredCategoryRemoteDTO majoredCategoryRemoteDTO:majoredCategoryRemoteDTOList){
            majoredCategoryRemoteDTO1.getChildList().add(majoredCategoryRemoteDTO);
            majoredCategoryRemoteDTO1.setChildNumber(majoredCategoryRemoteDTO1.getChildNumber()+1);
            majoredCategoryRemoteDTO.setChildList(null);
        }
        return majoredCategoryRemoteDTO1;
    }

    /**
     * 根据id专业接口
     * @param id
     * @return
     */
    @Override
    public Map getMajoredInfoById(long id){
        Major major= (Major) serviceImplMaps.get("majorService").fetch(id);
        MajorDetail majorDetail= (MajorDetail) serviceImplMaps.get("majorDetailService").fetch(id);
        MajorEmployment majorEmployment= (MajorEmployment) serviceImplMaps.get("majorEmploymentService").fetch(id);
        Map<String,Object> map= Maps.newHashMap();
        if (major!=null) {
            map.put("id", major.getId());
            map.put("majorName", major.getMajorName());
        }
        if (majorDetail!=null) {
            map.put("majorCode", majorDetail.getMajorCode());
            map.put("degreeOffered", majorDetail.getDegreeOffered());
            map.put("schoolingDuration", majorDetail.getSchoolingDuration());
            map.put("offerCourses", majorDetail.getOfferCourses());
            map.put("majorIntroduce", majorDetail.getMajorIntroduce());
        }
        if (majorEmployment!=null) {
            map.put("employmentRate", majorEmployment.getEmploymentRate());
            map.put("salary", majorEmployment.getSalary());
        }
        return map;
    }

    @Override
    public List getMajoredByName(String majoredName,String type){
        List<MajoredQueryDTO> majoredQueryDTOList=majoredExService.getMajoredByName(majoredName, type);
        List<Map<String,Object>> mapList= Lists.newArrayList();
        List<String> mList=Lists.newArrayList();
        for (MajoredQueryDTO majoredQueryDTO:majoredQueryDTOList){
            if(mList.contains(majoredQueryDTO.getDisciplineCategoriesName()+majoredQueryDTO.getMajorCategoryName())){//如果存在
                for (Map<String,Object> map1:mapList){
                    if (map1.get("disciplineCategoriesName").equals(majoredQueryDTO.getDisciplineCategoriesName())
                            &&map1.get("majorCategoryName").equals(majoredQueryDTO.getMajorCategoryName())){
                        Map<String,Object> map2=Maps.newHashMap();
                        map2.put("majoredId",majoredQueryDTO.getMajoredId());
                        map2.put("majoredName",majoredQueryDTO.getMajoredName());
                        List ll= (List) map1.get("majoredList");
                        ll.add(map2);
                        break;
                    }
                }
            }else {
                mList.add(majoredQueryDTO.getDisciplineCategoriesName() + majoredQueryDTO.getMajorCategoryName());
                Map<String,Object> map1=Maps.newHashMap();
                map1.put("disciplineCategoriesName",majoredQueryDTO.getDisciplineCategoriesName());
                map1.put("majorCategoryName",majoredQueryDTO.getMajorCategoryName());
                Map<String,Object> map2=Maps.newHashMap();
                map2.put("majoredId",majoredQueryDTO.getMajoredId());
                map2.put("majoredName",majoredQueryDTO.getMajoredName());
                List ll=Lists.newArrayList();
                ll.add(map2);
                map1.put("majoredList",ll);
                mapList.add(map1);
            }
        }
        return mapList;
    }

}
