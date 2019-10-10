package com.ly.mt.mis.basic.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.data.goods.entity.GoodsCategroyInfo;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.goods.service.GoodsCategroyService;
import com.ly.mt.mis.basic.goods.vo.GoodsCategroyCombotreeVo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 商品类目管理
 *
 * @author taoye
 */
@Service
public class GoodsCategroyServiceImpl extends BaseServiceImpl implements GoodsCategroyService {
    @Override
    public ResponseJson loadGoodsCategroyCombotree(JSONObject jsonObject) throws Exception {
        GoodsCategroyCombotreeVo vo = new GoodsCategroyCombotreeVo();
        vo.setId("-1");
        vo = getComboTreeVo(vo);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo.getChildren());
    }


    /**
     * 递归查询下拉树
     *
     * @author taoye
     */
    private GoodsCategroyCombotreeVo getComboTreeVo(GoodsCategroyCombotreeVo vo) {
        List<GoodsCategroyInfo> goodsCategroyInfos = goodsCategroyInfoDao.listGoodsCategroyInfoByParentIdFromRedis(vo.getId());
        if (goodsCategroyInfos.size() <= 0) {
            return vo;
        }
        List<GoodsCategroyCombotreeVo> goodsCategroyCombotreeVos = JSONObject.parseObject(JSONObject.toJSONString(goodsCategroyInfos), new TypeReference<List<GoodsCategroyCombotreeVo>>() {
        });
        for (GoodsCategroyCombotreeVo goodsCategroyCombotreeVo : goodsCategroyCombotreeVos) {
            getComboTreeVo(goodsCategroyCombotreeVo);
        }
        vo.setChildren(goodsCategroyCombotreeVos);
        return vo;
    }
}