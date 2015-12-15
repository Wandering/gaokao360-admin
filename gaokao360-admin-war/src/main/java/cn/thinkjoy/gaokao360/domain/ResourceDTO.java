package cn.thinkjoy.gaokao360.domain;

import cn.thinkjoy.common.managerui.domain.Resource;

import java.util.List;

/**
 * Created by wdong on 15/7/31.
 */
public class ResourceDTO {

    private Resource resource;
    private List<ResourceDTO> list;

    public List<ResourceDTO> getList() {
        return list;
    }

    public void setList(List<ResourceDTO> list) {
        this.list = list;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
