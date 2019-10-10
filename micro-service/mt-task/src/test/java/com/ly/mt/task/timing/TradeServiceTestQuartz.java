package com.ly.mt.task.timing;

import com.ly.mt.task.TaskServerApplicationTest;
import com.ly.mt.task.gy.timing.service.OrderTimingService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeServiceTestQuartz extends TaskServerApplicationTest {
    @Autowired
    OrderTimingService service;

    @Test
    public void getDeliveryInfo() {
        try {
            service.getDeliveryInfoByOrderId(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}