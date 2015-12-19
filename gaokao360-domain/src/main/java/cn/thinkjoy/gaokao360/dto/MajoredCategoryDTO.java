package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.MajoredCategory;

import java.util.List;

/**
 * Created by admin on 2015/12/18.
 */
public class MajoredCategoryDTO extends MajoredCategory {
    private List<MajoredCategoryDTO> majoredCategoryDTOs;

    public List<MajoredCategoryDTO> getMajoredCategoryDTOs() {
        return majoredCategoryDTOs;
    }

    public void setMajoredCategoryDTOs(List<MajoredCategoryDTO> majoredCategoryDTOs) {
        this.majoredCategoryDTOs = majoredCategoryDTOs;
    }
}
