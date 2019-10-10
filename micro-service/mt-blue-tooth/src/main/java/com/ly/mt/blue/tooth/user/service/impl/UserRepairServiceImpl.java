package com.ly.mt.blue.tooth.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.user.service.UserRepairService;
import com.ly.mt.blue.tooth.user.vo.BlueToothRepairsResultVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RandomUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.ly.mt.blue.tooth.base.dict.RepairsStatusEnum.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.BLUETOOTH_REPAIRS_INERT;
import static com.ly.mt.core.feign.DataCenterMethod.BLUETOOTH_REPAIRS_UPDATE;

@Service
public class UserRepairServiceImpl extends BaseServiceImpl implements UserRepairService {

    private final static Logger logger = LoggerFactory.getLogger(UserRepairServiceImpl.class);

    /**
     * 填写申请表
     */
    @Override
    public ResponseJson applicationFrom(String name,String mobile,String shippingAddress){
        //封装申请json
        JSONObject paramObject = new JSONObject();
        String repairsId = RandomUtil.generateRepairID();
        paramObject.put("id", repairsId);//报修id
        paramObject.put("user_id", getLoginUserId());
        paramObject.put("name", name);//姓名
        paramObject.put("mobile", mobile);//手机号
        paramObject.put("status", FILLED.getValue());//状态 已填写
        paramObject.put("shipping_address", shippingAddress);//收货地址
        String result = callDataCenter(BLUETOOTH_REPAIRS_INERT, paramObject);
        int i =Integer.parseInt(result);
        //报修对象
        BlueToothRepairsResultVo repairsResultVo = new BlueToothRepairsResultVo();
        repairsResultVo.setId(repairsId);
        repairsResultVo.setStatus(FILLED.getValue());
        if(i>0){//大于0 生成报修单成功
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, repairsResultVo);
        }else{
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "生成报修单失败,请您稍后再试!");
        }
    }


    /**
     * 填写物流公司信息
     * @param logisticsCompany
     * @param logisticsNumber
     * @return
     */
    @Override
    public ResponseJson applicationLogistics(String repairsId,String logisticsCompany,String logisticsNumber){
        //封装物流json
        JSONObject paramObject = new JSONObject();
        paramObject.put("id", repairsId);
        paramObject.put("logistics_company", logisticsCompany);//物流公司
        paramObject.put("logistics_number", logisticsNumber);//物流编号
        paramObject.put("status", TO_AUDIT.getValue());//状态 代审核
        String result = callDataCenter(BLUETOOTH_REPAIRS_UPDATE, paramObject);
        //报修对象
        BlueToothRepairsResultVo repairsResultVo = new BlueToothRepairsResultVo();
        repairsResultVo.setId(repairsId);
        repairsResultVo.setStatus(TO_AUDIT.getValue());
        int i =Integer.parseInt(result);
        if(i>0){//大于0 生成报修单成功 并返回报修id
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, repairsResultVo);
        }else{
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "报修单id无效,填写物流信息失败");
        }
    }


    /**
     * 关闭一键换新
     */
    @Override
    public ResponseJson closeBluetoothRepairs(String repairsId){
        //封装关闭json
        JSONObject paramObject = new JSONObject();
        paramObject.put("id", repairsId);
        paramObject.put("status", CLOSED.getValue());//状态 关闭
        String result = callDataCenter(BLUETOOTH_REPAIRS_UPDATE, paramObject);
        //报修对象
        BlueToothRepairsResultVo repairsResultVo = new BlueToothRepairsResultVo();
        repairsResultVo.setId(repairsId);
        repairsResultVo.setStatus(CLOSED.getValue());
        int i =Integer.parseInt(result);
        if(i>0){//大于0 生成报修单成功 并返回报修id
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, repairsResultVo);
        }else{
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "报修单id无效,关闭报修单失败!");
        }
    }

    /**
     * 确认收货
     */
    @Override
    public ResponseJson confirmReceipt(String repairsId){
        //封装关闭json
        JSONObject paramObject = new JSONObject();
        paramObject.put("id", repairsId);
        paramObject.put("status", RECEIVED.getValue());//状态 已收货
        String result = callDataCenter(BLUETOOTH_REPAIRS_UPDATE, paramObject);
        //报修对象
        BlueToothRepairsResultVo repairsResultVo = new BlueToothRepairsResultVo();
        repairsResultVo.setId(repairsId);
        repairsResultVo.setStatus(RECEIVED.getValue());
        int i =Integer.parseInt(result);
        if(i>0){//大于0
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "确认收货成功!");
        }else{
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "报修单id无效,确认收货失败!");
        }
    }
}
