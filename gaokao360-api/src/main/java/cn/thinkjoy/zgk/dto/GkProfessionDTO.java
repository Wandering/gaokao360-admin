package cn.thinkjoy.zgk.dto;

import cn.thinkjoy.zgk.domain.GkProfessionDetail;
import cn.thinkjoy.zgk.domain.GkProfessional;

import java.io.Serializable;

/**
 * Created by admin on 2016/1/20.
 */
public class GkProfessionDTO implements Serializable {
    private GkProfessional gkProfessional;
    private GkProfessionDetail gkProfessionDetail;

    public GkProfessional getGkProfessional() {
        return gkProfessional;
    }

    public void setGkProfessional(GkProfessional gkProfessional) {
        this.gkProfessional = gkProfessional;
    }

    public GkProfessionDetail getGkProfessionDetail() {
        return gkProfessionDetail;
    }

    public void setGkProfessionDetail(GkProfessionDetail gkProfessionDetail) {
        this.gkProfessionDetail = gkProfessionDetail;
    }
}
