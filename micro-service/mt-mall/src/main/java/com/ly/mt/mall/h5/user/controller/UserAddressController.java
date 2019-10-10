package com.ly.mt.mall.h5.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.mall.h5.user.service.UserAddressService;
import com.ly.mt.mall.h5.user.vo.UserAddressVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_OPERATOR_TOO_FASTER;
import static com.ly.mt.core.redis.RedisKey.*;


@Api(description = "用户收货地址管理接口")
@RestController
@RequestMapping("/mall/h5/userAddress")
public class UserAddressController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserAddressController.class);

    @Resource
    UserAddressService userAddressService;
    @Resource
    public RedisService redisServer;

    @ApiOperation(value = "新增收货地址接口", notes = "根据用户id保存用户的姓名，电话，省市区name,code值，收货地址详情")
    @ApiImplicitParam(name = "request", value = "入参包含：收货人姓名-receiveName，收货人电话-receivePhone，" +
            "省（直辖市）code-provinceCode,省（直辖市）名称-provinceName,省辖市code-cityCode,省辖市名称-cityName," +
            "县区code-districtCode,县区名称-districtName,详细地址-userAddress，lat-地址维度，lon-地址经度")
    @PostMapping("/saveAddress")
    public ResponseJson saveAddress(HttpServletRequest request) {
        try {
            String userId = request.getSession().getAttribute("userId").toString();
            String clickToken = redisServer.get(REDIS_REPEAT_CLICK_ACTION_ADD_ADDRESS, userId);
            if (StringUtil.isEmpty(clickToken)) {
                redisServer.set(REDIS_REPEAT_CLICK_ACTION_ADD_ADDRESS, userId, userId, 6L, TimeUnit.SECONDS);
            } else {
                return ResponseUtil.getResponseCode(RESPONSE_CODE_OPERATOR_TOO_FASTER);
            }
            JSONObject jsonObject = getJSONObject(request);
            LOGGER.info("UserAddressController.saveAddress 收货地址添加接口 入参:{}", jsonObject.toString());

            jsonObject = parseAndRequire(UserAddressVo.class, JSONObject.toJSONString(jsonObject),
                    new String[]{"receiveName", "receivePhone", "provinceCode", "provinceName", "cityCode", "cityName", "districtCode", "districtName", "userAddress", "lon", "lat"});
            ResponseJson responseJson = userAddressService.saveAddress(jsonObject.toJSONString());
            redisServer.del(REDIS_REPEAT_CLICK_ACTION_ADD_ADDRESS, userId);
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("新增收货地址接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "收货地址查询列表接口", notes = "根据用户id查询用户的地址列表")
    @ApiImplicitParam(name = "request", value = "用户id,这里后台可以从session中获取，不用前端传入参")
    @PostMapping("/listAddress")
    public ResponseJson listAddress(HttpServletRequest request,
                                    @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                    @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        try {
            return userAddressService.listAddress(page, rows);
        } catch (Exception e) {
            LOGGER.error("收货地址查询列表接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "设置为默认收货地址接口", notes = "根据用户id设置地址对应的id的状态设置为默认地址")
    @ApiImplicitParam(name = "request", value = "设置为默认地址的地址id")
    @PostMapping("/defaultAddress")
    public ResponseJson defaultAddress(@ApiParam(value = "地址Id", required = true) @RequestParam(value = "id") String id) {
        try {
            return userAddressService.defaultAddress(id);
        } catch (Exception e) {
            LOGGER.error(" 设置为默认收货地址接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "收货地址修改接口", notes = "根据用户的id修改一条收货地址")
    @ApiImplicitParam(name = "request", value = "地址信息")
    @PostMapping("/updateAddress")
    public ResponseJson updateAddress(HttpServletRequest request) {
        JSONObject jsonObject = null;
        String userId = request.getSession().getAttribute("userId").toString();
        String clickToken = redisServer.get(REDIS_REPEAT_CLICK_ACTION_UPDATE_ADDRESS, userId);
        if (StringUtil.isEmpty(clickToken)) {
            redisServer.set(REDIS_REPEAT_CLICK_ACTION_UPDATE_ADDRESS, userId, userId, 6L, TimeUnit.SECONDS);
        } else {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_OPERATOR_TOO_FASTER);
        }
        try {
            jsonObject = getJSONObject(request);
            jsonObject = parseAndRequire(UserAddressVo.class, JSONObject.toJSONString(jsonObject),
                    new String[]{"id", "receiveName", "receivePhone", "provinceCode", "provinceName", "cityCode", "cityName", "districtCode", "districtName", "userAddress", "lon", "lat", "isDefault"});
            ResponseJson responseJson = userAddressService.updateAddress(jsonObject.toJSONString());
            redisServer.del(REDIS_REPEAT_CLICK_ACTION_UPDATE_ADDRESS, userId);
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("收货地址修改接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "收货地址删除接口", notes = "根据用户id删除一条收货地址")
    @ApiImplicitParam(name = "id", value = "地址id")
    @PostMapping("/deleteAddress")
    public ResponseJson deleteAddress(@ApiParam(value = "地址Id", required = true) @RequestParam(value = "id") String id) {
        try {
            if (StringUtil.isEmpty(id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return userAddressService.deleteAddress(id);
        } catch (Exception e) {
            LOGGER.info("收货地址删除接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @ApiOperation(value = "查询用户默认收货地址接口", notes = "根据用户id查询用户默认收货地址")
    @ApiImplicitParam(name = "request", value = "无入参，登陆过即可")
    @PostMapping("/queryUserDefaultAddress")
    public ResponseJson queryUserDefaultAddress(HttpServletRequest request) {
        try {
            return userAddressService.getDefaultAddressByUserId();
        } catch (Exception e) {
            LOGGER.info("查询用户默认收货地址接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation(value = "根据地址id查询收货地址接口", notes = "根据地址id查询收货地址接口")
    @ApiImplicitParam(name = "id", value = "需要地址的id")
    @PostMapping("/queryAddressById")
    public ResponseJson queryOneAddressById(@ApiParam(value = "地址Id", required = true) @RequestParam(value = "id") String id) {
        try {
            if (StringUtil.isEmpty(id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数为空");
            }
            return userAddressService.getAddressById(id);
        } catch (Exception e) {
            LOGGER.info("根据地址id查询收货地址接口出错", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 获取request请求参数
     * @Author taoye
     */
    public static JSONObject getJSONObject(HttpServletRequest request) {
        //请求参数
        String parameters = "";
        if ("GET".equals(request.getMethod())) {
            //GET请求时的参数 网址中的参数
            parameters = request.getQueryString();
        } else if ("POST".equals(request.getMethod())) {
            //POST请求时的参数 表单及网址中全部参数
            String totalParameter = "";
            Map<String, String[]> params = request.getParameterMap();
            //参数个数
            int parametersNum = request.getParameterMap().size();
            int flag = 1;
            for (String key : params.keySet()) {
                String[] values = params.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    totalParameter += key + "=" + value;
                }
                if (flag < parametersNum) {
                    totalParameter += "&";
                }
                flag += 1;
            }
            parameters = totalParameter;
        }
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(parameters)) {
            String[] parameterArr = parameters.split("&");
            for (int i = 0; i < parameterArr.length; i++) {
                String[] paramArr = parameterArr[i].split("=");
                String paramValue = "";
                if (paramArr.length > 1) {
                    String value = paramArr[1];
                    if (StringUtil.isNotEmpty(value)) {
                        try {
                            // 去除前后空格
                            paramValue = URLDecoder.decode(value, "UTF-8").trim();
                        } catch (Exception e) {
                            LOGGER.error("request参数key={},value={}转json出错:", paramArr[0], value, e);
                        }
                    }
                }
                jsonObject.put(paramArr[0], paramValue);
            }
        }
        return jsonObject;
    }


    /**
     * 解析并判断是否存在必填字段
     *
     * @param tClass
     * @param params        json入参
     * @param requireFileds 必填字段
     * @param <T>
     * @return
     */
    public static <T> JSONObject parseAndRequire(Class<T> tClass, String params, String... requireFileds) throws Exception {
        T t = null;
        try {
            t = JSON.parseObject(params, tClass);
        } catch (Exception e) {
            throw new Exception("参数类型转换异常:" + e.getMessage());
        }
        //判断必填项是否已填
        if (requireFileds != null && requireFileds.length > 0) {
            Field[] fields = tClass.getDeclaredFields();
            int i = 0, j = 0;
            while (i < fields.length && j < requireFileds.length) {
                if (fields[i].getName().equals(requireFileds[j])) {
                    fields[i].setAccessible(true);
                    try {
                        if (fields[i].get(t) == null || "".equals(fields[i].get(t))) {
                            throw new Exception("必填项:" + requireFileds[j] + "对应的参数为空");
                        } else {
                            j++;
                            i = 0;
                        }
                    } catch (IllegalAccessException e) {
                        throw new Exception("判断必填项异常");
                    }
                } else {
                    i++;
                }
                if (i == fields.length) {
                    throw new Exception("必填项:" + requireFileds[j] + "对应的参数为空");
                }
            }
        }
        return JSONObject.parseObject(JSON.toJSONString(t));
    }
}
