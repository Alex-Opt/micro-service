package com.ly.mt.blue.tooth.wxshop.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.wxshop.web.service.WxShopWebService;
import com.ly.mt.blue.tooth.wxshop.web.vo.WxShopWebVo;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.dict.AuthStatus.AUTHORIZED;
import static com.ly.mt.core.base.dict.AuthStatus.REFUSEAUTHORIZED;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @program: my-blue-tooth
 * @description:微信后台Web操作service
 * @author: wanghongliang
 * @create: 2019-08-05 16:21
 **/
@Service
public class WxShopWebServiceImpl extends BaseServiceImpl  implements WxShopWebService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxShopWebServiceImpl.class);

    /**
     * 获取店铺列表
     * @return
     * @throws Exception
     */
    @Override
    public List<WxShopWebVo> getWxShopList(String authStatus, String cityName,String shopName, String shopAddress) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(!StringUtil.isEmpty(authStatus)){
            jsonObject.put("auth_status",authStatus);//授权状态
        }
        if(!StringUtil.isEmpty(cityName)){//城市名字
            jsonObject.put("city_name",cityName);
        }if(!StringUtil.isEmpty(shopName)){//店铺名称
            jsonObject.put("shop_name",shopName);
        }if(!StringUtil.isEmpty(shopAddress)){//店铺地址
            jsonObject.put("shopAddress",shopAddress);
        }
        String result = callDataCenter(WX_SHOP_WEB_GET, jsonObject); //获取店铺列表
        List<WxShopWebVo> list = JSONObject.parseObject(result, new TypeReference<List<WxShopWebVo>>(){});
        return list;
    }

    /**
     * 授权店铺
     * @param shopId
     * @return
     * @throws Exception
     */
    @Override
    public int authWxShop(String shopId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id",shopId);
        jsonObject.put("auth_status",AUTHORIZED.getId());//授权
        String result = callDataCenter(WX_SHOP_UPDATE, jsonObject); //更新授权状态
        int i  = Integer.parseInt(result);
        return i;
    }

    /**
     * 拒绝授权
     * @param shopId
     * @return
     * @throws Exception
     */
    @Override
    public int noAuthWxShop(String shopId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id",shopId);
        jsonObject.put("auth_status",REFUSEAUTHORIZED.getId());//拒绝授权
        String result = callDataCenter(WX_SHOP_UPDATE, jsonObject); //更新授权状态
        int i  = Integer.parseInt(result);
        return i;
    }

    /**
     * 更新店铺信息
     * @param wxShopWebVo
     * @return
     * @throws Exception
     */
    @Override
    public int updateWxShop(WxShopWebVo wxShopWebVo) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id",wxShopWebVo.getShopId());
        jsonObject.put("name",wxShopWebVo.getName());//姓名
        jsonObject.put("mobile",wxShopWebVo.getMobile());//手机号
        jsonObject.put("shop_name",wxShopWebVo.getShopName());//门店名称
        jsonObject.put("main_business",wxShopWebVo.getMainBusiness());//主营业务
        jsonObject.put("shop_address",wxShopWebVo.getShopAddress());//门店地址
        jsonObject.put("business_hours",wxShopWebVo.getBusinessHours());//营业时间
        jsonObject.put("supply_channels",wxShopWebVo.getSupplyChannels());//进货渠道
        jsonObject.put("city_name",wxShopWebVo.getCityName());//城市名字
        jsonObject.put("coordinate",wxShopWebVo.getCoordinate());//坐标
        jsonObject.put("udpate_time", DateUtil.getNowTimeStr());//更新时间
        String result = callDataCenter(WX_SHOP_UPDATE, jsonObject); //更新微信店铺信息
        int i  = Integer.parseInt(result);
        return i;
    }

    /**
     * 删除店铺信息
     * @param shopId
     * @return
     * @throws Exception
     */
    @Override
    public int deleteWxShop(String shopId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id",shopId);
        String result = callDataCenter(WX_SHOP_DELETE, jsonObject); //删除店铺信息
        int i  = Integer.parseInt(result);
        return i;
    }

    /**
     * 获取单个店铺信息
     * @param shopId
     * @return
     * @throws Exception
     */
    @Override
    public WxShopWebVo getWxShop(String shopId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shop_id",shopId);
        String result = callDataCenter(WX_SHOP_WEB_GET_ONE, jsonObject); //删除店铺信息
        WxShopWebVo wxShopWebVo  = JSONObject.parseObject(result,WxShopWebVo.class);
        return wxShopWebVo;
    }
}