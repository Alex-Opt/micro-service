package com.ly.mt.cabinet.b.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.request.*;
import com.ly.mt.cabinet.b.util.Resp;
import org.springframework.web.multipart.MultipartFile;

public interface CabinetBussinessCoorperationService {
    /**
     * 创建合作
     * @param data
     * @return
     */
    Resp createCoorperation(JSONObject data);

    /**
     * 格子柜保存
     * @param coorpReqVO
     * @return
     */
    Resp cabCoorperationSave(CoorpReqVo coorpReqVO);

    /**
     * 展柜创建
     * @param coorpReqVO
     * @return
     */
    Resp cabCoorperationCreate(CoorpReqVo coorpReqVO);

    /**
     * 展柜保存
     * @param coorpReqVO
     * @return
     */
    Resp caseCoorperationSave(CoorpReqVo coorpReqVO);

    /**
     * 展柜创建
     * @param coorpReqVO
     * @return
     */
    Resp caseCoorperationCreate(CoorpReqVo coorpReqVO);

    /**
     * 合作详情
     * @param data
     * @return
     */
    Resp coorperationInfo(JSONObject data);

    /**
     * 图片上传
     * @param file
     * @param path
     * @return
     */
    Resp upload(MultipartFile file,String path);

    /**
     * 待创建
     * @param coorperationListReqVO
     * @return
     */
    Resp coorperationTreatCreate(CoorperationListReqVO coorperationListReqVO);

    /**
     * 已创建
     * @param coorperationListReqVO
     * @return
     */
    Resp coorperationCreated(CoorperationListReqVO coorperationListReqVO);

    /**
     * 已使用
     * @param coorperationListReqVO
     * @return
     */
    Resp coorperationAlreadyUsed(CoorperationListReqVO coorperationListReqVO);

    /**
     * 全部
     * @param coorperationListReqVO
     * @return
     */
    Resp coorperationAll(CoorperationListReqVO coorperationListReqVO);

    /**
     * 搜索
     * @param coorperationSearchReqVO
     * @return
     */
    Resp search(CoorperationSearchReqVO coorperationSearchReqVO);

    /**
     * 店铺地址
     * @param basicAreaReqVO
     * @return
     */
    Resp basicArea(BasicAreaReqVO basicAreaReqVO);

    String findBdName(Long userId);

    /**
     * 展柜上线
     * @param coorpReqVo
     * @return
     */
    Resp caseCoorperationOnline(CoorpReqVo coorpReqVo);
}
