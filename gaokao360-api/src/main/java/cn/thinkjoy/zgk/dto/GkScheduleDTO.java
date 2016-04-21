package cn.thinkjoy.zgk.dto;

import cn.thinkjoy.zgk.domain.GkSchedule;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/1/4.
 */
public class GkScheduleDTO implements Serializable{
    /** 省份 */
    private Long areaId;
    /** 月份 */
    private String month;
    /** 年份 */
    private String years;

    private List<GkSchedule> schedules;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public List<GkSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<GkSchedule> schedules) {
        this.schedules = schedules;
    }
}
