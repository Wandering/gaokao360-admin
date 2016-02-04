package cn.thinkjoy.zgk.cloudstack;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2016/2/4.
 */
@Component
public class BaseWhiteList{

    protected final static Set<String> INTERFACEMETHODS= new HashSet<>();

    public boolean hasWhiteList(String name) {
        return INTERFACEMETHODS.contains(name);
    }
    @PostConstruct
    public void init(){
        INTERFACEMETHODS.add("getGkEntryList");
        INTERFACEMETHODS.add("getGkEntryInfo");
        INTERFACEMETHODS.add("getGkHotList");
        INTERFACEMETHODS.add("getGkHotInfo");
//        INTERFACEMETHODS.add("getGkPhoneList");
        INTERFACEMETHODS.add("getGkPolicyList");
        INTERFACEMETHODS.add("getGkPolicyInfo");
        INTERFACEMETHODS.add("getGkPolicyInfo");
//        INTERFACEMETHODS.add("getGkVideoList");
//        INTERFACEMETHODS.add("getGkVideoInfo");
//        INTERFACEMETHODS.add("hitInc");
    }

}
