package com.ly.mt.core.base.eaeyui;

/**
 * easyui分页
 *
 * @author taoye
 */
public class Page {
    private int page = 1;
    private int rows = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}