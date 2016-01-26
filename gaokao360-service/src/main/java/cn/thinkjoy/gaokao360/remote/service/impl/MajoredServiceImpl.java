package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.gaokao360.service.common.ex.IMajoredCategoryExService;
import cn.thinkjoy.gaokao360.service.common.ex.IUniversityMajorExService;
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
        List<String> mcName1= Lists.newArrayList();
        for (MajoredCategoryRemoteDTO majoredCategoryRemoteDTO:majoredCategoryRemoteDTOList){
            //判断本科中是否已有大门类
            if (mcName1.contains(majoredCategoryRemoteDTO.getParentName())){//如果有
                for (MajoredCategoryRemoteDTO majoredCategoryRemoteDTO2:majoredCategoryRemoteDTO1.getChildList()){
                    if(majoredCategoryRemoteDTO2.getName().equals(majoredCategoryRemoteDTO.getParentName())){
                        majoredCategoryRemoteDTO2.getChildList().add(majoredCategoryRemoteDTO);
                        if (majoredCategoryRemoteDTO2.getChildNumber()!=null){
                            majoredCategoryRemoteDTO2.setChildNumber(majoredCategoryRemoteDTO2.getChildNumber()+1);//包含专业门类个数
                        }else {
                            majoredCategoryRemoteDTO2.setChildNumber(1);//包含专业门类个数
                        }
                    }
                }
            }else {
                mcName1.add(majoredCategoryRemoteDTO.getParentName());//记录中增加一个大门类
                MajoredCategoryRemoteDTO majoredCategoryRemoteDTO2=new MajoredCategoryRemoteDTO();//大门类
                majoredCategoryRemoteDTO2.setId(majoredCategoryRemoteDTO.getParentId());//大门类Id
                majoredCategoryRemoteDTO2.setName(majoredCategoryRemoteDTO.getParentName());//大门类名称

                majoredCategoryRemoteDTO2.getChildList().add(majoredCategoryRemoteDTO);//增加包含专业门类
                if (majoredCategoryRemoteDTO2.getChildNumber()!=null){
                    majoredCategoryRemoteDTO2.setChildNumber(majoredCategoryRemoteDTO2.getChildNumber()+1);//包含专业门类个数
                }else {
                    majoredCategoryRemoteDTO2.setChildNumber(1);//包含专业门类个数
                }

                majoredCategoryRemoteDTO1.getChildList().add(majoredCategoryRemoteDTO2);//将大门类增加在本科类中
            }


        }
        return majoredCategoryRemoteDTO1;
    }

}
