package com.ly.mt.home.tob.address.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.address.service.ShopAddressService;
import com.ly.mt.home.tob.address.vo.ShopAddressVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 商家收获地址接口
 *
 * @author linan
 * @date 20190731
 */
@Api(tags = "商家收获地址接口")
@RestController
@RequestMapping("/home/address")
public class ShopAddressController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    ShopAddressService shopAddressService;

    @PostMapping(path = "/addShopAddress")
    @ResponseBody
    @ApiOperation(value = "新增收获地址", notes = "新增收获地址")
    public ResponseJson addShopAddress(@RequestBody ShopAddressVo shopAddressVo) {
        shopAddressService.addShopAddress(shopAddressVo);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    @ApiOperation(value = "收获地址列表", notes = "收获地址列表")
    @PostMapping(path = "/shopAddressList")
    @ResponseBody
    public ResponseJson shopAddressList() {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, shopAddressService.getShopAddressList());
    }

    @ApiOperation(value = "根据id查询收货地址", notes = "根据id查询收货地址")
    @PostMapping(path = "/shopAddress")
    @ResponseBody
    public ResponseJson getShopAddressById(@ApiParam(value = "id", required = true) @RequestParam(value = "id") String id) {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, shopAddressService.getShopAddressById(id));
    }

    @ApiOperation(value = "商家默认收货地址", notes = "商家默认收货地址")
    @PostMapping(path = "/defaultShopAddress")
    @ResponseBody
    public ResponseJson defaultShopAddress() {
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, shopAddressService.getDefaultShopAddress());
    }

    @ApiOperation(value = "修改商家收货地址", notes = "修改商家收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true),
            @ApiImplicitParam(name = "provinceCode", value = "省（直辖市）代码", required = true),
            @ApiImplicitParam(name = "provinceName", value = "省（直辖市）名称", required = true),
            @ApiImplicitParam(name = "cityCode", value = "省辖市代码", required = true),
            @ApiImplicitParam(name = "cityName", value = "省辖市名称", required = true),
            @ApiImplicitParam(name = "districtCode", value = "县区代码", required = true),
            @ApiImplicitParam(name = "districtName", value = "县区名称", required = true),
            @ApiImplicitParam(name = "userAddress", value = "详细地址", required = true),
            @ApiImplicitParam(name = "isDefault", value = "是否默认地址  0-非默认地址  1-默认地址", required = true),
            @ApiImplicitParam(name = "sendLon", value = "地址经度", required = true),
            @ApiImplicitParam(name = "sendLat", value = "地址纬度", required = true),
            @ApiImplicitParam(name = "receiveName", value = "收货人姓名", required = true),
            @ApiImplicitParam(name = "receivePhone", value = "收货人电话", required = true)
    })
    @PostMapping(path = "/updateShopAddress")
    @ResponseBody
    public ResponseJson updateShopAddress(@RequestBody ShopAddressVo shopAddressVo) {
        shopAddressService.updateShopAddress(shopAddressVo);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    @ApiOperation(value = "删除商家收货地址", notes = "删除商家收货地址")
    @PostMapping(path = "/delShopAddress")
    @ResponseBody
    public ResponseJson delShopAddress(@ApiParam(value = "id", required = true) @RequestParam(value = "id") String id) {
        shopAddressService.deleteShopAddress(id);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }
}
