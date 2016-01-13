package cn.thinkjoy.zgk.dto;

import java.io.Serializable;

/**
 * Created by zuohao on 16/1/13.
 */
public class UniversityPlanChartDTO implements Serializable {
    private String year;
    private String typeName;
    private int number;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
