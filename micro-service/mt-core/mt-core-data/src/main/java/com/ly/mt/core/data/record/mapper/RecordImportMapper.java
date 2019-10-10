package com.ly.mt.core.data.record.mapper;

import com.ly.mt.core.data.record.entity.RecordImport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RecordImport操作接口
 *
 * @author taoye
 */
@Mapper
public interface RecordImportMapper {
    /**
     * 新增RecordImport
     *
     * @param recordImport 新增数据
     * @author taoye
     */
    void insertRecordImport(RecordImport recordImport);

    int updateRecordImport(RecordImport recordImport);

    /**
     * 查询List<RecordImport>
     *
     * @param recordImport 查询条件
     * @return List<RecordImport>
     * @author taoye
     */
    List<RecordImport> listRecordImport(RecordImport recordImport);
}