package cn.thinkjoy.zgk.remote;

import cn.thinkjoy.zgk.dto.MajoredCategoryRemoteDTO;

import java.util.List;

/**
 * Created by zuohao on 16/1/23.
 */
public interface IMajoredService {
    List getMajorOpenUniversityList(int majoredId,int offset,int rows,String orderBy,String sortBy);

    public MajoredCategoryRemoteDTO getMajoredCategory(long scienceId);

    public MajoredCategoryRemoteDTO getCategoryMajoredList(long categoryId);
}
