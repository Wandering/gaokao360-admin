package cn.thinkjoy.zgk.cloudstack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clei on 15/8/8.
 */
public abstract class InterfaceConst {
    protected final static List<String> INTERFACEMETHODS= new ArrayList<String>();
    static {
        INTERFACEMETHODS.add("getGkEntryList");
        INTERFACEMETHODS.add("getGkEntryInfo");
        INTERFACEMETHODS.add("getGkHotList");
        INTERFACEMETHODS.add("getGkHotInfo");
        INTERFACEMETHODS.add("getGkPhoneList");
        INTERFACEMETHODS.add("getGkPolicyList");
        INTERFACEMETHODS.add("getGkPolicyInfo");
        INTERFACEMETHODS.add("getGkPolicyInfo");
        INTERFACEMETHODS.add("getGkVideoList");
        INTERFACEMETHODS.add("getGkVideoInfo");
        INTERFACEMETHODS.add("hitInc");
    }

    public static boolean hasWhiteList(String name) {
        return INTERFACEMETHODS.contains(name);
    }
}
