package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/4.
 */
public class GkSchedule extends BaseDomain{

    /** 月份 */
    private String month;
    /** 年份 */
    private String years;
    /** 内容 */
    private String content;
    /** 标题 */
    private String title;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
