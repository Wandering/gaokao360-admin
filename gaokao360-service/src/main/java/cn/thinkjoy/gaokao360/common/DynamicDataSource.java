package cn.thinkjoy.gaokao360.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override     
    protected Object determineCurrentLookupKey() {
        Object O = CustomerContextHolder.getContextType();
        return CustomerContextHolder.getContextType();
    }

}
