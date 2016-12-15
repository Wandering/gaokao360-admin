package cn.thinkjoy.gaokao360.domain;

import cn.thinkjoy.common.domain.BaseDomain;

import java.util.List;

/**
 * Created by yangyongping on 2016/11/25.
 */
public class ExpertDate extends BaseDomain{
    private String expertId;
    private List<ExpertTime> expertTimes;

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public List<ExpertTime> getExpertTimes() {
        return expertTimes;
    }

    public void setExpertTimes(List<ExpertTime> expertTimes) {
        this.expertTimes = expertTimes;
    }
}
