package com.ly.mt.blue.tooth.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogDB;
import com.ly.mt.blue.tooth.mq.service.RabbitMqService;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogMqVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogVo;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.BluetoothBindStatus.BIND_STATUS;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BLUETOOTH_LOG;
import static com.ly.mt.core.feign.DataCenterMethod.BLUETOOTH_INSERT_LOG;
import static com.ly.mt.core.feign.DataCenterMethod.BLUETOOTH_USER_BIND_UPDATE_BY_CONDTIONS;

;


/**
 * mq消费业务处理层
 * @author whl
 */
@Service
public class RabbitMqServiceImpl extends BaseServiceImpl implements RabbitMqService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMqServiceImpl.class);

    /**
     * 保存蓝牙烟杆抽烟数据
     * @param bluetoothLogMqVo 蓝牙抽烟数据
     * @throws Exception
     */
    @Override
    public void handlerBlueToothLogMq(BluetoothLogMqVo<BluetoothLogVo> bluetoothLogMqVo) throws Exception {
        try {
            LOGGER.info("蓝牙APP-保存蓝牙烟杆抽烟数据==user_id"+bluetoothLogMqVo.getUserId()+"|当前电量:"+bluetoothLogMqVo.getElectricQuantity()+"开始==");
            List<BluetoothLogVo> list =  JSONObject.parseObject(bluetoothLogMqVo.getBluetoothLogVoList().toString(), new TypeReference<List<BluetoothLogVo>>(){});
            //蓝牙数据为空时不进去入库操作
            if(list!=null&&list.size()>0){
                List<BluetoothLogDB> bluetoothLogDBList = new ArrayList<>();
                //循环变成数据库对象
                list.forEach(bluetoothLogVo -> {
                    BluetoothLogDB bluetoothLogDB = new BluetoothLogDB();
                    bluetoothLogDB.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_LOG));
                    bluetoothLogDB.setUser_id(bluetoothLogMqVo.getUserId());//用户id
                    bluetoothLogDB.setMac_address(bluetoothLogMqVo.getMacAddress());//mac地址
                    bluetoothLogDB.setPower(bluetoothLogVo.getPower());//功率
                    bluetoothLogDB.setTime_stamp(bluetoothLogVo.getTimeStamp());//时间戳
                    bluetoothLogDB.setTime(bluetoothLogVo.getTime());//时长
                    bluetoothLogDB.setCreate_time(DateUtil.getNowTimeStr());
                    bluetoothLogDBList.add(bluetoothLogDB);
                });
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bluetoothLogList", bluetoothLogDBList);
                callDataCenter(BLUETOOTH_INSERT_LOG, jsonObject);
            }else{
                LOGGER.info("保存蓝牙烟杆抽烟数据为空,不进行写库操作==user_id"+bluetoothLogMqVo.getUserId()+"|结束==");
            }
            LOGGER.info("蓝牙APP-开始更新剩余电量："+bluetoothLogMqVo.getUserId()+"|当前电量:"+bluetoothLogMqVo.getElectricQuantity());
            // 调用service接口
            JSONObject upObject = new JSONObject();
            upObject.put("user_id", bluetoothLogMqVo.getUserId());//userId
            upObject.put("mac_address", bluetoothLogMqVo.getMacAddress());//mac地址
            upObject.put("electric_quantity", bluetoothLogMqVo.getElectricQuantity());//剩余电量
            upObject.put("bind_status", BIND_STATUS.getId());//只更新绑定状态的
            String result = callDataCenter(BLUETOOTH_USER_BIND_UPDATE_BY_CONDTIONS, upObject);
            LOGGER.info("蓝牙APP-结束更新剩余电量："+bluetoothLogMqVo.getUserId()+"|当前电量:"+bluetoothLogMqVo.getElectricQuantity());
            LOGGER.info("保存蓝牙烟杆抽烟数据==user_id"+bluetoothLogMqVo.getUserId()+"|结束==");
        } catch (Exception e) {
            LOGGER.error("同步蓝牙烟杆抽烟数据异常|userId:"+bluetoothLogMqVo.getUserId()+"|macAddress:"+bluetoothLogMqVo.getMacAddress(),e);
        }
    }
}
