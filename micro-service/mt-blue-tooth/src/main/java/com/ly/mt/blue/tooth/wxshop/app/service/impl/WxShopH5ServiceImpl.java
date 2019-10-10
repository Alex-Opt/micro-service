package com.ly.mt.blue.tooth.wxshop.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.base.util.LocationUtils;
import com.ly.mt.blue.tooth.wxshop.app.service.WxShopH5Service;
import com.ly.mt.blue.tooth.wxshop.app.vo.WxShopH5Vo;
import com.ly.mt.blue.tooth.wxshop.app.vo.WxSignatureVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.AlOssPath.AL_OSS_PATH_IMAGE_WXSHOP_ICON;
import static com.ly.mt.core.base.dict.AuthStatus.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.WX_SHOP_GET;
import static com.ly.mt.core.feign.DataCenterMethod.WX_SHOP_INSERT;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_GENERTATE_SIGNNATURE;

/**
 * @program: my-blue-tooth
 * @description:微信店铺小程序操作service
 * @author: wanghongliang
 * @create: 2019-08-02 11:02
 **/
@Service
public class WxShopH5ServiceImpl extends BaseServiceImpl implements WxShopH5Service {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxShopH5ServiceImpl.class);

    /**
     * 获取微信公众号签名
     * @throws Exception
     */
    @Override
    public ResponseJson getWxSignature(String requestUrl) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url",requestUrl);
        String result = callThirdCenter(WX_GENERTATE_SIGNNATURE, jsonObject); //获取微信公众号签名
        JSONObject resultObject = JSONObject.parseObject(result);
        WxSignatureVo wxSignatureVo = new WxSignatureVo();
        wxSignatureVo.setAppId(String.valueOf(resultObject.get("appId")));
        wxSignatureVo.setNonceStr(String.valueOf(resultObject.get("nonceStr")));
        wxSignatureVo.setSignature(String.valueOf(resultObject.get("signature")));
        wxSignatureVo.setTimestamp(String.valueOf(resultObject.get("timestamp")));
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, wxSignatureVo);
    }

    /**
     * 获取店铺列表
     * @param shopAddress
     * @param coordinate
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getWxShopList(String shopAddress,String coordinate) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(!StringUtil.isEmpty(shopAddress)){
            jsonObject.put("shop_address",shopAddress);//店铺地址
        }
        if(StringUtil.isEmpty(shopAddress)&&StringUtil.isEmpty(coordinate)){//店铺地址/坐标都为空时只查北京
            jsonObject.put("city_name","北京");//城市名字
        }
        String result = callDataCenter(WX_SHOP_GET, jsonObject); //获取店铺列表
        List<WxShopH5Vo> list = JSONObject.parseObject(result, new TypeReference<List<WxShopH5Vo>>(){});
        if(StringUtil.isEmpty(coordinate)){//坐标为空直接返回
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        }
        //五公里内店铺集合
        List<WxShopH5Vo> newList = new ArrayList<>();
        String[] coordinateArry=coordinate.split(",");
        double lat =Double.parseDouble(coordinateArry[0]);//纬度
        double lng=Double.parseDouble(coordinateArry[1]);//经度
        //开始计算在五公里的店铺
        for (WxShopH5Vo wxShopH5Vo : list) {
            String coordinateDB = wxShopH5Vo.getCoordinate();
            String[] coordinateDBArry=coordinateDB.split(",");
            double latDB =Double.parseDouble(coordinateDBArry[0]);//纬度
            double lngDB =Double.parseDouble(coordinateDBArry[1]);//经度
            double distance = LocationUtils.getDistance(lat, lng,
                    latDB, lngDB)/1000;
            LOGGER.info("店铺名称为距当前"+distance+"公里");
            if(distance<=5)newList.add(wxShopH5Vo);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, newList);
    }

    /**
     * 保存店铺
     * @param wxShopH5Vo
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson addWxShop(WxShopH5Vo wxShopH5Vo) throws Exception {
        /**
         * 判断店铺是否已经存在
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("coordinate", wxShopH5Vo.getCoordinate());
        jsonObject.put("auth_status",AUTHORIZED.getId());//已授权
        String result = callDataCenter(WX_SHOP_GET, jsonObject);
        List<WxShopH5Vo> list = JSONObject.parseObject(result, new TypeReference<List<WxShopH5Vo>>() {});
        if(list!=null&&list.size()>0){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "该店铺已授权");
        }
        JSONObject insertObject = new JSONObject();
        insertObject.put("name", wxShopH5Vo.getName());
        insertObject.put("mobile", wxShopH5Vo.getMobile());
        insertObject.put("shop_name", wxShopH5Vo.getShopName());
        insertObject.put("main_business", wxShopH5Vo.getMainBusiness());
        insertObject.put("shop_address", wxShopH5Vo.getShopAddress());
        insertObject.put("business_hours", wxShopH5Vo.getBusinessHours());
        insertObject.put("supply_channels", wxShopH5Vo.getSupplyChannels());
        insertObject.put("photo_url", wxShopH5Vo.getPhotoUrl());
        insertObject.put("wx_open_id", wxShopH5Vo.getWxOpenId());
        insertObject.put("auth_status", UNAUTHORIZED.getId());//暂时默认已授权 方便测试后期改过来
        insertObject.put("city_name", wxShopH5Vo.getCityName());
        insertObject.put("coordinate", wxShopH5Vo.getCoordinate());

        String insertResult = callDataCenter(WX_SHOP_INSERT, insertObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "添加成功!");
    }

    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson uploadWxShopLogo(MultipartFile file) throws Exception {
        //上传门店图片
        LOGGER.info("开始上传门店图片");
        String url = callThirdCenter(file, AL_OSS_PATH_IMAGE_WXSHOP_ICON.getPath());
        LOGGER.info("结束上传门店图片");
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, url);
    }
}