package com.ly.mt.core.data.record.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.record.dao.RecordImportDao;
import com.ly.mt.core.data.record.entity.RecordImport;
import com.ly.mt.core.data.record.mapper.RecordImportMapper;
import com.ly.mt.core.data.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * RecordImport操作接口
 *
 * @author taoye
 */
@Service
public class RecordImportDaoImpl extends BaseDaoServiceImpl implements RecordImportDao {
    @Resource
    private RecordImportMapper mapper;

    @Override
    public void insertRecordImport(RecordImport recordImport) {
        mapper.insertRecordImport(recordImport);
    }


    @Override
    public int updateRecordImport(RecordImport recordImport) {
        Assert.notNull(recordImport, "RecordImportDaoImpl.updateRecordImport recordImport must not be null");
        Assert.notNull(recordImport.getId(), "RecordImportDaoImpl.updateRecordImport recordImport.id must not be null");
        return mapper.updateRecordImport(recordImport);
    }


    @Override
    public Datagrid loadDatagridFromMysql(RecordImport recordImport, Page page) {
        Assert.notNull(page, "RecordImportDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<RecordImport> list = mapper.listRecordImport(recordImport);
        PageInfo<RecordImport> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}