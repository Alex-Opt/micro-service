package com.ly.mt.center.data.cabinet.response;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 总数量
     */
    private long total;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 数据列表
     */
    private List<T> list;
    /**
     * 是否是第一页
     */
    //private boolean isFirstPage = false;
    /**
     * 是否是最后一页
     */
    //private boolean isLastPage = false;

    public PageInfo(){}

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = Long.valueOf(page.getPages());
            this.list = page;
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = 1l;
            this.list = list;
            this.total = list.size();
        }
        /*if (list instanceof Collection) {
            judgePageBoudary();
        }*/
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * 判定页面边界
     */
    /*private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
    }*/
}
