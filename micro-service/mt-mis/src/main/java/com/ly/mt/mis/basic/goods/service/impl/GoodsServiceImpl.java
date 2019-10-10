package com.ly.mt.mis.basic.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.goods.entity.GoodsCategroyInfo;
import com.ly.mt.core.data.goods.entity.GoodsInfoView;
import com.ly.mt.core.data.goods.entity.GoodsSkuPicture;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.goods.service.GoodsService;
import com.ly.mt.mis.basic.goods.vo.GoodsDatagridVo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 商品管理
 *
 * @author taoye
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {
    @Override
    public ResponseJson loadSkuPicture(JSONObject jsonObject) throws Exception {
        String skuId = jsonObject.getString("skuId");
        GoodsSkuPicture goodsSkuPicture = goodsSkuPictureDao.getGoodsSkuPictureBySkuIdFromRedis(skuId);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsSkuPicture.getPictureUrl());
    }


    @Override
    public ResponseJson loadGoodsDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        GoodsInfoView goodsInfoView = JSONObject.toJavaObject(jsonObject, GoodsInfoView.class);
        Datagrid datagrid = goodsInfoViewDao.loadDatagridFromMysql(goodsInfoView, page);
        List<GoodsDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<GoodsDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setSpuStatusName(EnumUtil.getNameById("SpuStatus", vo.getSpuStatus()));
                    String cid = vo.getSpuCid();
                    if (StringUtil.isNotEmpty(cid)) {
                        GoodsCategroyInfo goodsCategroyInfo = goodsCategroyInfoDao.getGoodsCategroyInfoDaoByIdFromRedis(cid);
                        vo.setSpuCname(goodsCategroyInfo.getName());
                    }
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}