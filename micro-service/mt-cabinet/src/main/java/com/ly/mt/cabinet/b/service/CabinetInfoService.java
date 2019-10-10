package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.*;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.ShowCabinetByBdMsgVo;
import com.ly.mt.cabinet.b.common.response.ShowcaseCoopDefaultRespVo;
import com.ly.mt.cabinet.b.common.response.SquareCabinetBdMsgVo;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜
 */
public interface CabinetInfoService {

    /**
     *  新增格子柜录入信息时，返回给前端展示目前货柜类型和对应的配货方案
     * @return
     */
    ResponseJson getCabinetInfoVoParam(WarehouseKeeperVo warehouseKeeperVo);

    /**
     *  仓库人员获取所有未使用商家信息
     * @return
     */
    ResponseJson getCabinetStoreNotUsed();

    /**
     *  仓库人员获取所有已上线商家信息
     * @return
     */
    ResponseJson getCabinetStoreIsOnline();


    /**
     * 查询带bd信息的格子柜
     * @param body
     * @param userToken
     * @return
     */
    String squareCabinets(CabinetMenageListRequestVo body,TokenUserMessage userToken);

    /**
     *  新增格子柜录入信息时，返回给前端展示目前货柜类型和对应的配货方案
     * @return
     */
    ResponseJson getCabinetProgrammeList();

    /**
     *  仓库人员添加货柜信息与店铺绑定
     * @return
     */
    ResponseJson addCabinetInfoBindStore(CabinetInfoBindStoreReqVo cabinetInfoBindStoreVo) throws Exception;


    /**
     * 查询合作信息详细信息
     * @param imei
     * @param tokenUserMessage
     * @return
     */
    ShowcaseCoopDefaultRespVo findSquareCabinetCoopMsg(ImeiRequestBody imei, TokenUserMessage tokenUserMessage);


    /**
     * 指定格子柜下架
     * @param imei
     * @param tokenUserMessage
     * @return
     */
    String downSquareCabinet(ImeiRequestBody imei,TokenUserMessage tokenUserMessage);

    /**
     * bd展柜集合
     * @param pageRequestVo
     * @param userMessage
     * @return
     */
    PageInfoResponseVo<ShowCabinetByBdMsgVo> showcaseCabinets(BasePageRequestVo pageRequestVo, TokenUserMessage userMessage);


    /**
     * 查询展柜合作信息
     * @param imei
     * @param tokenUserMessage
     * @return
     */
    ShowcaseCoopDefaultRespVo findShowcaseCabinetCoopMsg(ImeiRequestBody imei, TokenUserMessage tokenUserMessage);


    /**
     * 展柜下架
     * @param imei
     * @param tokenUserMessage
     * @return
     */
    String downShowCabinet(ImeiRequestBody imei,TokenUserMessage tokenUserMessage);


    /**
     * 人员旗下格子柜
     * @param body
     * @param userToken
     * @return
     */
    PageInfoResponseVo<SquareCabinetBdMsgVo> findCabinetsByUser(CabinetMenageListRequestVo body, TokenUserMessage userToken) ;

    /**
     * 展柜上架
     * @param imei
     * @param tokenUserMessage
     * @return
     */
    String upShowCabinet(ImeiRequestBody imei, TokenUserMessage tokenUserMessage);
}
