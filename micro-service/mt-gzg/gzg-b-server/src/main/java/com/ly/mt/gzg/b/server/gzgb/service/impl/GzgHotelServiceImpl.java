package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.gzg.GzgHotel;
import com.ly.mt.core.common.entity.gzg.GzgHotelUserRelation;
import com.ly.mt.core.common.entity.gzg.GzgInfo;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.gzg.b.server.base.constant.GzgConstant;
import com.ly.mt.gzg.b.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgHotelMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgHotelUserRelationMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgInfoMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.UserMapper;
import com.ly.mt.gzg.b.server.gzgb.service.GzgHotelService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.ly.mt.core.common.constant.ResultCodeEnum.GZG_PHONE_NOT_REGISTER;

@Service
@Slf4j
public class GzgHotelServiceImpl extends BaseServiceImpl implements GzgHotelService {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(GzgHotelServiceImpl.class);
    @Resource
    private GzgHotelMapper gzgHotelMapper;

    @Resource
    private GzgHotelUserRelationMapper gzgHotelUserRelationMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private GzgInfoMapper gzgInfoMapper;

    @Override
    public JSONObject saveHotel(String jsonParam) {
        log.info("saveHotel jsonParam:{}",jsonParam);
        JSONObject jsonObject = JSON.parseObject(jsonParam);
        GzgHotel hotel = jsonObject.getObject("hotel",GzgHotel.class);
        log.info("saveHotel 接收的参数 hotel={}",hotel);
        GzgInfo gzgInfo = jsonObject.getObject("gzg",GzgInfo.class);
        log.info("saveHotel格子柜信息是={}",gzgInfo);

        //根据手机号查看用户是否注册
        String phone = hotel.getHotelAdminId().toString();
        User user = userMapper.findByMobile(phone);

        if (user == null){
            Logger.info("该手机号未注册,或被停用，phone={}",phone);
            return JsonUtil.getErrorJson(GZG_PHONE_NOT_REGISTER);
        }

        hotel.setHotelAdminId(user.getId());
        //.getId(IdEnum.GZG_HOTEL_INFO)
        hotel.setId(StringUtil.getRandomIntByLength(10));
        hotel.setCreateTime(new Date());
        gzgHotelMapper.insertSelective(hotel);
        log.info("添加酒店成功，添加後的hotel={}",hotel);

        //删除之前用户酒店关系，重新添加
        //GzgHotelUserRelation byHotelIdAndUserId = gzgHotelUserRelationMapper.findByHotelIdAndUserId(Long.parseLong(hotel.getId()), user.getId());
        GzgHotelUserRelation gzgHotelUserRelation = gzgHotelUserRelationMapper.selectByUserId(Long.parseLong(user.getId()));
        if (gzgHotelUserRelation == null){
            GzgHotelUserRelation relation = new GzgHotelUserRelation();
            relation.setHotelId(hotel.getId());
            relation.setUserId(user.getId());
            relation.setTyep(GzgConstant.USER_TYPE_1);
            relation.setCreateTime(new Date());
            relation.setId(StringUtil.getRandomIntByLength(10));
            gzgHotelUserRelationMapper.insertSelective(relation);
        }else {
            gzgHotelUserRelation.setHotelId(hotel.getId());
            gzgHotelUserRelation.setUserId(user.getId());
            gzgHotelUserRelationMapper.updateByPrimaryKeySelective(gzgHotelUserRelation);
        }
        GzgInfo gInfo = gzgInfoMapper.selectByGzgCode(Long.valueOf(gzgInfo.getCode()));
        log.info("保存酒店获取格子柜信息={}",JSON.toJSONString(gInfo));
        if (gInfo != null){
            gInfo.setPlanId(gzgInfo.getPlanId());
            gInfo.setHotelId(hotel.getId());
            gInfo.setPosition(gzgInfo.getPosition());
            log.info("更新格子柜和用户关系gInfo={}",gInfo);
            gzgInfoMapper.updateByPrimaryKeySelective(gInfo);
        }else {
            //添加格子柜
            gzgInfo.setHotelId(hotel.getId());
            log.info("添加格子柜和用户关系gzgInfo={}",gzgInfo);
            gzgInfoMapper.insertSelective(gzgInfo);
        }
        return JsonUtil.getSuccessJson();
    }
}
