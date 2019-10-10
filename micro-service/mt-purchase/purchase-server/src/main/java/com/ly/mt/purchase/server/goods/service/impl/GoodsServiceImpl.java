package com.ly.mt.purchase.server.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.purchase.GoodsCategroyInfo;
import com.ly.mt.core.common.entity.purchase.GoodsSkuInfoVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.goods.mapper.GoodsMapper;
import com.ly.mt.purchase.server.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品相关
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 23:45:45
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * B端 类目查询
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject queryCategroyListByParentId(String json) throws Exception {
        long parentId = -1;
        List<GoodsCategroyInfo> goodsCategroyList = goodsMapper.selectCategroyListByParentId(parentId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsCategroyList",goodsCategroyList);
        return JsonUtil.getSuccessJson(jsonObject);
    }

    /**
     * B端 销量top5
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject queryListTop5ByCid(String json) throws Exception {
        LOGGER.info("B端销量top5查询，入参为：{}", json);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        String cid = map.get("cid");
        List<GoodsSkuInfoVO> skuInfoVOList = goodsMapper.selectListTop5ByCid(Long.parseLong(cid));
        return JsonUtil.getSuccessJson(skuInfoVOList);
    }
}
