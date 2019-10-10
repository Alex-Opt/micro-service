package com.ly.mt.cabinet.c.rujin.service.lmpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.order.entity.GzgName;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.programme.entity.GzgProgramme;
import com.ly.mt.cabinet.c.rujin.entity.GzgRujinRelation;
import com.ly.mt.cabinet.c.rujin.entity.RujinDoEnum;
import com.ly.mt.cabinet.c.rujin.entity.RujinUrlEnum;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.cabinet.c.tools.HttpClientUtil;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
@PropertySource(value = {"classpath:myconfig-c.properties"},ignoreResourceNotFound=false,encoding="UTF-8")
public class GzgRujinDeviceServiceImpl extends BaseServiceImpl implements GzgRujinDeviceService {
    private final static Logger log = LoggerFactory.getLogger(GzgRujinDeviceServiceImpl.class);
    @Value("${rujin.skey}")
    private String skey;


    @Override
    public ResponseJson bindRunjinDevice(String tid,String plan_name) throws Exception {
        log.info("开始绑定如金设备，绑定设备编号：{}",tid);
        String url = RujinUrlEnum.RUJIN_BIND.getValue();//绑定终端设备信息
        long times = System.currentTimeMillis();
        String str = tid+times+skey;
        String token = MD5Util.md5(str).toLowerCase();
        JSONObject json =new JSONObject();
        json.put("token",token);
        json.put("times",times);
        json.put("tid",tid);
        JSONObject jsonObject = HttpClientUtil.httpPost(url, json);
        String error = jsonObject.getString("error");
        if(!"0".equals(error)){
            log.info("设备编号为 {} 绑定失败，失败原因 {}",tid,jsonObject.getString("emsg"));
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,jsonObject.getString("emsg"));
        }
        JSONObject data = jsonObject.getJSONObject("data");
        String id = data.getString("id");
        String tname = data.getString("tname");
        JSONArray hglist = data.getJSONArray("hdlist");
        String hgsn = hglist.getJSONObject(0).getString("hgsn");
        String hdtol = hglist.getJSONObject(0).getString("hdtol");
        JSONObject jsonGzgRujinRelation = new JSONObject();
        jsonGzgRujinRelation.put("id",SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_GZG_RUJIN_RELATION));
        jsonGzgRujinRelation.put("name",tid);
        jsonGzgRujinRelation.put("tid",id);
        jsonGzgRujinRelation.put("tname",tname);
        jsonGzgRujinRelation.put("hg",hgsn);
        jsonGzgRujinRelation.put("hd",hdtol);
        jsonGzgRujinRelation.put("create_time", DateUtil.getNowDateStr());
        jsonGzgRujinRelation.put("plan_name",plan_name);
        updateAndInsertGzgRunjin(jsonGzgRujinRelation);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    @Override
    public Boolean queryHgState(String tid, String hd,String do1) throws Exception {
        String url = RujinUrlEnum.RUJIN_HGSTATE.getValue();//查询终端在线状态
        JSONObject jsonObject =new JSONObject();
        long times = System.currentTimeMillis();
        String hg = "1";//如金所有4个格子的货柜号都是1
        //String do1 = "1";//do=操作： 0=查询， 1=清除 货道故障(此处0和1的操作是一样的，所以简化只1操作)
        jsonObject.put("tid",tid);
        jsonObject.put("hg",hg);
        jsonObject.put("hd",hd);
        jsonObject.put("do",do1);
        jsonObject.put("times",times);
        String str = tid+hg+hd+times+skey;
        String token = MD5Util.md5(str).toLowerCase();
        jsonObject.put("token",token);

        JSONObject jsonResult = HttpClientUtil.httpPost(url, jsonObject);
        String error = jsonResult.getString("error");
        if(!"0".equals(error)){
            log.info("设备编号为 {} 查询终端货柜状态失败，失败原因 {}",tid,jsonObject.getString("emsg"));
            return false;
        }
        JSONObject data = jsonResult.getJSONObject("data");
        String state = data.getString("error");
        if("0".equals(state)){//error: 货道故障： 0=无， 1=有
            return true;
        }
        return false;
    }



    public Boolean hgState(String tid, String hd,RujinDoEnum rujinDoEnum)throws Exception {
        String url = RujinUrlEnum.RUJIN_HGSTATE.getValue();//查询终端在线状态
        JSONObject jsonObject =new JSONObject();
        long times = System.currentTimeMillis();
        String hg = "1";
        jsonObject.put("tid",tid);
        jsonObject.put("hg",hg);
        jsonObject.put("hd",hd);
        if("3".equals(rujinDoEnum.getKey())){
             jsonObject.put("do","0");
        }else {
             jsonObject.put("do","1");
        }
        jsonObject.put("times",times);
        String str = tid+hg+hd+times+skey;
        String token = MD5Util.md5(str).toLowerCase();
        jsonObject.put("token",token);

        JSONObject jsonResult = HttpClientUtil.httpPost(url, jsonObject);
        String error = jsonResult.getString("error");
        if(!"0".equals(error)){
            log.info("设备编号为 {} 查询终端货柜状态失败，失败原因 {}",tid,jsonObject.getString("emsg"));
            return false;
        }
        JSONObject data = jsonResult.getJSONObject("data");
        String state = data.getString("error");
        if("0".equals(state)){//error: 货道故障： 0=无， 1=有
            return true;
        }
        return false;
    }

    @Override
    public List<GzgOrderItem> getGzgOrderItems(String orderNo) {
        JSONObject jsonGzgOrderItem = new JSONObject();
        jsonGzgOrderItem.put("order_id", orderNo);
        String resultGzgOrderItem = callDataCenter(GZG_ORDER_ITEMS_GET, jsonGzgOrderItem);
        return JSONArray.parseArray(resultGzgOrderItem, GzgOrderItem.class);
    }

    @Override
    public String insertRuJinGzgName(String name) {
        JSONObject param = new JSONObject();
        String id = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_ACTIVITY_SIGN_UP_CABINET);
        param.put("id",id);
        param.put("name",name);
        try{
            callDataCenter(GZG_NAME_INSERT,param);
        }catch (Exception e){
            return "";
        }

        return id;
    }

    @Override
    public List<GzgName> getGzgNameBatch(String start,String size) {
        JSONObject o = new JSONObject();
        o.put("start",start);
        o.put("size",size);
        String result = callDataCenter(GET_GZG_NAME_LIST,o);
        if(StringUtil.isNotEmpty(result)){
            return JSONObject.parseArray(result,GzgName.class);
        }else{
            return new ArrayList();
        }

    }

    @Override
    public Boolean isOnline(String tid) throws Exception {
        log.info("进入是否在线方法");
        String url = RujinUrlEnum.RUJIN_ONLINE.getValue();//查询终端在线状态
        long times = System.currentTimeMillis();
        String str = tid+times+skey;
        String token = MD5Util.md5(str).toLowerCase();
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("token",token);
        jsonObject.put("times",times);
        jsonObject.put("tid",tid);
        log.info("向如金发起请求");
        JSONObject jsonResult = HttpClientUtil.httpPost(url, jsonObject);
        log.info("如金在线状态接口 返回:{}",jsonObject.toJSONString());
        String error = jsonResult.getString("error");
        if(!"0".equals(error)){
            log.info("设备编号为 {} 是否在线失败，失败原因 {}",tid,jsonObject.getString("emsg"));
            return false;
        }
        JSONObject data = jsonResult.getJSONObject("data");
        String state = data.getString("state");
        if("1".equals(state)){//状态（1=在线， 0=离线）
            return true;
        }
        return false;
    }


    //购买之后，如果第一次开门失败，要等10秒后第二次开门，第三次开门。最多三次重复开门
    @Override
    public Boolean openRujinDevice(String orderId, String tid, String cabinet_no, GzgPayTypeEnum gzgPayTypeEnum) throws Exception {
        String url = RujinUrlEnum.RUJIN_ORDER.getValue();//打开设备
        log.info("打开如今柜子,货道号={},订单号={}",cabinet_no,orderId);
        JSONObject jsonResult = sendOpenCommand(orderId, tid, cabinet_no, gzgPayTypeEnum, url);
        String error = jsonResult.getString("error");
        if(!"0".equals(error)){
            log.info("第一次打开设备编号为 {} 失败，失败原因 {}",tid,jsonResult.getString("emsg"));
            return false;
        }
        JSONObject data = jsonResult.getJSONObject("data");
        String state = data.getString("state");
        if("1".equals(state)){ //state=开门指令， 0 未发送， 1=已发送
            log.info("第一次打开设备编号为 {} 命令发出，请查看回调接口信息",tid);
            return true;
        }else{
            //等待10秒
            Thread.sleep(10000);
            JSONObject jsonResult1 = sendOpenCommand(orderId, tid, cabinet_no, gzgPayTypeEnum, url);
            if(!"0".equals(jsonResult1.getString("error"))) {
                log.info("第二次打开设备编号为 {} 失败，失败原因 {}", tid, jsonResult1.getString("emsg"));
                return false;
            }

            if("1".equals(jsonResult1.getJSONObject("data").getString("state"))){ //state=开门指令， 0 未发送， 1=已发送
                log.info("第二次打开设备编号为 {} 命令发出，请查看回调接口信息",tid);
                return true;
            } else{//等待10秒
                Thread.sleep(10000);
                JSONObject jsonResult2 = sendOpenCommand(orderId, tid, cabinet_no, gzgPayTypeEnum, url);
                if(!"0".equals(jsonResult2.getString("error"))) {
                    log.info("第三次打开设备编号为 {} 失败，失败原因 {}", tid, jsonResult2.getString("emsg"));
                    return false;
                }
                if("1".equals(jsonResult2.getJSONObject("data").getString("state"))){ //state=开门指令， 0 未发送， 1=已发送
                    log.info("第三次打开设备编号为 {} 命令发出，请查看回调接口信息",tid);
                    return true;
                }
                return false;
            }
        }
    }

    private JSONObject sendOpenCommand(String orderId, String tid, String cabinet_no, GzgPayTypeEnum gzgPayTypeEnum, String url) throws Exception {
        Calendar rightNow  = Calendar.getInstance();
        String year = rightNow.get(Calendar.YEAR)+"";
        String hg = "1";
        String hd = cabinet_no;
        String order = orderId;
        String money = "0.01";
        String goods = "魔笛电子烟";
        String pay = gzgPayTypeEnum.getKey();
        long times = System.currentTimeMillis();
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("year",year);
        jsonObject.put("tid",tid);
        jsonObject.put("hg",hg);
        jsonObject.put("hd",hd);
        jsonObject.put("order",order);
        jsonObject.put("money",money);
        jsonObject.put("goods",goods);
        jsonObject.put("pay",pay);
        jsonObject.put("times",times);

        String str = order+tid+hg+hd+times+skey;
        String token = MD5Util.md5(str).toLowerCase();
        jsonObject.put("token",token);
        return HttpClientUtil.httpPost(url, jsonObject);
    }

    /**
     *
     * @param tid
     * @param cabinet_no 多个货道号可以用“-” 区分，如： 1-3-5-6，表示 1 号， 3 号， 5 号，6 号 4 个货道同时开门
     * @return
     * @throws Exception
     */
    @Override
    public Boolean maintainDevice(String tid, String cabinet_no, RujinDoEnum rujinDoEnum) throws Exception {

        String url = RujinUrlEnum.RUJIN_SERVER.getValue();//维护打开门
        String hg = "1";
        String hd = cabinet_no;
        long times = System.currentTimeMillis();
        JSONObject jsonObject =new JSONObject();

        jsonObject.put("tid",tid);
        jsonObject.put("hg",hg);
        jsonObject.put("hd",hd);
        jsonObject.put("do",rujinDoEnum.getKey());
        String str = tid+hg+hd+times+skey;
        String token = MD5Util.md5(str).toLowerCase();
        jsonObject.put("times",times);
        jsonObject.put("token",token);
        JSONObject jsonResult = HttpClientUtil.httpPost(url, jsonObject);
        String error = jsonResult.getString("error");
        if(!"0".equals(error)){
            log.info("维护开门请求失败，编号为 {} 失败，失败原因 {}",tid,jsonResult.getString("emsg"));
            return false;
        }
        return true;
    }

    @Override
    public ResponseJson maintainDeviceByTname(String tname, String cabinet_no, RujinDoEnum rujinDoEnum) throws Exception {
        GzgRujinRelation gzgRujinRelation = getGzgRujinRelationByTname(tname.toUpperCase());
        if(gzgRujinRelation == null){
            return  ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"参数异常");
        }
        String tid = gzgRujinRelation.getTid();
        Boolean aBoolean = maintainDevice(tid, cabinet_no, rujinDoEnum);
        if(aBoolean){
            return  ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }else {
            return  ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 通过name获取GzgRujinRelation
     * @param name
     * @return
     */
    public GzgRujinRelation getGzgRujinRelationByName(String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        String result = callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME, jsonObject);
        GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(JSONObject.parseObject(result), GzgRujinRelation.class);
        return gzgRujinRelation;
    }

    /**
     * 通过tname获取GzgRujinRelation
     * @param tname
     * @return
     */
    public GzgRujinRelation getGzgRujinRelationByTname(String tname) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tname",tname);
        String result = callDataCenter(GZG_RUJIN_RELATION_GET_BY_TNAME, jsonObject);
        GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(JSONObject.parseObject(result), GzgRujinRelation.class);
        return gzgRujinRelation;
    }


    /**
     * 通过name获取配货方案
     * @param name
     * @return
     */
    public List<GzgProgramme> getGzgProgrammeByName(String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        String result = callDataCenter(GZG_PROGRAMME_GET_LIST_BY_NAME, jsonObject);
        List<GzgProgramme> gzgProgrammeList = JSONArray.parseArray(result, GzgProgramme.class);
        return gzgProgrammeList;
    }


    @TxcTransaction(appName = "gzg")
    public void updateAndInsertGzgRunjin(JSONObject jsonObject){
        String xid = TxcContext.getCurrentXid();
        String planName = jsonObject.getString("plan_name");
        if(StringUtil.isNotEmpty(planName)){
            List<GzgProgramme> gzgProgrammeList = getGzgProgrammeByName(planName);
            if(gzgProgrammeList != null && gzgProgrammeList.size()>0){
                for (int i=0;i<gzgProgrammeList.size();i++){
                    JSONObject json = new JSONObject();
                    json.put("xid",xid);
                    json.put("id", SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_GZG_GOODS_PLAN));
                    json.put("imei", jsonObject.getString("tid"));
                    json.put("cabinet_no", gzgProgrammeList.get(i).getCabinet_no());
                    json.put("sku_id", gzgProgrammeList.get(i).getSku_id());
                    json.put("plan_name", gzgProgrammeList.get(i).getName());
                    json.put("state", "1");//'0:不可用，1：正常使用'
                    json.put("create_time", DateUtil.getNowDateStr());//'0:不可用，1：正常使用'
                    callDataCenter(GZG_GOODS_PLAN_INSERT, json);
                }
            }
        }

        jsonObject.put("xid",xid);
        callDataCenter(GZG_RUJIN_RELATION_INSERT_, jsonObject);//此处应该是插入，但前期还是更新为主
    }

}
