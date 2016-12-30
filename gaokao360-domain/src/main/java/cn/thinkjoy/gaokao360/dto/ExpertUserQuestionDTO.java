package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.ExpertCases;
import cn.thinkjoy.gaokao360.domain.ExpertUserQuestion;

/**
 * Created by zuohao on 16/12/30.
 */
public class ExpertUserQuestionDTO extends ExpertUserQuestion {
    private String ExpertName;

    public String getExpertName() {
        return ExpertName;
    }

    public void setExpertName(String expertName) {
        ExpertName = expertName;
    }
}
