package com.ly.mt.blue.tooth.log.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.base.dict.CountTypeEnum;
import com.ly.mt.blue.tooth.log.service.BluetoothLogService;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogChartVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogCountInfoVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogTastInfoVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogMqVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogVo;
import com.ly.mt.blue.tooth.taste.vo.BluetoothUserTasteVo;
import com.ly.mt.blue.tooth.tobarod.vo.BluetoothUserBindVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.blue.tooth.base.dict.PropertyEnum.TASTE_LAST_PERCENT;
import static com.ly.mt.blue.tooth.base.dict.PropertyEnum.TASTE_SMOKING_MOUTH_NUMBER;
import static com.ly.mt.blue.tooth.base.util.CalcUntils.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_BLUETOOTH_LOG;

@Service
public class BluetoothLogServiceImpl extends BaseServiceImpl implements BluetoothLogService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothLogServiceImpl.class);

    /**
     * 获取首页烟弹数据
     * @param macAddress
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getBuluetoothTasteLog(String macAddress) throws Exception {
        //获取最新烟弹名称以及时间
        String userId = getLoginUserId();
        LOGGER.info("获取用户首页抽烟日志:user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject tasteJson = new JSONObject();
        tasteJson.put("user_id", userId);
        tasteJson.put("mac_address", macAddress);
        String result = callDataCenter(BLUETOOTH_USER_TASTE_GET, tasteJson); //查询最新添加烟弹
        BluetoothUserTasteVo bluetoothUserTasteVo = JSONObject.parseObject(result, BluetoothUserTasteVo.class);
        //烟弹首页对象信息（包含电量）
        BluetoothLogTastInfoVo bluetoothLogTastInfoVo = new BluetoothLogTastInfoVo();
        if(bluetoothUserTasteVo ==null){
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothLogTastInfoVo);
        }
        //查询烟杆剩余电量
        JSONObject tobaccoRodJson = new JSONObject();
        tobaccoRodJson.put("user_id", userId);
        tobaccoRodJson.put("mac_address", macAddress);
        String tobaccoRodResult = callDataCenter(BLUETOOTH_USER_BIND_GET, tobaccoRodJson);
        List<BluetoothUserBindVo> list = JSONObject.parseObject(tobaccoRodResult, new TypeReference<List<BluetoothUserBindVo>>(){});//转换为烟杆/只能查询一个绑定一个为了servcie方法通用
        if(list!=null&&!list.isEmpty()){
            bluetoothLogTastInfoVo.setElectricQuantity(list.get(0).getElectricQuantity());//剩余电量
        }
        bluetoothLogTastInfoVo.setIconUrl(bluetoothUserTasteVo.getIconUrl());//图标url
        bluetoothLogTastInfoVo.setAddTime(DateUtil.timeFormat(bluetoothUserTasteVo.getAddTime()));//添加时间
        bluetoothLogTastInfoVo.setTaste(bluetoothUserTasteVo.getTaste());//口味名称
        bluetoothLogTastInfoVo.setTasteKey(bluetoothUserTasteVo.getTasteKey());//口味KEY
        //查询该用户该烟杆总共抽了多少口
        JSONObject jsonObject = new JSONObject();//查询烟杆数据
        jsonObject.put("user_id",userId);
        jsonObject.put("mac_address",macAddress);
        //jsonObject.put("query_start_time", bluetoothUserTasteVo.getAddTime());
        //查询用户抽烟总口数
        String resultLog = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, jsonObject);
        int smokingTotal = Integer.parseInt(resultLog);

        //统计从最新添加的烟弹时间后/抽烟的总口数
        JSONObject newObject = new JSONObject();//查询烟杆数据
        newObject.put("user_id",userId);
        newObject.put("mac_address",macAddress);
        jsonObject.put("query_start_time", bluetoothUserTasteVo.getAddTime());
        String smokingResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, jsonObject);
        int smokingAddTimeTotal = Integer.parseInt(smokingResult);

        //下面查询目标设定
        JSONObject targetObeject = new JSONObject();
        targetObeject.put("user_id", userId);
        //计算用户抽烟总口数
        String totalReulst = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, targetObeject); //统计用户抽烟总数
        int totalNumberMounth = Integer.parseInt(totalReulst);//总口数
        if (totalNumberMounth > 0) {
            String dataCountResult = callDataCenter(BLUETOOTH_LOG_SMOKING_HAVE_DATA_DAY_COUNT, targetObeject); //统计有数据的天数
            List<BluetoothLogChartVo> chartList = JSONObject.parseArray(dataCountResult, BluetoothLogChartVo.class);
            int dataCount = chartList.size();
            int avg = calcAvgSmokingMouth(totalNumberMounth, dataCount);//用户日均抽烟口数
            bluetoothLogTastInfoVo.setAvgSmoking(avg);
        }else{
            bluetoothLogTastInfoVo.setAvgSmoking(0);//没有总口数时 日均抽烟口数0
        }
        //开始计算烟弹剩余和烟弹还可抽
        bluetoothLogTastInfoVo.setLast((int)TASTE_LAST_PERCENT.getValue());//烟弹剩余
        bluetoothLogTastInfoVo.setTobaccoTotalSmokingMouth(smokingTotal);//烟弹还可抽
        //从添加完最新烟弹后的抽烟数据大于0
        if(smokingAddTimeTotal>0){
            //烟弹剩余和烟弹还可抽
            bluetoothLogTastInfoVo.setLast(calcTasteLastPercent(smokingAddTimeTotal));//烟弹剩余
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothLogTastInfoVo);
    }

    /**
     * 保存蓝牙日志数据--(MQ生产者)
     * @throws Exception
     */
    @Override
    public ResponseJson saveBlueToothLog(BluetoothLogMqVo<BluetoothLogVo> bluetoothLogMqVo) throws Exception {
        //转换放入mq
        LOGGER.info("蓝牙APP-生产者MQ接收烟杆数据========================" + JSONObject.toJSONString(bluetoothLogMqVo));
        String userId = getLoginUserId();
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        try {
            LOGGER.info("蓝牙APP-生产者MQ放入消息队列开始,userId"+userId+"========================");
            bluetoothLogMqVo.setUserId(userId);
            mqService.sendMessage(RABBIT_MQ_BLUETOOTH_LOG, JSONObject.parseObject(JSONObject.toJSONString(bluetoothLogMqVo)));
            LOGGER.info("蓝牙APP-生产者MQ放入消息队列结束,userId"+userId+"========================");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "MQ接收蓝牙数据失败");
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * 统计页面-计算抽烟数据
     * @throws Exception
     */
    @Override
    public ResponseJson countBlueToothLog(String type,String macAddress,String startTime,String endTime) throws Exception{
        String userId = getLoginUserId();
        LOGGER.info("获取用户计算抽烟日志:user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        List<BluetoothLogChartVo> chartList = new ArrayList<>();//图表list

        JSONObject jsonObject = new JSONObject();//查询烟杆数据
        jsonObject.put("mac_address",macAddress);
        jsonObject.put("query_start_time",startTime);
        jsonObject.put("query_end_time",endTime);
        String totalResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, jsonObject); //累计抽烟总口数
        BluetoothLogCountInfoVo bluetoothLogCountInfoVo = new BluetoothLogCountInfoVo();//返回前端数据对象
        int smokingNumberMounthTotal = Integer.parseInt(totalResult);//抽烟总口数
        //类型为天
        String countTypeResult ="";
        if(type.equals(CountTypeEnum.DAY.getId())){//计算总数大于0在按小时计算数据
            countTypeResult = callDataCenter(BLUETOOTH_LOG_SMOKING_DATA_HOUR_COUNT, jsonObject); //按小时计算数据
            chartList = JSONObject.parseArray(countTypeResult,BluetoothLogChartVo.class);
            bluetoothLogCountInfoVo.setTotalOrAvgDayNumber(smokingNumberMounthTotal);//日抽烟总口数
            bluetoothLogCountInfoVo.setChartList(chartList);//返回连续小时数据集合
            if(smokingNumberMounthTotal==0){
                LOGGER.info("用户烟杆暂无数据:user_id"+userId+"macAddress:"+macAddress);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothLogCountInfoVo);
            }
        }else if((type.equals(CountTypeEnum.WEEK.getId())||type.equals(CountTypeEnum.MONTH.getId()))){//计算总数大于0在按小时计算天数据
            String noDataResult = callDataCenter(BLUETOOTH_LOG_SMOKING_DATA_DAY_COUNT, jsonObject); //返回连续天数
            List<BluetoothLogChartVo> chartListNew = new ArrayList<>();//连续图表list
            chartListNew =JSONObject.parseArray(noDataResult,BluetoothLogChartVo.class);//连续的数据
            bluetoothLogCountInfoVo.setChartList(chartListNew);//图表集合
            if(smokingNumberMounthTotal>0){ //抽烟口数大于0 计算日均抽烟
                countTypeResult = callDataCenter(BLUETOOTH_LOG_SMOKING_HAVE_DATA_DAY_COUNT, jsonObject); //计算有数据的实际天数
                chartList = JSONObject.parseArray(countTypeResult,BluetoothLogChartVo.class);//有数据的天数
                int dataNum = chartList.size();//有数的天数
                bluetoothLogCountInfoVo.setTotalOrAvgDayNumber(calcAvgSmokingMouth(smokingNumberMounthTotal,dataNum));//日均抽烟
            }else{
                LOGGER.info("用户烟杆暂无数据:user_id"+userId+"macAddress:"+macAddress);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothLogCountInfoVo);
            }
        }
        //只有当总口数大于0 才进行下面计算逻辑 否则在上面代码中直接返回
        int lessHarmful =calcHarmFul(smokingNumberMounthTotal);//有害物质少吸入量
        int lessCigarettes =calcLessCigarettesr(smokingNumberMounthTotal);//累计少抽香烟根数
        int smokingTimes =calcSmokingTimes(smokingNumberMounthTotal);//抽烟次数f
        if(smokingTimes==0) smokingTimes=1;//为0时 暂记一次
        if(lessCigarettes==0) lessCigarettes=1;//为0时 暂记一次
        bluetoothLogCountInfoVo.setLessHarmful(lessHarmful);//有害物质少吸入量
        bluetoothLogCountInfoVo.setSmokingNumber(smokingNumberMounthTotal);//抽烟口数
        bluetoothLogCountInfoVo.setSmokingTimes(smokingTimes);//抽烟次数
        bluetoothLogCountInfoVo.setLessCigarettes(lessCigarettes);//累计少抽香烟根数
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothLogCountInfoVo);
    }
}
