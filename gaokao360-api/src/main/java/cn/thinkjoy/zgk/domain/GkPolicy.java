package cn.thinkjoy.zgk.domain;

/**
 * Created by admin on 2016/1/12.
 */
public class GkPolicy extends BaseDomain {
    /** 富文本内容 */
    private String content;
    /** 标题 */
    private String title;
    /** 内容摘要 */
    private String subContent;
    /** 政策解读时间 */
    private Long lastModDate;

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

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

}
