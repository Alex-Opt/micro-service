package com.ly.mt.cabinet.c.rujin.controller;

import com.ly.mt.cabinet.b.replenish.service.ReplenishmentGirdService;
import com.ly.mt.cabinet.c.alipay.service.GzgPayCommonService;
import com.ly.mt.cabinet.c.order.entity.GzgName;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.order.entity.OrderStateEnum;
import com.ly.mt.cabinet.c.rujin.entity.*;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



/**
 * 格子柜订单控制层
 */
@RestController
@RequestMapping("/cabinet/c/rujin/")
@Api(value = "格子柜订单控制层", tags = "rujin")
public class RujinNotifyController {
    @Autowired
    GzgRujinDeviceService gzgRujinDeviceService;
    @Autowired
    GzgPayCommonService gzgPayCommonService;

    @Autowired
    private ReplenishmentGirdService replenishmentGirdService;

    private static  final  String signkey= "68rFCT2QgqaovHPu";

    private static final Logger log = LoggerFactory.getLogger(RujinNotifyController.class);
    @ApiOperation(value = "如金柜子回调接口")
    @PostMapping("notify")
    public RujinNotifyVo GzgOrderGenerate(@ApiParam(name = "RujinNotify", value = "传入格子编号和柜子打开状态", required = true)
                                              @RequestBody RujinNotify rujinNotify) throws Exception {
        log.info("如金开始调用回调接口，controller层接收到的参数{}",rujinNotify);
        RujinNotifyVo rujinNotifyVo =   new RujinNotifyVo();
        //验签skey:68rFCT2QgqaovHPu
        //验签规则:MD5(tid+order+signkey) 省略验签步骤
       // String sign = MD5Util.md5(rujinNotify.getTid()+rujinNotify.getOrder()+signkey);
        String order = rujinNotify.getOrder();//订单编号
        String state = rujinNotify.getState();//终端开门状态： 1=成功， 2=故障
        //if(sign.equals(rujinNotify.getToken())){
            if(StringUtil.isEmpty(order)){
                rujinNotifyVo.setError("1");
                rujinNotifyVo.setEmsg("订单编号不能为空");
            }
            if(StringUtil.isEmpty(state)){
                rujinNotifyVo.setError("1");
                rujinNotifyVo.setEmsg("终端开门状态不能为空");
            }
            if("1".equals(state)){
                //根据订单号查询订单详情
                List<GzgOrderItem> gzgOrderItems = gzgRujinDeviceService.getGzgOrderItems(rujinNotify.getOrder());
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(order);
                gzgOrderVo.setOrder_status(OrderStateEnum.COMPLETED.getKey());
                gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                for (GzgOrderItem item:gzgOrderItems) {
                    log.info("reduce stock Cabinet_no: {} order:{}",item.getCabinet_no(),rujinNotify.getOrder());
                }
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,gzgOrderItems);
                log.info("扣减库存,增加销量完成");
                rujinNotifyVo.setError("0");
            }else {
                GzgOrder gzgOrderVo = new GzgOrder();
                gzgOrderVo.setId(order);
                gzgOrderVo.setOrder_status(OrderStateEnum.OPEN_FAIL.getKey());
                gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
                gzgPayCommonService.updateGzgOrder(gzgOrderVo,null);
                rujinNotifyVo.setError("0");
            }
            return rujinNotifyVo;
    //    }else{
      //      GzgOrder gzgOrderVo = new GzgOrder();
    //        gzgOrderVo.setId(order);
    //        gzgOrderVo.setOrder_status(OrderStateEnum.OPEN_FAIL.getKey());
    //        gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
    //        gzgPayCommonService.updateGzgOrder(gzgOrderVo);
     //       rujinNotifyVo.setError("0");
     //       return rujinNotifyVo;
     //   }
    }



    @ApiOperation(value = "如金柜子维护接口 参数: tid:柜子编号cabinet_no 多个货道号可以用“-” 区分，如： 1-3-5-6，表示 1 号， 3 号， 5 号，6 号 4 个货道同时开门 ")
    @PostMapping("maintain")
    ResponseJson maintain(@RequestParam(value = "tid",required = true)String tid,
                          @RequestParam(value = "cabinet_no",required = true)String cabinet_no, HttpServletRequest request){
        log.info("如金柜子维护接口，controller层接收到的参数tid:{},cabinet_no:{}",tid,cabinet_no);
        Boolean aBoolean = null;
        try {
            return (gzgRujinDeviceService.maintainDevice(tid, cabinet_no, RujinDoEnum.RUJIN_ONLINE))?
                    ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS)
                    : ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        } catch (Exception e) {
            log.info("如金柜子维护接口调用异常，tid:{},cabinet_no:{}",tid,cabinet_no);
           return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"打开柜子异常");
        }
    }


    @ApiOperation(value = "如金柜子查询货道状态 参数 tid:柜子编号 cabinet_no 多个货道号可以用")
    @PostMapping("/hdState")
    ResponseJson hdState(@RequestParam(value = "tid",required = true)String tid,
                          @RequestParam(value = "cabinet_no",required = true)String cabinet_no, HttpServletRequest request){
        log.info("如金柜子维护接口，controller层接收到的参数tid:{},cabinet_no:{}",tid,cabinet_no);
        Boolean aBoolean = null;
        try {
           return (gzgRujinDeviceService.hgState(tid,cabinet_no,RujinDoEnum.RUJIN_ONLINE))?ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS):
                   ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        } catch (Exception e) {
            log.info("如金柜子清除货道接口接口调用异常，tid:{},cabinet_no:{}",tid,cabinet_no);
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"打开柜子异常");
        }
    }

    @ApiOperation(value = "如金柜子实时查询柜子在线状态 ")
    @PostMapping("onlineState")
    RujinNotifyVo GzgOnlineState(@ApiParam(name = "RujinNotify", value = "传入柜子终端在线状态", required = true)
                                @RequestBody RujinOnlineState rujinOnlineState)throws Exception{
        log.info("如金在线状态回调接口，controller层接收到的参数 {}",rujinOnlineState);
        String tid = rujinOnlineState.getTid();
        String state = rujinOnlineState.getState();
        RujinNotifyVo rujinNotifyVo =   new RujinNotifyVo();
            if(StringUtil.isEmpty(tid)){
                rujinNotifyVo.setError("1");
                rujinNotifyVo.setEmsg("终端ID编号不能为空");
            }
            if(StringUtil.isEmpty(state)){
                rujinNotifyVo.setError("1");
                rujinNotifyVo.setEmsg("终端在线状态不能为空");
            }
            if("1".equals(state)){
                rujinNotifyVo.setError("0");
            }else {
                rujinNotifyVo.setError("0");
            }
            return rujinNotifyVo;
    }


    @ApiOperation(value = "如金柜子实时查询柜子订单开门状态 ")
    @PostMapping("openState")
    RujinNotifyVo GzgOpenState(@ApiParam(name = "RujinNotify", value = "传入格子编号和柜子打开状态", required = true)
                              @RequestBody OrderOpenState orderOpenState ,HttpServletRequest request) throws Exception{

        log.info("如金柜子订单开门状态,controller层接收到的参数{}",orderOpenState);
        RujinNotifyVo rujinNotifyVo =   new RujinNotifyVo();
        String order = orderOpenState.getOrder();
        String state = orderOpenState.getState();
        if(StringUtil.isEmpty(order)){
            rujinNotifyVo.setError("1");
            rujinNotifyVo.setEmsg("订单ID编号不能为空");
        }
        if(StringUtil.isEmpty(state)){
            rujinNotifyVo.setError("1");
            rujinNotifyVo.setEmsg("终端开门状态不能为空");
        }
        if("1".equals(state)){
            //根据订单号查询订单详情
            List<GzgOrderItem> gzgOrderItems = gzgRujinDeviceService.getGzgOrderItems(order);
            GzgOrder gzgOrderVo = new GzgOrder();
            gzgOrderVo.setId(order);
            gzgOrderVo.setOrder_status(OrderStateEnum.COMPLETED.getKey());
            gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
            gzgPayCommonService.updateGzgOrder(gzgOrderVo,gzgOrderItems);
            log.info("扣减库存,增加销量完成");
            try{
                replenishmentGirdService.createReplenishOrder(orderOpenState.getOrder());
            }catch (Exception e){
                log.info("生成补货订单异常 :{}",e);
            }
            rujinNotifyVo.setError("0");
        }else {
            GzgOrder gzgOrderVo = new GzgOrder();
            gzgOrderVo.setId(order);
            gzgOrderVo.setOrder_status(OrderStateEnum.OPEN_FAIL.getKey());
            gzgOrderVo.setOrder_finish_time(DateUtil.getNowTimeStr());
            gzgPayCommonService.updateGzgOrder(gzgOrderVo,null);
            rujinNotifyVo.setError("0");
        }
        return rujinNotifyVo;
    }



    @PostMapping("bindDevice")
    public ResponseJson bindDevice(@RequestParam("tid")String tid,@RequestParam("planName")String planName){
        try{
            return gzgRujinDeviceService.bindRunjinDevice(tid,planName);
        }catch (Exception e){
            return  ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @PostMapping("bindDeviceBatch")
    public void bindDevice(HttpServletRequest request) {
        //生成随机数
       /* int random = (int)((Math.random()*9+1)*10000);
        System.out.println(random);
        Set<String>  names = new HashSet<>();

        while(names.size() < 10000)
        {
            int gzgName = (int)((Math.random()*9+1)*100000);
            names.add("RJ"+gzgName);
        }
        Iterator<String > ite = names.iterator();
        for (;ite.hasNext();)
        {
            try{
                gzgRujinDeviceService.insertRuJinGzgName(ite.next());
                System.out.println(ite.next());
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/
       List<GzgName> namess = gzgRujinDeviceService.getGzgNameBatch("3004","12");
        for (GzgName name : namess) {
            try {
                System.out.println(name.getName());
                gzgRujinDeviceService.bindRunjinDevice(name.getName(), "");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println(namess.size());
    }
//    @ApiOperation(value = "如金柜子维护接口 参数: tname:格子柜名称， cabinet_no 多个货道号可以用“-” 区分，如： 1-3-5-6，表示 1 号， 3 号， 5 号，6 号 4 个货道同时开门 ")
//    @PostMapping("maintainByTname")
//    ResponseJson maintainByTname(@RequestParam(value = "tname",required = true)String tname,
//                          @RequestParam(value = "cabinet_no",required = true)String cabinet_no, HttpServletRequest request){
//        log.info("如金柜子维护接口 maintainByTname ，controller层接收到的参数tid:{},cabinet_no:{}",tname,cabinet_no);
//        Boolean aBoolean = null;
//        try {
//                return gzgRujinDeviceService.maintainDeviceByTname(tname, cabinet_no, RujinDoEnum.RUJIN_ONLINE);
//        } catch (Exception e) {
//            log.info("如金柜子维护接口 maintainByTname 调用异常，tname:{},cabinet_no:{}",tname,cabinet_no);
//            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"打开柜子异常");
//        }
//    }
}
