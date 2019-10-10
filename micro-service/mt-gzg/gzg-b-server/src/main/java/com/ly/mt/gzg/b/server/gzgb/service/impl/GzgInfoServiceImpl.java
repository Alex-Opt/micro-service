package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.gzg.GzgBUserRelation;
import com.ly.mt.core.common.entity.gzg.GzgHotelUserRelation;
import com.ly.mt.core.common.entity.gzg.GzgInfo;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.server.base.constant.GzgConstant;
import com.ly.mt.gzg.b.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.gzg.b.server.gzgb.mapper.*;
import com.ly.mt.gzg.b.server.gzgb.service.GzgInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.common.constant.ResultCodeEnum.GZG_PHONE_NOT_REGISTER;

@Service
@Slf4j
public class GzgInfoServiceImpl extends BaseServiceImpl implements GzgInfoService {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(GzgInfoServiceImpl.class);
    @Resource
    private GzgInfoMapper gzgInfoMapper;
    @Resource
    private GzgBUserRelationMapper gzgBUserRelationMapper;
    @Resource
    private GzgHotelUserRelationMapper gzgHotelUserRelationMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private GzgPlanMapper gzgPlanMapper;

    @Override
    public JSONObject saveGzg(String jsonParam) {
        JSONObject jsonObject = JSON.parseObject(jsonParam);
        JSONArray data = jsonObject.getJSONArray("data");
        List<GzgInfo> gzgInfos = data.toJavaList(GzgInfo.class);
        gzgInfos.forEach(x->x.setId(IdUtil.getId(IdEnum.GZG_INFO)));
        int num = gzgInfoMapper.batchInsert(gzgInfos);
        return JsonUtil.getSuccessJson(num);
    }

    @Override
    public JSONObject getInfos(String jsonParam) {
        log.info("getInfos jsonParam={}",jsonParam);
        String hotelId = JSONObject.parseObject(jsonParam).getString("hotelId");
        log.info("getInfos hotelId={}",hotelId);
        List<GzgInfo> byHotelId = gzgInfoMapper.findByHotelId(Long.parseLong(hotelId));
        log.info("findGzgUsers function return data={}",JSON.toJSONString(byHotelId));
        return JsonUtil.getSuccessJson(byHotelId);
    }

    @Override
    public JSONObject saveGzgUser(String jsonParam) {
        log.info("saveGzgUser jsonParam:{}",jsonParam);
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        String gzgId = jsonObject.getString("gzgId");
        String userIdStr = jsonObject.getString("userId");
        JSONArray datas = jsonObject.getJSONArray("data");
        List<JSONObject> phs = datas.toJavaList(JSONObject.class);

        //清除该柜子关系
        gzgBUserRelationMapper.delByGzglId(Long.parseLong(gzgId));

        GzgInfo gzgInfo = gzgInfoMapper.selectByPrimaryKey(Long.parseLong(gzgId));
        for (JSONObject object:phs){
            User user = userMapper.findByMobile(object.getString("mobile"));
            String nickName = object.getString("nickName");
            log.info("saveGzgUser nickName={}",nickName);
            if (user == null){
                return JsonUtil.getErrorJson(GZG_PHONE_NOT_REGISTER);
            }
            long userId = Long.valueOf(user.getId());
            List<GzgBUserRelation> gzgBUserRelations = gzgBUserRelationMapper.selectByUserId(userId);
            if (gzgBUserRelations != null && gzgBUserRelations.size() > 0){
                return JsonUtil.getErrorJson(ResultCodeEnum.GZG_HAS_EXISTS);
            }
            GzgBUserRelation relation = new GzgBUserRelation();
            relation.setGzgId(gzgId);
            relation.setbUserId(user.getId());
            gzgBUserRelationMapper.insertSelective(relation);
            //更新用户昵称
            user.setNickName(nickName);
            log.info("saveGzgUser user info={}",user);
            int i = userMapper.updateByPrimaryKey(user);
            log.info("update user counts={}",i);
            //添加酒店用户关系
            GzgHotelUserRelation hotelUserRelation = gzgHotelUserRelationMapper.findByHotelIdAndUserId(Long.parseLong(gzgInfo.getHotelId()),user.getId());
            GzgHotelUserRelation gzgHotelUserRelation = gzgHotelUserRelationMapper.selectByUserId(Long.parseLong(user.getId()));
            if (hotelUserRelation == null && gzgHotelUserRelation == null){
                hotelUserRelation = new GzgHotelUserRelation();
                hotelUserRelation.setUserId(user.getId());
                hotelUserRelation.setHotelId(gzgInfo.getHotelId());
                hotelUserRelation.setTyep(GzgConstant.USER_TYPE_2);
                gzgHotelUserRelationMapper.insertSelective(hotelUserRelation);
            }/*else {
                hotelUserRelation.setUserId(user.getId());
                hotelUserRelation.setHotelId(gzgInfo.getHotelId());
                hotelUserRelation.setTyep(GzgConstant.USER_TYPE_2);
                gzgHotelUserRelationMapper.updateByPrimaryKeySelective(hotelUserRelation);
            }*/
        }
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject findGzgUsers(String jsonParam) {
        log.info("findGzgUsers function jsonParam:{}",jsonParam);
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        String gzgId = jsonObject.getString("gzgId");
        List<User> users =  gzgBUserRelationMapper.findGzgUsers(Long.parseLong(gzgId));
        log.info("findGzgUsers function return data={}",JSON.toJSONString(users));
        return JsonUtil.getSuccessJson(users);
    }

    @Override
    public JSONObject findUserByPhone(String jsonParam) {
        log.info("findUserByPhone jsonParam:{}",jsonParam);
        JSONObject jsonObject = JSON.parseObject(jsonParam);
        long gzgId = jsonObject.getLong("gzgId");
        String mobile = jsonObject.getString("mobile");
        List<User> users = gzgBUserRelationMapper.findUserByGzgIdAndLikePhone(gzgId,mobile);
        log.info("findUserByPhone function return data={}",JSON.toJSONString(users));
        return JsonUtil.getSuccessJson(users);
    }

    @Override
    public JSONObject delGzgUserRelation(String jsonParam) {
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        gzgBUserRelationMapper.delByGzgIdAndUserId(jsonObject);
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject gzgPlans(String json) {
        log.info("gzgPlans jsonParam={}",json);
        List<Map<Integer,String>> plans = gzgPlanMapper.getPlans();
        return JsonUtil.getSuccessJson(plans);
    }
}
