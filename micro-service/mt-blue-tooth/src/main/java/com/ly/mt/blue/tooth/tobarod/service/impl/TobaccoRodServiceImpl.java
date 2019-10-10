package com.ly.mt.blue.tooth.tobarod.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogChartVo;
import com.ly.mt.blue.tooth.tobarod.service.TobaccoRodService;
import com.ly.mt.blue.tooth.tobarod.vo.BluetoothElectricQuantityDetailVo;
import com.ly.mt.blue.tooth.tobarod.vo.BluetoothUserBindVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.dict.BluetoothBindStatus.UNBIND_STATUS;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.blue.tooth.base.dict.PropertyEnum.AVG_SMOKING_MOUTH_NUMBER;
import static com.ly.mt.blue.tooth.base.util.CalcUntils.*;

@Service
public class TobaccoRodServiceImpl  extends BaseServiceImpl implements TobaccoRodService {

    private final static Logger LOGGER = LoggerFactory.getLogger(TobaccoRodServiceImpl.class);

    /**
     * 用户绑定烟杆
     * @param name
     * @param macAddress
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson bindTobaccoRod(String name,String macAddress) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户绑定烟杆user_id:"+userId+"|MAC地址为:"+macAddress);
        /**
         * 判断改烟杆是否被其它用户绑定
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mac_address",macAddress);//查询烟杆是否被绑定
        String result = callDataCenter(BLUETOOTH_USER_BIND_GET, jsonObject);
        List<BluetoothUserBindVo> list = JSONObject.parseObject(result, new TypeReference<List<BluetoothUserBindVo>>() {});
        if(list!=null&&list.size()>0){
            BluetoothUserBindVo bluetoothUserBindVo = list.get(0);
            if(bluetoothUserBindVo.getUserId().equals(userId)){//自己绑定直接成功
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "绑定成功!");
            }else{
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "此雾化杆已被其他账号绑定");
            }
        }
        JSONObject bindObject = new JSONObject();
        bindObject.put("name", name);
        bindObject.put("mac_address", macAddress);
        bindObject.put("user_id",userId);
        String bindResult = callDataCenter(BLUETOOTH_USER_BIND_INERT, bindObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "绑定成功!");
    }

    /**
     * 用户解绑烟杆
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson unbindTobaccoRod(String id) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户解绑烟杆user_id:"+userId+"|解绑id为:"+id);
        // 调用service接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("bind_status", UNBIND_STATUS.getId());
        String result = callDataCenter(BLUETOOTH_USER_BIND_UPDATE, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "解绑成功！");
    }

    /**
     * 用户修改烟杆名称
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson modifyTobaccoRod(String id,String name) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户修改烟杆名称user_id:"+userId+"|修改id为:"+id);
        //调用service接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        String result = callDataCenter(BLUETOOTH_USER_BIND_UPDATE, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "修改成功！");
    }

    /**
     * 获取用户绑定烟杆列表
     * @throws Exception
     */
    @Override
    public ResponseJson getTobaccoRodList() throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = getLoginUserId();
        LOGGER.info("用户获取绑定烟杆列表BlueToothUserid="+id);
        jsonObject.put("user_id",id);//放入当前登录用户id
        String result = callDataCenter(BLUETOOTH_USER_BIND_GET, jsonObject);
        List<BluetoothUserBindVo> list = JSONObject.parseObject(result, new TypeReference<List<BluetoothUserBindVo>>(){});
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }

    /**
     * 烟杆设置儿童锁
     * @throws Exception
     */
    @Override
    public ResponseJson settingChildLock(String action,String id) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户设置烟杆儿童锁user_id:"+userId+"|action为:"+action);
        // 调用service接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("child_lock", action);
        String result = callDataCenter(BLUETOOTH_USER_BIND_UPDATE, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "设置成功！");
    }

    /**
     * 获取烟杆电量详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getElectricQuantityDetail(String id) throws Exception {
        String userId = getLoginUserId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);//查询烟杆
        String result = callDataCenter(BLUETOOTH_USER_BIND_GET, jsonObject);
        //查询烟杆电量剩余百分比
        List<BluetoothUserBindVo> list = JSONObject.parseObject(result, new TypeReference<List<BluetoothUserBindVo>>() {});
        if(list==null||list.isEmpty()){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "id无效,无法查询烟杆电量详情");
        }
        BluetoothUserBindVo bluetoothUserBindVo = list.get(0);
        //查询用户抽烟总口数
        JSONObject targetObeject = new JSONObject();
        targetObeject.put("user_id",userId);
        String countReulst = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, targetObeject); //统计用户抽烟总数
        int totalNumberMounth = Integer.parseInt(countReulst);//用户吸烟总口数
        int avgMouthNumber =0;//用户日均抽烟口数
        //统计总数大于0在按小时统计数据
        if(totalNumberMounth>0){
            //查询抽烟累计天数
            String dataCountResult = callDataCenter(BLUETOOTH_LOG_SMOKING_HAVE_DATA_DAY_COUNT, targetObeject); //统计有数据的天数
            List<BluetoothLogChartVo> chartList = JSONObject.parseArray(dataCountResult,BluetoothLogChartVo.class);
            int dataCount = chartList.size();//累计天数
            //计算用户日均抽烟
            avgMouthNumber=calcAvgSmokingMouth(totalNumberMounth,dataCount);//用户日均抽烟口数
        }else{
            avgMouthNumber =(int)AVG_SMOKING_MOUTH_NUMBER.getValue();//没有抽烟数据给默认值
        }
        //电量详情VO
        BluetoothElectricQuantityDetailVo bluetoothElectricQuantityDetailVo = new BluetoothElectricQuantityDetailVo();
        bluetoothElectricQuantityDetailVo.setElectricQuantity(bluetoothUserBindVo.getElectricQuantity());//剩余电量百分比
        bluetoothElectricQuantityDetailVo.setFullOfTime(calcFullOfTime(bluetoothUserBindVo.getElectricQuantity()));//电池充满时间
        bluetoothElectricQuantityDetailVo.setOnStandby(calcOnStandby(bluetoothUserBindVo.getElectricQuantity(),avgMouthNumber));//可抽天数
        bluetoothElectricQuantityDetailVo.setSmokingMouthNumber(calcSmokingMouthNumber(bluetoothUserBindVo.getElectricQuantity()));//可抽口数
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,bluetoothElectricQuantityDetailVo);
    }
}