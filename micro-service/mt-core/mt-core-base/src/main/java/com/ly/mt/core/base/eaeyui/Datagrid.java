package com.ly.mt.core.base.eaeyui;

import java.util.List;

/**
 * easyui表格
 *
 * @author taoye
 */
public class Datagrid {
    private long total;
    private List<?> rows;
    private List<?> footer;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public List<?> getFooter() {
        return footer;
    }

    public void setFooter(List<?> footer) {
        this.footer = footer;
    }
}