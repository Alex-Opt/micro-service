package com.ly.mt.user.web.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.user.UserAddressVo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.user.web.base.controller.BaseController;
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

import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.method.UserMethodEnum.*;


@Api(description = "用户收货地址管理接口")
@RestController
@RequestMapping("/user/address")
public class UserAddressController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserAddressController.class);


    @ApiOperation(value = "新增收货地址接口", notes = "根据用户id保存用户的姓名，电话，省市区name,code值，收货地址详情")
    @ApiImplicitParam(name = "request", value = "入参包含：收货人姓名-receiveName，收货人电话-receivePhone，" +
            "省（直辖市）code-provinceCode,省（直辖市）名称-provinceName,省辖市code-cityCode,省辖市名称-cityName," +
            "县区code-districtCode,县区名称-districtName,详细地址-userAddress，前端回显定位值-pickerValue")
    @PostMapping("/saveAddress")
    public JSONObject saveAddress(HttpServletRequest request) {
        JSONObject jsonObject = JsonUtil.getJSONObject(request);
        LOGGER.info("UserAddressController.saveAddress 收货地址添加接口 入参:{}", jsonObject.toString());
        try {
            jsonObject = JsonUtil.parseAndRequire(UserAddressVo.class, JSONObject.toJSONString(jsonObject),
                    new String[]{"receiveName", "receivePhone", "provinceCode", "provinceName", "cityCode", "cityName", "districtCode", "districtName", "userAddress", "pickerValue"});
        } catch (Exception e) {
            LOGGER.error("接口入参存在非空字段", e.getMessage());
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject2 = callUserServer(USER_ADDRESS_SAVE, jsonObject);
        LOGGER.info("UserAddressController.saveAddress 收货地址添加接口 出参:{}", jsonObject2.toString());
        return jsonObject2;
    }


    @ApiOperation(value = "收货地址查询列表接口", notes = "根据用户id查询用户的地址列表")
    @ApiImplicitParam(name = "request", value = "用户id,这里后台可以从session中获取，不用前端传入参")
    @PostMapping("/listAddress")
    public JSONObject listAddress(HttpServletRequest request,
                                  @ApiParam(value = "页号", required = true) @RequestParam(value = "page") int page,
                                  @ApiParam(value = "每页条数", required = true) @RequestParam(value = "rows") int rows) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        return callUserServer(USER_ADDRESS_LIST, jsonObject);
    }


    @ApiOperation(value = "设置为默认收货地址接口", notes = "根据用户id设置地址对应的id的状态设置为默认地址")
    @ApiImplicitParam(name = "request", value = "设置为默认地址的地址id")
    @PostMapping("/defaultAddress")
    public JSONObject defaultAddress(@ApiParam(value = "地址Id", required = true) @RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        LOGGER.info("UserAddressController.defaultAddress 设置为默认收货地址接口 入参:{}", jsonObject.toString());
        return callUserServer(USER_ADDRESS_SET_DEFAULT, jsonObject);
    }


    @ApiOperation(value = "收货地址修改接口", notes = "根据用户的id修改一条收货地址")
    @ApiImplicitParam(name = "request", value = "地址信息")
    @PostMapping("/updateAddress")
    public JSONObject updateAddress(HttpServletRequest request) {
        JSONObject jsonObject = JsonUtil.getJSONObject(request);
        try {
            jsonObject = JsonUtil.parseAndRequire(UserAddressVo.class, JSONObject.toJSONString(jsonObject),
                    new String[]{"id", "receiveName", "receivePhone", "provinceCode", "provinceName", "cityCode", "cityName", "districtCode", "districtName", "userAddress", "pickerValue"});
        } catch (Exception e) {
            LOGGER.error("接口入参存在非空字段", e.getMessage());
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        LOGGER.info("UserAddressController.updateAddress 收货地址修改接口 入参:{}", jsonObject.toString());
        return callUserServer(USER_ADDRESS_UPDATE, jsonObject);
    }


    @ApiOperation(value = "收货地址删除接口", notes = "根据用户id删除一条收货地址")
    @ApiImplicitParam(name = "id", value = "地址id")
    @PostMapping("/deleteAddress")
    public JSONObject deleteAddress(@ApiParam(value = "地址Id", required = true) @RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        LOGGER.info("UserAddressController.deleteAddress 收货地址删除接口 入参:{}", jsonObject.toString());
        return callUserServer(USER_ADDRESS_DELETE, jsonObject);
    }


    @ApiOperation(value = "查询用户默认收货地址接口", notes = "根据用户id查询用户默认收货地址")
    @ApiImplicitParam(name = "request", value = "无入参，登陆过即可")
    @PostMapping("/queryUserDefaultAddress")
    public JSONObject queryUserDefaultAddress(HttpServletRequest request) {
        return callUserServer(USER_ADDRESS_GET_DELETE, new JSONObject());
    }

    @ApiOperation(value = "根据地址id查询收货地址接口", notes = "根据地址id查询收货地址接口")
    @ApiImplicitParam(name = "id", value = "需要地址的id")
    @PostMapping("/queryAddressById")
    public JSONObject queryOneAddressById(@ApiParam(value = "地址Id", required = true) @RequestParam(value = "id") String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        return callUserServer(USER_ADDRESS_GET_BY_ID, jsonObject);
    }
}
