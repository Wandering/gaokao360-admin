package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/21.
 */
public class GkEntry extends BaseDomain {
    /** 标题 */
    private String title;
    /** 摘要 */
    private String summary;
    /** 富文本内容 */
    private String content;
    /** 排序 */
    private Integer sortId;
    /** 点击量 */
    private Integer hits;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
}
