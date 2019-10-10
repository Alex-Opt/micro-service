package com.ly.mt.cabinet.b.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页展示对象
 * 此类为了解决分页查询后进行深度数据包装原生-
 * pahehelper不能进行数据重新set进去的问题。
 *
 * 具体使用方法使用Spring 的
 *                 BeanUtils.copyProperties(pageInfo, mtPageinfoModel)；
 *                 然后在把包装好的数据list集合set进此对象就哦了
 *
 */
@ApiModel("分页返回对象")
public class PageInfoResponseVo<T> {

    //当前页
    @ApiModelProperty("当前页码")
    private int pageNum;
    //每页的数量
    @ApiModelProperty("每页条数")
    private int pageSize;
    //当前页的数量
    @ApiModelProperty("当前页条数")
    private int size;
    //总记录数
    @ApiModelProperty("总记录数")
    private long total;
    //总页数
    @ApiModelProperty("总页数")
    private int pages;
    //结果集
    @ApiModelProperty("结果集")
    private List<T> list;
    //是否为第一页
    @ApiModelProperty("是否是第一页")
    private boolean isFirstPage;
    //是否为最后一页
    @ApiModelProperty("是否是最后一页")
    private boolean isLastPage;
    //是否有前一页
    @ApiModelProperty("是否是最后一页")
    private boolean hasPreviousPage;
    //是否有后一页
    @ApiModelProperty("是否有最后一页")
    private boolean hasNextPage;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
