package com.ly.mt.center.data.punch.mapper;

import com.ly.mt.center.data.punch.entity.PunchCardPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PunchCardPointMapper {
    /**
     * @Description 插入PunchCardPoint
     * @Author taoye
     */
    void insertPunchCardPoint(PunchCardPoint punchCardPoint);

    /**
     * @Description 根据id删除PunchCardPoint
     * @Author taoye
     */
    void deletePunchCardPointById(PunchCardPoint punchCardPoint);

    /**
     * @Description 根据id更新PunchCardPoint
     * @Author taoye
     */
    int updatePunchCardPointById(PunchCardPoint punchCardPoint);

    /**
     * @Description 根据条件查询PunchCardPoint
     * @Author taoye
     */
    PunchCardPoint getPunchCardPoint(PunchCardPoint punchCardPoint);

    /**
     * @Description 根据id查询PunchCardPoint
     * @Author taoye
     */
    PunchCardPoint getPunchCardPointById(PunchCardPoint punchCardPoint);

    /**
     * //查找打卡是否有积分
     * @param punchCardPoint
     * @return
     */
    List<PunchCardPoint> getPunchCardPointByStatus(PunchCardPoint punchCardPoint);
}