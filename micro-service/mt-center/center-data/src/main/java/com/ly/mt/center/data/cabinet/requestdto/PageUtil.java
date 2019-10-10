package com.ly.mt.center.data.cabinet.requestdto;

import com.github.pagehelper.Page;
import com.ly.mt.center.data.cabinet.response.PageInfo;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;

import java.util.List;
import java.util.Map;

public class PageUtil {

    public static ResponseJson resultPageInfo(Page<Object> pages, List<Map<String,Object>> r){
        PageInfo result = new PageInfo(r);
        result.setTotal(pages.getTotal());
        result.setPages(pages.getPages());
        result.setTotal(pages.getTotal());
        result.setPageSize(r.size());
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
    }

    public static  PageInfo pageHelperHandle(Page pages,List r){
        PageInfo result = new PageInfo(r);
        result.setTotal(pages.getTotal());
        result.setPages(pages.getPages());
        result.setTotal(pages.getTotal());
        result.setPageNum(pages.getPageNum());
        result.setPageSize(r.size());
        return result;
    }

    public static PageInfo pageHelperHandler_(int pageNum,int pageSize,int total,int pages,List r){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);
        pageInfo.setPages(pages);
        pageInfo.setList(r);
        return pageInfo;
    }
}
