package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.ExpertAppraise;

/**
 * Created by yangyongping on 2016/10/20.
 */
public class ExpertAppraiseDTO extends ExpertAppraise {
    private String serverName;
    private String expertName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }
}
