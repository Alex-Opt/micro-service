package com.ly.mt.test.taoye;

import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.core.data.gzg.dao.GzgInfoDao;
import com.ly.mt.test.TestApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapperReturnMapTest extends TestApplicationTest {
    @Autowired
    GzgInfoDao gzgInfoDao;

    @Test
    public void MapperReturnMapTest() {
        try {
            List<CabinetPlan> list = new ArrayList<>();
            CabinetPlan cabinetPlan = new CabinetPlan();
            cabinetPlan.setId("1");
            list.add(cabinetPlan);
            cabinetPlan = new CabinetPlan();
            cabinetPlan.setId("22222");
            list.add(cabinetPlan);
            Map<Long, Map<String, Long>> mapList = gzgInfoDao.getCountByCabinetPlansFromMysql(list);
            mapList.forEach(
                    (k, v) -> System.out.println("{" + k + "=" + v.toString() + "}")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}