package com.ly.mt.center.third;

import com.ly.mt.center.third.fn.service.FnTokenService;
import com.ly.mt.center.third.fn.util.FnRandomUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FnTest extends CenterThirdApplicationTest {
    @Autowired
    FnTokenService service;

    @Test
    public void testGetToken() {
        service.getBusinessSign("", FnRandomUtil.getInstance().generateValue(1000, 10000));
    }
}