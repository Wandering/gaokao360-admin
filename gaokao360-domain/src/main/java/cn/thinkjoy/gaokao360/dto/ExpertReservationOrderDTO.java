package cn.thinkjoy.gaokao360.dto;

import cn.thinkjoy.gaokao360.domain.ExpertReservationOrder;

/**
 * Created by yangyongping on 2016/10/24.
 */
public class ExpertReservationOrderDTO extends ExpertReservationOrder {
    private String expertName;
    private String expertPhone;

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertPhone() {
        return expertPhone;
    }

    public void setExpertPhone(String expertPhone) {
        this.expertPhone = expertPhone;
    }
}
