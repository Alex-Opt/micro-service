package com.ly.mt.blue.tooth.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.log.service.BluetoothLogShareService;
import com.ly.mt.blue.tooth.log.vo.BluetoothSmokingDayDataShareVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothSmokingMonthDataShareVo;
import com.ly.mt.blue.tooth.log.vo.BluetoothSmokingWeekDataShareVo;
import com.ly.mt.blue.tooth.tobarod.vo.BluetoothUserBindVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ly.mt.blue.tooth.base.util.CalcUntils.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @program: my-blue-tooth
 * @description:用户分享service
 * @author: wanghongliang
 * @create: 2019-07-26 15:08
 **/
@Service
public class BluetoothLogShareServiceImpl extends BaseServiceImpl implements BluetoothLogShareService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothLogShareService.class);
    /**
     * 用户抽烟数据日分享
     * @param id
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson shareSmokoingDataByDay(String id, String date) throws Exception {
        /**
         * 第一个页面
         * 1：添加烟杆到今天的总天数
         * 2：时间烟杆首次添加时间
         * 3:  今天吸烟口数 统计页面带出显示
         * 4：累计吸烟口数 算这个人吸烟总口数
         * getDaySub
         */
        LOGGER.info("蓝牙APP-用户抽烟数据日分享开始=="+date);
        //查询烟杆信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        String result = callDataCenter(BLUETOOTH_USER_BIND_GET_ONE, jsonObject);
        BluetoothUserBindVo bluetoothUserBindVo = JSON.toJavaObject(JSONObject.parseObject(result),BluetoothUserBindVo.class);
        if(bluetoothUserBindVo==null){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "烟杆id不存在!");
        }
        //计算添加烟杆到今天的总天数
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd ");
        String currentDate =   dateFormat.format(new Date());//当前系统时间
        int dayCount =(int) DateUtil.getDaySub(bluetoothUserBindVo.getCreateTime(),currentDate);//添加烟杆到今天的总天数
        //查询累计吸烟口数
        JSONObject macObject = new JSONObject();//查询烟杆数据
        macObject.put("mac_address",bluetoothUserBindVo.getMacAddress());
        String totalResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, macObject); //累计抽烟总口数
        int smokingNumberMounthTotal = Integer.parseInt(totalResult);//抽烟总口数为0时
        //分享日对象Vo
        BluetoothSmokingDayDataShareVo bluetoothSmokingDataShareVo = new BluetoothSmokingDayDataShareVo();
        if(smokingNumberMounthTotal==0){//抽烟总口数为0时
            return ResponseUtil.getResponseObj(RESPONSE_CODE_ERROR, bluetoothSmokingDataShareVo);
        }
        bluetoothSmokingDataShareVo.setPubDate(bluetoothUserBindVo.getCreateTime());//烟杆添加时间
        bluetoothSmokingDataShareVo.setDayTotal(dayCount);//添加烟杆到今天的总天数
        bluetoothSmokingDataShareVo.setDayTotalSmoingMouthNumber(smokingNumberMounthTotal);//累计吸烟总口数
        /**
         * 第二个页面
         * 1:今天节省多少钱 60*(总口数/650f))
         * 2:时间烟杆首次添加时间
         * 3:今天少吸香烟根数    统计页面带出
         * 4:累计少吸烟香烟x根  （抽烟口数/650）*60根   计算累计
         */
        int totalSaveMoney=calcTotalSaveMoney(smokingNumberMounthTotal);//累计节省多少钱
        int lessCigarettes =calcLessCigarettesr(smokingNumberMounthTotal);//累计少抽香烟根数
        bluetoothSmokingDataShareVo.setDayTotalSaveMoney(totalSaveMoney);//累计节省多少钱
        bluetoothSmokingDataShareVo.setDayTotalLessCigarettes(lessCigarettes);//累计少抽香烟根数
        /**
         * 第三个页面
         * 1:加时时间= 口数*1.1，取整数，不足1取1
         * 2:时间烟杆首次添加时间
         * 3: 少吸入有害物质  统计页面带出
         * 4:累计少吸有害物质  抽烟口数*5 计算累计
         */
        int lessHarmful =calcHarmFul(smokingNumberMounthTotal);//有害物质少吸入量
        int continueOneSLifeMin = calcContinueOneSLifeMin(smokingNumberMounthTotal);//加时时间(续命多少分钟)
        bluetoothSmokingDataShareVo.setDayLessHarmful(lessHarmful);//有害物质少吸入量
        bluetoothSmokingDataShareVo.setDaycontinueOneSLifeMin(continueOneSLifeMin);//加时时间(续命多少分钟)
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothSmokingDataShareVo);
    }

    /**
     * 用户抽烟数据周分享
     * @param macAddress
     * @param startTime
     * @param endTime
     * @deprecated
     *  类型为周
     *          * 1.本周打分规则
     *          *   a.当周总口数=0时，健康指数=100分。
     *          *   b.当1≤口数≤599时，健康指数=|300-口数| / 16 +80，再四舍五入取整，最后结果在80~99分之间
     *          *   c.当口数大于等于600时，健康指数=79分
     *          * 2.时间前端为查询时间
     *          * 3.本周少吸入有害物质  统计页面带出
     *          * 4.对比上周增加/减少=（A-B）/B
     *          * 5.本周少吸香烟根数 统计页面带出
     *          * 6.累计少吸入有害物质  计算累计
     *          * 7.本周少吸相应根数 统计页面带出
     *          * 8.对比上周增加/减少=（A-B）/B
     *          * 9.累计少吸烟多少根 计算得出
     *          * 10.本周吸MOTI口数 统计页面带出
     *          * 11.对比上周增加/减少=（A-B）/B
     *          * 12.累计少吸烟多少口 计算累计
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson shareSmokoingDataByWeek(String macAddress, String startTime, String endTime) throws Exception {
        //查询本周累计吸烟口数
        JSONObject numberOfObject = new JSONObject();
        numberOfObject.put("mac_address",macAddress);
        numberOfObject.put("query_start_time",startTime);
        numberOfObject.put("query_end_time",endTime);
        String weekResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, numberOfObject); //本周累计抽烟总口数
        int smokingNumberMounthWeek = Integer.parseInt(weekResult);//周抽烟总口数
        //健康指数
        BluetoothSmokingWeekDataShareVo bluetoothSmokingWeekDataShareVo = new BluetoothSmokingWeekDataShareVo();
        bluetoothSmokingWeekDataShareVo.setWeekHealthIndex(calcWeekHealthIndex(smokingNumberMounthWeek));//健康指数
        //先计算上周开始时间与结束时间
        String lastWeekBeginDate =DateUtil.getFirstWeekLastDay(startTime);//上周周一
        String lastWeekEndDate =DateUtil.getAfterWeekLastDay(startTime);//上周周日
        //查询上周累计吸烟口数
        JSONObject lastMonthOfObject = new JSONObject();
        numberOfObject.put("mac_address",macAddress);
        numberOfObject.put("query_start_time",lastWeekBeginDate);
        numberOfObject.put("query_end_time",lastWeekEndDate);
        String lastWeekResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, numberOfObject); //上周累计抽烟总口数
        int smokingNumberMounthLastWeek = Integer.parseInt(lastWeekResult);//上周抽烟总口数

        //查询累计吸烟口数
        JSONObject totalQueryObject = new JSONObject();
        totalQueryObject.put("mac_address",macAddress);
        String totalResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, totalQueryObject); //累计抽烟总口数
        int smokingNumberMounthTotal = Integer.parseInt(totalResult);//抽烟总口数

        /**
         * 计算
         * 本周少吸入有害物质-/对比上周增加/减少-正数增加/负数减少
         * 累计少吸入有害物质-计算累计
         */
        int lessHarmful =calcHarmFul(smokingNumberMounthWeek);//本周少吸入有害物质
        int lastLessHarmful =calcHarmFul(smokingNumberMounthLastWeek);//上周少吸入有害物质
        bluetoothSmokingWeekDataShareVo.setWeekContrastLastLessHarmful(calcContrastLast(lessHarmful,lastLessHarmful));//本周少吸入有害物质-/对比上周增加/减少-正数增加/负数减少
        int totalLessHarmful =calcHarmFul(smokingNumberMounthTotal);//累计少吸入有害物质
        bluetoothSmokingWeekDataShareVo.setWeekTotalLessHarmful(totalLessHarmful);//累计少吸入有害物质-计算累计
        /**
         * 计算
         * 本周少吸烟根数-对比上周增加/减少-正数增加/负数减少
         * 累计少吸烟多少根-计算累计
         */
        int lessCigarettes =calcLessCigarettesr(smokingNumberMounthWeek);//本周少吸烟根数
        int lastLessCigarettes =calcLessCigarettesr(smokingNumberMounthLastWeek);//上周少吸烟根数
        bluetoothSmokingWeekDataShareVo.setWeekContrastLastLessCigarettes(calcContrastLast(lessCigarettes,lastLessCigarettes)); //本周少吸烟根数-对比上周增加/减少-正数增加/负数减少
        int totalLessCigarettes = calcLessCigarettesr(smokingNumberMounthTotal);//累计少吸烟多少根
        bluetoothSmokingWeekDataShareVo.setWeekTotalLessCigarettes(totalLessCigarettes);//累计少吸烟多少根
        /**
        * 计算
        * 本周吸MOTI口数-对比上周增加/减少正数增加/负数减少
        * 累计少吸烟多少口-计算累计
         * weekContrastSmoingMonthNumber
        */
        bluetoothSmokingWeekDataShareVo.setWeekContrastSmoingMouthNumber(calcContrastLast(smokingNumberMounthWeek,smokingNumberMounthLastWeek)); //本周吸MOTI口数-对比上周增加/减少正数增加/负数减少
        bluetoothSmokingWeekDataShareVo.setWeekTotalSmoingMouthNumber(smokingNumberMounthTotal);//累计少吸烟多少口
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothSmokingWeekDataShareVo);
    }

    /**
     * 用户抽烟数据月分享
     * @param macAddress
     * @param startTime
     * @param endTime
     * @deprecated
     * 类型为月时 样式为一个页面 统计时间纬度不同
         * 本月
         * 1.本月打分规则
         *   X=月总口数/4.5
         * a.X=0时，健康指数=100分。
         * b.当1≤X≤599时，健康指数=|300-口数| / 16 +80，再四舍五入取整，最后结果在80~99分之间
         * c.当X大于等于600时，健康指数=79
         * 2.时间前端为查询时间
         * 3.本月少吸入有害物质  统计页面带出
         * 4.对比上月增加/减少=（A-B）/B
         * 5.本月少吸香烟根数 统计页面带出
         * 6.累计少吸入有害物质  计算累计
         * 7.本月少吸相应根数 统计页面带出
         * 8.对比上月增加/减少=（A-B）/B
         * 9.累计少吸烟多少根 计算得出
         * 10.本月吸MOTI口数 统计页面带出
         * 11.对比上月增加/减少=（A-B）/B
         * 12.累计少吸烟多少口 计算累计
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson shareSmokoingDataByMonth(String macAddress, String startTime, String endTime) throws Exception {
        //查询本月累计吸烟口数
        JSONObject numberOfObject = new JSONObject();
        numberOfObject.put("mac_address",macAddress);
        numberOfObject.put("query_start_time",startTime);
        numberOfObject.put("query_end_time",endTime);
        String monthResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, numberOfObject); //本月累计抽烟总口数
        int smokingNumberMouthMonth = Integer.parseInt(monthResult);//月抽烟总口数
        //健康指数
        BluetoothSmokingMonthDataShareVo bluetoothSmokingMonthDataShareVo = new BluetoothSmokingMonthDataShareVo();
        bluetoothSmokingMonthDataShareVo.setMonthHealthIndex(calcMonthHealthIndex(smokingNumberMouthMonth));//健康指数
        //先计算上月开始时间与结束时间
        String lastMonthBeginDate =DateUtil.getFirstDayOfLastMonth(startTime);//上月月一
        String lastMonthEndDate =DateUtil.getAfterDayOfLastMonth(startTime);//上月月末
        //查询上月累计吸烟口数
        JSONObject lastMonthOfObject = new JSONObject();
        numberOfObject.put("mac_address",macAddress);
        numberOfObject.put("query_start_time",lastMonthBeginDate);
        numberOfObject.put("query_end_time",lastMonthEndDate);
        String lastMonthResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, numberOfObject); //上月累计抽烟总口数
        int smokingNumberMounthLastMonth = Integer.parseInt(lastMonthResult);//上月抽烟总口数

        //查询累计吸烟口数
        JSONObject totalQueryObject = new JSONObject();
        totalQueryObject.put("mac_address",macAddress);
        String totalResult = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, totalQueryObject); //累计抽烟总口数
        int smokingNumberMounthTotal = Integer.parseInt(totalResult);//抽烟总口数

        /**
         * 计算
         * 本月少吸入有害物质-/对比上月增加/减少-正数增加/负数减少
         * 累计少吸入有害物质-计算累计
         */
        int lessHarmful =calcHarmFul(smokingNumberMouthMonth);//本月少吸入有害物质
        int lastLessHarmful =calcHarmFul(smokingNumberMounthLastMonth);//上月少吸入有害物质
        bluetoothSmokingMonthDataShareVo.setMonthContrastLastLessHarmful(calcContrastLast(lessHarmful,lastLessHarmful));//本月少吸入有害物质-/对比上月增加/减少-正数增加/负数减少
        int totalLessHarmful =calcHarmFul(smokingNumberMounthTotal);//累计少吸入有害物质
        bluetoothSmokingMonthDataShareVo.setMonthTotalLessHarmful(totalLessHarmful);//累计少吸入有害物质-计算累计
        /**
         * 计算
         * 本月少吸烟根数-对比上月增加/减少-正数增加/负数减少
         * 累计少吸烟多少根-计算累计
         */
        int lessCigarettes =calcLessCigarettesr(smokingNumberMouthMonth);//本月少吸烟根数
        int lastLessCigarettes =calcLessCigarettesr(smokingNumberMounthLastMonth);//上月少吸烟根数
        bluetoothSmokingMonthDataShareVo.setMonthContrastLastLessCigarettes(calcContrastLast(lessCigarettes,lastLessCigarettes)); //本月少吸烟根数-对比上月增加/减少-正数增加/负数减少
        int totalLessCigarettes = calcLessCigarettesr(smokingNumberMounthTotal);//累计少吸烟多少根
        bluetoothSmokingMonthDataShareVo.setMonthTotalLessCigarettes(totalLessCigarettes);//累计少吸烟多少根
        /**
         * 计算
         * 本月吸MOTI口数-对比上月增加/减少正数增加/负数减少
         * 累计少吸烟多少口-计算累计
         * weekContrastSmoingMonthNumber
         */
        bluetoothSmokingMonthDataShareVo.setMonthContrastSmoingMouthNumber(calcContrastLast(smokingNumberMouthMonth,smokingNumberMounthLastMonth)); //本月吸MOTI口数-对比上月增加/减少正数增加/负数减少
        bluetoothSmokingMonthDataShareVo.setMonthTotalSmoingMouthNumber(smokingNumberMounthTotal);//累计少吸烟多少口
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothSmokingMonthDataShareVo);
    }
}