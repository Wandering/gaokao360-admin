package cn.thinkjoy.gaokao360.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override     
    protected Object determineCurrentLookupKey() {
        System.out.println(Thread.currentThread().getId());
        Object O = CustomerContextHolder.getContextType();
        System.out.println("当前类："+this.getClass().getName()+"当前线程="+Thread.currentThread().getId()+"当前数据库："+CustomerContextHolder.getContextType());
        return CustomerContextHolder.getContextType();
    }

}
