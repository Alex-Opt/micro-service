package com.ly.mt.center.data.notice.mapper;

import com.ly.mt.center.data.notice.entity.NoticeLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeLogsMapper {
    /**
     * @Description 保存NoticeLogs
     * @Author taoye
     */
    void insertNoticeLogs(NoticeLogs noticeLogs);

    /**
     * @Description 删除NoticeLogs
     * @Author taoye
     */
    void deleteNoticeLogs(NoticeLogs noticeLogs);

    /**
     * @Description 更新NoticeLogs
     * @Author taoye
     */
    int updateNoticeLogs(NoticeLogs noticeLogs);

    /**
     * @Description 查询NoticeLogs
     * @Author taoye
     */
    NoticeLogs getNoticeLogs(NoticeLogs noticeLogs);
}