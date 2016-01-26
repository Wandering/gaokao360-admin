package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.domain.MajoredCategory;
import cn.thinkjoy.gaokao360.service.common.ex.IMajoredCategoryExService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorExService;
import cn.thinkjoy.zgk.dto.CategoryMajoredDTO;
import cn.thinkjoy.zgk.dto.MajoredCategoryRemoteDTO;
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

}
