package com.ly.mt.mis.mis.main.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.mis.mis.main.vo.TopMenuVo;

import java.util.List;

/**
 * 主页
 *
 * @author taoye
 */
public interface MainService {
    /**
     * 加载顶部菜单
     *
     * @author taoye
     */
    List<TopMenuVo> loadTopMenu() throws Exception;

    /**
     * 根据顶部菜单id加载左侧菜单
     *
     * @author taoye
     */
    ResponseJson loadLeftMenu(JSONObject jsonObject) throws Exception;
}