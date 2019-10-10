package com.ly.mt.purchase.server.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.purchase.ShopGradeVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.shop.mapper.ShopGradeMapper;
import com.ly.mt.purchase.server.shop.service.ShopGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiaobei
 * @version 1.0
 * @date 2019-06-21 08:13:13
 */
@Service
public class ShopGradeServiceImpl extends BaseServiceImpl implements ShopGradeService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ShopGradeServiceImpl.class);

    @Resource
    private ShopGradeMapper shopGradeMapper;

    /**
     * 查询所有的店铺会员等级配置情况
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject listAll(String json) throws Exception {
        List<ShopGradeVO> gradeList = shopGradeMapper.listAll();
        return JsonUtil.getSuccessJson(gradeList);
    }
}
