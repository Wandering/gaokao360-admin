package cn.thinkjoy.gaokao360.domain;

import cn.thinkjoy.common.domain.BaseDomain;

import java.util.List;

/**
 * Created by yangyongping on 2016/11/25.
 */
public class ExpertTime extends BaseDomain{
    private String picker;
    private String start;
    private String end;
    private List<ExpertTime> expertTimes;

    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<ExpertTime> getExpertTimes() {
        return expertTimes;
    }

    public void setExpertTimes(List<ExpertTime> expertTimes) {
        this.expertTimes = expertTimes;
    }
}
