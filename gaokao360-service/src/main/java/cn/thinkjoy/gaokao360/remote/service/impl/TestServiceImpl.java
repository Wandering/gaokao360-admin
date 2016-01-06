package cn.thinkjoy.gaokao360.remote.service.impl;

import cn.thinkjoy.ITestService.ITestService;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/1/4.
 */
@Service("TestServiceImpl")
public class TestServiceImpl implements ITestService {

    @Override
    public int helloword() {
        return 1;
    }
}
