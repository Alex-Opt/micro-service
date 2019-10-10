package com.ly.mt.user.server.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.common.entity.user.UserAddress;
import com.ly.mt.core.common.entity.user.UserAddressVo;
import com.ly.mt.core.common.util.DateUtil;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.user.mapper.UserAddressServiceMapper;
import com.ly.mt.user.server.user.service.UserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.common.constant.IdEnum.USER_ADDRESS;

/**
 * @author zhanglifeng
 * @description 用户中心-收货地址管理服务层
 * @date 2019-05-20
 */
@Service
public class UserAddressServiceImpl extends BaseServiceImpl implements UserAddressService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserAddressServiceImpl.class);

    @Resource
    private UserAddressServiceMapper userAddressServiceMapper;

    /**
     * 地址新增要根据前端传的省市区名字，查询字典表，匹配出对应的code,
     * @param json 入参
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject saveAddress(String json) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        UserAddressVo userAddressVo = JSON.parseObject(json, UserAddressVo.class);
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressVo,userAddress);
        String loginUserId =getLoginUserId();
        String loginUserName = getLoginUserName();
        userAddress.setIsDefault("0");
        //先去库里查下该用户有没有地址，没有则新增的为第一条。需要设置为默认地址
        UserAddress defaultAddressByUserId = userAddressServiceMapper.getDefaultAddressByUserId(Long.valueOf(loginUserId));
        if (null == defaultAddressByUserId) {
            userAddress.setIsDefault("1");
        }
        userAddress.setUserId(loginUserId);
        userAddress.setUserName(loginUserName);
        String id = IdUtil.getId(USER_ADDRESS);
        userAddress.setId(id);
        userAddress.setValidStatus("1");
        userAddress.setCreateTime(DateUtil.getNowTimeStr());
        int i = userAddressServiceMapper.insert(userAddress);
        result.put("flag", true);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject listAddress(String json) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        UserAddress userAddress = JSON.parseObject(json, UserAddress.class);
        PageHelper.startPage(userAddress.getPage(), userAddress.getRows());
        List<UserAddress> userAddressList = userAddressServiceMapper.listByUserId(Long.valueOf(getLoginUserId()));
        PageInfo<UserAddress> pageInfo = new PageInfo<>(userAddressList);
        result.put("total", pageInfo.getTotal());
        result.put("rows", userAddressList);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject defaultAddress(String json) throws Exception {
        Map<String, Object> result = new HashMap<>(2);
        UserAddress userAddress = JSON.parseObject(json, UserAddress.class);
        //首先更新原来该用户-userId的默认地址都为非默认。
        userAddress.setIsDefault("0");
        userAddress.setUserId(getLoginUserId());
        userAddressServiceMapper.updateDefaultAddressByUserId(userAddress);
        userAddress.setIsDefault("1");
        userAddress.setModifyTime(DateUtil.getNowTimeStr());
        //再更新具体某个id对应的地址为默认地址。
        userAddressServiceMapper.updateDefaultAddress(userAddress);
        result.put("flag", true);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject updateAddress(String json) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        UserAddressVo userAddressVo = JSON.parseObject(json, UserAddressVo.class);
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressVo,userAddress);
        userAddress.setModifyTime(DateUtil.getNowTimeStr());
        userAddressServiceMapper.updateByPrimaryKey(userAddress);
        result.put("flag", true);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject deleteAddress(String json) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        Map map = JSON.parseObject(json, Map.class);
        UserAddress record = new UserAddress();
        record.setId(map.get("id").toString());
        record.setValidStatus("2");
        //既然已经删除，就算是默认的地址，也要更新为非默认地址。
        record.setIsDefault("0");
        userAddressServiceMapper.updateValidStatusById(record);
        result.put("flag", true);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getDefaultAddressByUserId(String json) throws Exception {
        String userId = getLoginUserId();
        UserAddress userAddress = userAddressServiceMapper.getDefaultAddressByUserId(Long.valueOf(userId));
        return JsonUtil.getSuccessJson(userAddress);
    }

    @Override
    public JSONObject getAddressById(String json) throws Exception {
        Map map = JSON.parseObject(json, Map.class);
        Object id = map.get("id");
        UserAddress userAddress = userAddressServiceMapper.selectByPrimaryKey(Long.parseLong(id.toString()));
        return JsonUtil.getSuccessJson(userAddress);
    }
}
