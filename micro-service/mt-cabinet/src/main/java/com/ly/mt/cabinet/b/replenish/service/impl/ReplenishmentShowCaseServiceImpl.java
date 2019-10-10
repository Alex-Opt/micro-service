package com.ly.mt.cabinet.b.replenish.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.entity.CabinetInfo;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.replenish.bo.CabinetBussinessCoorperationBo;
import com.ly.mt.cabinet.b.replenish.bo.CabinetZgReplenishOrderReasonBo;
import com.ly.mt.cabinet.b.replenish.bo.GzgGoodsPlanBo;
import com.ly.mt.cabinet.b.replenish.dto.CabinetZgReplenishOrderReasonDto;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentShowCaseService;
import com.ly.mt.cabinet.b.replenish.vo.*;
import com.ly.mt.cabinet.base.config.YmlConfig;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.good.entity.GzgGoodsPlan;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.core.base.dict.CabinetZgReplenishOrderReason;
import com.ly.mt.core.base.dict.CabinetZgReplenishOrderStatus;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.feign.DataCenterMethod;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_ZAN_GUI;
import static com.ly.mt.core.base.dict.CabinetMessageType.MESSAGE_TYPE_GZG;
import static com.ly.mt.core.base.dict.CabinetMessageType.MESSAGE_TYPE_ZG;
import static com.ly.mt.core.base.dict.CabinetReadStatus.READ_STATUS_NO;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_CABINET_MESSAGE;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_GZG_PLAN;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @description:展柜补货
 * @author: wanghongliang
 * @create: 2019-09-12 10:59
 **/
@Service
public class ReplenishmentShowCaseServiceImpl extends BaseServiceImpl implements ReplenishmentShowCaseService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ReplenishmentShowCaseServiceImpl.class);

    @Autowired
    private YmlConfig yml;

    /**
     * 该接口完成的操作有
     * 1.生成展柜的补货订单,以供展柜所在的店员，店主，或者BD进行补货操作。(目前这期只有BD补货)
     * 2.更新gzg_goods_plan表stock库存字段，若stock字段为0，前端页面这个地方会是灰色，用户无法购买
     * 3.当满足三个条件中一种 则生成对应的展柜补货订单
     * 1、TOP3商品售罄（运营给）
     * 2、套装售罄
     * 3、该展柜库存剩余小于3
     * 4.如果有押金，推送消息给给该柜所在门店店员发送补货push，否则push给BD
     *
     * @param gzgOrder
     * @return
     */
    @Override
    public ResponseJson createReplenishZgOrder(GzgOrder gzgOrder){
        //查询订单上所属格子柜库存 满足条件时 在查找格子柜所属的BD人员  并生成展柜补货订单
        //据订单id获取订单明细集合信息
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("order_id", gzgOrder.getId());
        String gzgOrderItemStr = callDataCenter(GZG_ORDER_ITEMS_GET_LIST, jsonParam);
        List<GzgOrderItem> gzgOrderItems = JSONObject.parseArray(gzgOrderItemStr, GzgOrderItem.class);//订单明细
        String[] top3SkuId =yml.getTop3SkuId().split(",");
        String resaon;//缺货理由
        boolean top3Resaon=false;//top3是否满足
        boolean suitResaon=false;//套装是否卖完了
        boolean less7Resaon=false;//总库存是否小于3
        int stock=0;
        int num=0;//需要补货数量
        //top3商品转换为map
        Map<String,String> map = new HashMap<>();
        for(String skuId:top3SkuId){
            map.put(skuId,"1");
        }
        //判断本次卖出的商品是否有top3商品/是否有套装售罄
        for(GzgOrderItem item:gzgOrderItems){
            //证明本次卖出商品包含top3商品 只有卖出top商品才去判断top3库存
            if(map.get(item.getSku_id())!=null&&map.get(item.getSku_id()).equals("1")){
                //查询库存是否还有to3商品
                JSONObject top3Param = new JSONObject();
                top3Param.put("imei",gzgOrder.getImei());
                top3Param.put("skuIdList",top3SkuId);
                String top3Result = callDataCenter(GZG_GOODS_PLAN_TOP3_GET, top3Param);//查询skuid
                List<GzgGoodsPlan> top3GzgGoodsPlanList = JSONObject.parseArray(top3Result, GzgGoodsPlan.class);
                //top3商品库中也确实没有了
                if(top3GzgGoodsPlanList!=null&&top3GzgGoodsPlanList.size()==0){
                    top3Resaon= true;//补货理由to3售罄理由成立 这里不跳出防止后期top3商品也为套装商品
                }
            }
            //是否为套装
            JSONObject skuParam = new JSONObject();
            skuParam.put("id", item.getSku_id());
            String skuResult = callDataCenter(DataCenterMethod.GOODS_SKU_INFO_GET_BY_ID, skuParam);
            //sku商品不为空时 空判断
            if(!StringUtil.isEmpty(skuResult)){
                GoodsSkuInfoVo goodsSkuInfoVo = JSONObject.parseObject(skuResult,GoodsSkuInfoVo.class);
                //查询sku所属spuid是否为套装
                JSONObject spuParma = new JSONObject();
                spuParma.put("id",goodsSkuInfoVo.getSpuId());
                String spuResult = callDataCenter(DataCenterMethod.GOODS_SPU_QUERY, spuParma);
                GoodsSpuInfoVo goodsSpuInfoVo = JSONObject.parseObject(spuResult,GoodsSpuInfoVo.class);
                //类目1 代表套装,这里暂且写死 且卖出的数量大于等于配置 补货套装数量
                if(goodsSpuInfoVo.getCid().equals("1")&&Integer.parseInt(item.getSku_num())>=yml.getZgSuitStockTotal()){
                    suitResaon=true;
                }
            }else{
                logger.info("订单明细中skuId查询不到商品信息,sku_id:"+item.getSku_id()+"|订单明细id为:"+item.getId());
            }
        }
        //根据格子柜id查询下面的库存数量
        JSONObject iemiParam = new JSONObject();
        iemiParam.put("imei",gzgOrder.getImei());
        String planResult = callDataCenter(GZG_GOODS_PLAN_GET, iemiParam);
        List<GzgGoodsPlanBo> gzgGoodsPlanBoList = JSONObject.parseObject(planResult,new TypeReference<List<GzgGoodsPlanBo>>(){});
        //目前数据库已有库存
        for(GzgGoodsPlanBo gzgGoodsPlanBo:gzgGoodsPlanBoList){
            logger.info("开始计算货柜编号："+gzgOrder.getImei()+"库存数量");
            stock=stock+Integer.parseInt(gzgGoodsPlanBo.getStock());
        }
        //判断总库存是否小于7,生成展柜的补货订单
        if(stock<yml.getZgReplenishStock()){
            less7Resaon=true;
        }
        num =yml.getZgStockTotal()-stock;//需要补货数量
        if(num<0){num=0;}
        //三种触发条件只要一种满足则插入/或更新展柜补货单
        createZgReplenishOrders(gzgOrder, top3Resaon, suitResaon, less7Resaon, num);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "生成展柜补货单成功！");
    }

    /**
     * 生成展柜补货订单
     *  三种触发条件只要一种满足则插入/或更新展柜补货单
     * @param gzgOrder
     * @param top3Resaon
     * @param suitResaon
     * @param less7Resaon
     * @param num
     */
    private void createZgReplenishOrders(GzgOrder gzgOrder, Boolean top3Resaon, Boolean suitResaon, Boolean less7Resaon, int num) {
        if(top3Resaon||suitResaon||less7Resaon){
            //通过店铺id 查询商务合作表 得到商务合作信息 用户BDUserId/BD手机号等
            JSONObject storeParam = new JSONObject();
            storeParam.put("storeId",gzgOrder.getHotel_id());//店铺id
            if (StringUtil.isEmpty(gzgOrder.getHotel_id())) {
                logger.info("此订单未查询到货柜所属店铺："+gzgOrder.getImei()+"|确认货柜是否上架");
            }
            String cabinetBussinessCoorperationResult = callDataCenter(CABINET_COORPERATION_GET_BY_STRORE, storeParam);//商务合作信息
            CabinetBussinessCoorperationBo cabinetBussinessCoorperationVo = JSONObject.parseObject(cabinetBussinessCoorperationResult, CabinetBussinessCoorperationBo.class);

            //下面开始生成补货订单
            JSONObject querReOrderParam = new JSONObject();
            querReOrderParam.put("user_id",cabinetBussinessCoorperationVo.getBdUserId());
            querReOrderParam.put("cabinet_code",gzgOrder.getImei());
            querReOrderParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
            querReOrderParam.put("cabinet_store_id",gzgOrder.getHotel_id());//店铺id
            String querOrderResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_GET, querReOrderParam);
            List<CabinetZgReplenishOrderVo> cabinetZgReplenishOrderVoList = JSONObject.parseObject(querOrderResult,new TypeReference<List<CabinetZgReplenishOrderVo>>(){});
            //为空时插入新的补货订单
            if(cabinetZgReplenishOrderVoList==null|| cabinetZgReplenishOrderVoList.size()==0){
                String id = RandomUtil.generateReplenishMentId();//生成展柜补货订单ID
                JSONObject zgReOrderParam = new JSONObject();
                zgReOrderParam.put("id",id);

                if(cabinetBussinessCoorperationVo==null||StringUtil.isEmpty(cabinetBussinessCoorperationVo.getBdUserId())){
                    logger.info("通过店铺id查询商务合作信息没查询到userId");
                    ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "未查询到店铺信息！");
                }
                zgReOrderParam.put("user_id",cabinetBussinessCoorperationVo.getBdUserId());//bd UserID
                zgReOrderParam.put("phone",cabinetBussinessCoorperationVo.getBdPhone());//bd手机号
                zgReOrderParam.put("cabinet_code",gzgOrder.getImei());
                zgReOrderParam.put("cabinet_store_id",gzgOrder.getHotel_id());
                zgReOrderParam.put("num",num);
                zgReOrderParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());//待补货
                zgReOrderParam.put("create_time",DateUtil.getNowTimeStr());
                zgReOrderParam.put("update_time",DateUtil.getNowTimeStr());
                String zgReResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_INSERT, zgReOrderParam);//插入补货订单
                //插入补货理由
                List<CabinetZgReplenishOrderReasonDto> list = new ArrayList<>();

                /**
                 *  1、TOP3商品售罄（运营给）
                 *  2、套装售罄
                 *  3、该展柜库存剩余小于3
                 */
                if(top3Resaon){//插入top3售罄理由
                    CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = getCabinetZgReplenishOrderReasonDto(gzgOrder, id);
                    cabinetZgReplenishOrderReasonDto.setReplenishment_reason(CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());//待补货;
                    list.add(cabinetZgReplenishOrderReasonDto);
                }
                if(suitResaon){//套装售罄
                    CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = getCabinetZgReplenishOrderReasonDto(gzgOrder, id);
                    cabinetZgReplenishOrderReasonDto.setReplenishment_reason(CabinetZgReplenishOrderReason.SUIT.getValue());
                    list.add(cabinetZgReplenishOrderReasonDto);
                }
                if(less7Resaon){//该展柜库存剩余小于3
                    CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = getCabinetZgReplenishOrderReasonDto(gzgOrder, id);
                    cabinetZgReplenishOrderReasonDto.setReplenishment_reason(CabinetZgReplenishOrderReason.STOCK_LIMIT.getValue());
                    list.add(cabinetZgReplenishOrderReasonDto);
                }
                JSONObject reasonParam = new JSONObject();
                reasonParam.put("addList",list);
                callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_INSERT, reasonParam);//批量插入补货理由
                //放入展柜用户消息
                saveCabientShowCaseMessage(cabinetBussinessCoorperationVo.getBdUserId());
            }else{//否则更新补货订单和补货理由
                CabinetZgReplenishOrderVo cabinetZgReplenishOrderVo = cabinetZgReplenishOrderVoList.get(0);
                JSONObject updateOrderParam = new JSONObject();
                updateOrderParam.put("id",cabinetZgReplenishOrderVo.getId());
                updateOrderParam.put("num",num);
                updateOrderParam.put("update_time", DateUtil.getNowTimeStr());
                String zgReResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_UPDATE, updateOrderParam);//更新补货订单数量和补货理由
                //查询数据库存在的理由
                //如果有对应的补货订单理由 则需要更新补货理由、补货数量、补货
                JSONObject querOrderReasonParam = new JSONObject();
                querOrderReasonParam.put("zg_replenish_order_id",cabinetZgReplenishOrderVo.getId());
                querOrderReasonParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
                String reasonResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_GET, querOrderReasonParam);
                List<CabinetZgReplenishOrderReasonBo> cabinetZgReplenishOrderVoReasonList = JSONObject.parseObject(reasonResult,new TypeReference<List<CabinetZgReplenishOrderReasonBo>>(){});
                //数据库已存在理由
                boolean top3ResaonDb=false;
                boolean suitResaonDb=false;
                boolean less7ResaonDb=false;

                //判断数据库已存在的理由
                for(CabinetZgReplenishOrderReasonBo cabinetZgReplenishOrderReasonBo:cabinetZgReplenishOrderVoReasonList){
                    /**
                     *  1、TOP3商品售罄（运营给）
                     *  2、套装售罄
                     *  3、该展柜库存剩余小于3
                     */
                    if(cabinetZgReplenishOrderReasonBo.getReplenishmentReason().equals(CabinetZgReplenishOrderReason.TOP3.getValue())){
                        top3ResaonDb=true;
                    }else if(cabinetZgReplenishOrderReasonBo.getReplenishmentReason().equals(CabinetZgReplenishOrderReason.SUIT.getValue())){
                        suitResaonDb=true;
                    }else if(cabinetZgReplenishOrderReasonBo.getReplenishmentReason().equals(CabinetZgReplenishOrderReason.STOCK_LIMIT.getValue())){
                        less7ResaonDb=true;
                    }
                }
                //插入补货理由
                List<CabinetZgReplenishOrderReasonDto> list = new ArrayList<>();
                if(top3Resaon&&!top3ResaonDb){//本次top3卖完 且数据库不存在 则插入
                    CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = getCabinetZgReplenishOrderReasonDto(gzgOrder, cabinetZgReplenishOrderVo.getId());
                    cabinetZgReplenishOrderReasonDto.setReplenishment_reason(CabinetZgReplenishOrderReason.TOP3.getValue());
                    list.add(cabinetZgReplenishOrderReasonDto);
                }
                if(suitResaon&&!suitResaonDb){//套装售罄 且数据库不存在 则插入
                    CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = getCabinetZgReplenishOrderReasonDto(gzgOrder, cabinetZgReplenishOrderVo.getId());
                    cabinetZgReplenishOrderReasonDto.setReplenishment_reason(CabinetZgReplenishOrderReason.SUIT.getValue());
                    list.add(cabinetZgReplenishOrderReasonDto);
                }
                if(less7Resaon&&!less7ResaonDb){//该展柜库存剩余小于3 且数据库不存在 则插入
                    CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = getCabinetZgReplenishOrderReasonDto(gzgOrder, cabinetZgReplenishOrderVo.getId());
                    cabinetZgReplenishOrderReasonDto.setReplenishment_reason(CabinetZgReplenishOrderReason.STOCK_LIMIT.getValue());
                    list.add(cabinetZgReplenishOrderReasonDto);
                }
                if(list!=null&&list.size()>0){
                    JSONObject reasonParam = new JSONObject();
                    reasonParam.put("addList",list);
                    callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_INSERT, reasonParam);//批量插入补货理由
                }
            }
        }else{
            logger.info("货柜编号为=="+gzgOrder.getImei()+"/不满足三个展柜触发条件,此处不生成展柜补货订单");
        }
    }

    /**
     * 放入展柜用户消息
     * @param userId
     */
    public void saveCabientShowCaseMessage(String userId){
        String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_CABINET_MESSAGE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message_id",id);//消息id
        jsonObject.put("user_id",userId);
        jsonObject.put("message_type",MESSAGE_TYPE_ZG.getId());//展柜消息类型
        jsonObject.put("read_status",READ_STATUS_NO.getId());//是否已读
        jsonObject.put("message_title","展柜补货");//暂且写死
        jsonObject.put("message_content","展柜补货");//暂且写死
        jsonObject.put("create_time",DateUtil.getNowTimeStr());//取当前系统时间
        jsonObject.put("modify_time",DateUtil.getNowTimeStr());//取当前系统时间
        String result = callDataCenter(GZG_CABINET_MESSAGE_INSERT, jsonObject);//商务合作信息
    }

    /**
     * 封装补货理由
     * @param gzgOrder
     * @param id
     * @return
     */
    private CabinetZgReplenishOrderReasonDto getCabinetZgReplenishOrderReasonDto(GzgOrder gzgOrder, String id) {
        CabinetZgReplenishOrderReasonDto cabinetZgReplenishOrderReasonDto = new CabinetZgReplenishOrderReasonDto();
        cabinetZgReplenishOrderReasonDto.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_GZG_REPLENISH_ORDER));
        cabinetZgReplenishOrderReasonDto.setZg_replenish_order_id(id);
        cabinetZgReplenishOrderReasonDto.setCabinet_code(gzgOrder.getImei());
        cabinetZgReplenishOrderReasonDto.setStatus(CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
        cabinetZgReplenishOrderReasonDto.setCreate_time(DateUtil.getNowTimeStr());
        cabinetZgReplenishOrderReasonDto.setUpdate_time(DateUtil.getNowTimeStr());
        return cabinetZgReplenishOrderReasonDto;
    }


    /**
     * 获取Spu列表
     * @return
     */
    @Override
    public ResponseJson getSpuInfoList(){
        JSONObject jsonParam = new JSONObject();
        String reulst = callDataCenter(GOODS_SPU_QUERY_ALL, jsonParam);
        List<GoodsSpuInfoVo> goodsSpuInfoList  =JSONObject.parseArray(reulst,GoodsSpuInfoVo.class);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsSpuInfoList);
    }

    /**
     * 获取sku列表
     * @return
     */
    @Override
    public ResponseJson getSkuInfoList(String spuId,String cabinetCode){
        //查询spu下sku列表
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("spuId",spuId);
        String result = callDataCenter(GOODS_SKU_BY_CONDTION, jsonParam);
        List<GoodsSkuInfoVo> goodsSkuInfoList  =JSONObject.parseObject(result,new TypeReference<List<GoodsSkuInfoVo>>(){});
        //根据格子柜id查询下面的库存数量
        JSONObject stockParam = new JSONObject();
        stockParam.put("imei",cabinetCode);
        String planResult = callDataCenter(GZG_GOODS_PLAN_GET, stockParam);
        List<GzgGoodsPlanBo> gzgGoodsPlanBoList = JSONObject.parseObject(planResult,new TypeReference<List<GzgGoodsPlanBo>>(){});
        Map<String,Object> planMap = new HashMap<>();
        //此格子柜是有库存的
        if(gzgGoodsPlanBoList!=null&&gzgGoodsPlanBoList.size()>0){
            for(GzgGoodsPlanBo gzgGoodsPlanBo:gzgGoodsPlanBoList){
                planMap.put(gzgGoodsPlanBo.getSkuId(),Integer.parseInt(gzgGoodsPlanBo.getStock()));//放入skuid和库存
            }
            //两个循环比对 通过skuId比对 是否存在库存 没有则库存数量为0
            for(GoodsSkuInfoVo goodsSkuInfoVo:goodsSkuInfoList){
                String r = String.valueOf(planMap.get(goodsSkuInfoVo.getId()));
                int stock =0;
                if(!r.equals("null")&&r!=null){
                    stock =Integer.parseInt(r);//得出此格子柜库存
                }
                goodsSkuInfoVo.setStock(stock);//放入库存
            }
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsSkuInfoList);
    }


    /**
     * 添加商品
     * 判断逻辑
     * 1:补货失败，套装数量大于1件 配置数量
     * 2:补货失败，补货数量大于25件  配置数量
     * 3：补货失败，该展柜库存已有25件 配置数量
     * 4：补货失败，该展柜库存中已存在1件套装 配置数量
     * 5：补货失败，已大于库存最大数量25件 配置数量
     * 否则：
     *   判断商品在gzg_goods_plan 是否存在数据 有则在原来stock+本次添加数量
     *   否则gzg_goods_plan 插入一条新的数据
     *
     * 还需要更新展柜的补货订单 满足时更新补货理由/补货数量/补货状态
     * @return
     */
    @Override
    @TxcTransaction(appName = "cabinet")
    public ResponseJson addGoodsStock(GoodsAddZgVo goodsAddZgVo){
        TokenInfo tokenInfo = getTokenInfo();
        String cabinetCode = goodsAddZgVo.getCabinetCode();
        List<GoodsAddVo> goodsAddVos =goodsAddZgVo.getGoodsAddVoList();
        int stockTotal=0;//商品已有库存
        int addTotal=0;//本次添加库存
        int total=0;//商品已有库存+本次添加库存
        int suitNum=0;//本次添加套装数量
        int suitStock=0;//库存已存在套装数量
        Boolean isTop3=false;
        //to3商品
        String[] top3SkuId =yml.getTop3SkuId().split(",");
        //top3商品转换为map
        Map<String,String> map = new HashMap<>();
        for(String skuId:top3SkuId){
            map.put(skuId,"1");
        }
        Map<String,String> suitGoodsMap = new HashMap<>();
        //下面开始判断本次添加套装数量
        //本次添加商品
        for(GoodsAddVo goodsAddVo:goodsAddVos){
            addTotal=addTotal+goodsAddVo.getStock();
            //本次补货包含top3商品 下面更新补货订单时用到
            if(map.get(goodsAddVo.getSkuId())!=null&&map.get(goodsAddVo.getSkuId()).equals("1")){
                //当有一个为top3时 下面查询库存是不是都补上了
                isTop3=true;
            }
            //cid为1认为是套装
            if(goodsAddVo.getCid().equals("1")){
                suitNum=suitNum+goodsAddVo.getStock();
                suitGoodsMap.put(goodsAddVo.getSpuId(),"1");
            }
            //证明此商品是套装 并且套装数量大于配置直接抛出异常并停止
            if(suitNum>yml.getZgSuitStockTotal()){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"补货失败，套装数量大于"+yml.getZgSuitStockTotal());
            }
        }
        if(addTotal>yml.getZgStockTotal()){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"补货失败，补货数量大于"+yml.getZgStockTotal());
        }
        //当本次添加商品中包含套装,则查询数据库中是否存在套装
        if(suitNum>0){
            //查询数据库套装数量
            JSONObject suitParam = new JSONObject();
            suitParam.put("imei",cabinetCode);
            String suitResult = callDataCenter(GZG_GOODS_PLAN_SUIT_GET, suitParam);
            suitStock = Integer.parseInt(suitResult);
            if(suitStock+suitNum>yml.getZgSuitStockTotal()){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"补货失败，套装库存数量+套装补货数量大于"+yml.getZgSuitStockTotal());
            }
        }
        //根据格子柜id查询下面的库存数量
        JSONObject iemiParam = new JSONObject();
        iemiParam.put("imei",cabinetCode);
        String planResult = callDataCenter(GZG_GOODS_PLAN_GET, iemiParam);
        List<GzgGoodsPlanBo> gzgGoodsPlanBoList = JSONObject.parseObject(planResult,new TypeReference<List<GzgGoodsPlanBo>>(){});
        //目前已有库存
        for(GzgGoodsPlanBo gzgGoodsPlanBo:gzgGoodsPlanBoList){
            stockTotal=stockTotal+Integer.parseInt(gzgGoodsPlanBo.getStock());
        }
        //商品已有库存大于配置库存
        if(stockTotal>=yml.getZgStockTotal()){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"补货失败，该展柜库存已有"+yml.getZgStockTotal()+"个,无需补货");
        }
        total=stockTotal+addTotal;
        if(total>yml.getZgStockTotal()){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"补货失败，补货数量+库存数量已大于"+yml.getZgStockTotal()+"个");
        }
        //查询此展柜下此货物是否存在,如果存在则更新gzg_goods_plan,否则插入gzg_goods_plan
        createZgReplenishOrders(cabinetCode, goodsAddVos);
        //如果存在补货订单 则更新展柜补货订单数量/补货理由/补货状态 没测先注释掉
        updateZgReplenishOrder(cabinetCode, addTotal, total, suitNum, isTop3,top3SkuId);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS,"展柜补货成功！");
    }

    /**
     * 更新补货订单
     * @param cabinetCode
     * @param addTotal
     * @param total
     * @param suitNum
     * @param isTop3
     */
    private void updateZgReplenishOrder(String cabinetCode,int addTotal, int total, int suitNum, Boolean isTop3,String[] top3SkuId) {
        TokenInfo tokenInfo = getTokenInfo();
        //先查询是否有需要补货的订单
        JSONObject querReOrderParam = new JSONObject();
        querReOrderParam.put("user_id",tokenInfo.getUserId());
        querReOrderParam.put("cabinet_code",cabinetCode);
        querReOrderParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
        String querOrderResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_GET, querReOrderParam);
        List<CabinetZgReplenishOrderVo> cabinetZgReplenishOrderVoList = JSONObject.parseObject(querOrderResult,new TypeReference<List<CabinetZgReplenishOrderVo>>(){});
        if(cabinetZgReplenishOrderVoList==null|| cabinetZgReplenishOrderVoList.size()==0){
            logger.info("此"+cabinetCode+">展柜没有补货订单,无需更新");
        }else{
            CabinetZgReplenishOrderVo cabinetZgReplenishOrderVo = cabinetZgReplenishOrderVoList.get(0);
            //如果有对应的补货订单理由 则需要更新补货理由、补货数量、补货
            JSONObject querOrderReasonParam = new JSONObject();
            querOrderReasonParam.put("zg_replenish_order_id",cabinetZgReplenishOrderVo.getId());
            //querOrderReasonParam.put("cabinet_code",cabinetCode);
            querOrderReasonParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
            String reasonResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_GET, querOrderReasonParam);
            List<CabinetZgReplenishOrderReasonBo> cabinetZgReplenishOrderVoReasonList = JSONObject.parseObject(reasonResult,new TypeReference<List<CabinetZgReplenishOrderReasonBo>>(){});
            //为空时证明没有满足缺货的补货订单
            if(cabinetZgReplenishOrderVoReasonList==null|| cabinetZgReplenishOrderVoReasonList.size()==0){
                logger.info("此"+cabinetCode+">展柜没有补货订单理由,无需更新");
            }else{//证明此展柜有缺货订单 在补货完成后更新补货理由/补货数量/补货单状态
                for(CabinetZgReplenishOrderReasonBo cabinetZgReplenishOrderReasonBo:cabinetZgReplenishOrderVoReasonList){
                    JSONObject updateReasonParam = new JSONObject();
                    updateReasonParam.put("id",cabinetZgReplenishOrderReasonBo.getId());
                    updateReasonParam.put("status",CabinetZgReplenishOrderStatus.COMPLETED.getValue());
                    updateReasonParam.put("update_time", DateUtil.getNowTimeStr());
                    String reason =cabinetZgReplenishOrderReasonBo.getReplenishmentReason();//缺货理由
                    String updateReason;//更新后理由
                    int updateNum=0;
                    //0:TOP3商品售罄（运营给）1:套装售罄 2:该展柜库存剩余小于3
                    if(reason.equals(CabinetZgReplenishOrderReason.TOP3.getValue())&&isTop3){
                        //查询库存是否还有to3商品
                        JSONObject top3Param = new JSONObject();
                        top3Param.put("imei",cabinetZgReplenishOrderVo.getCabinetCode());
                        top3Param.put("skuIdList",top3SkuId);
                        String top3Result = callDataCenter(GZG_GOODS_PLAN_TOP3_GET, top3Param);//查询skuid
                        List<GzgGoodsPlan> top3GzgGoodsPlanList = JSONObject.parseArray(top3Result, GzgGoodsPlan.class);
                        //数据库中包含三条top商品 且有库存则更新top3商品补货理由为已完成
                        if(top3GzgGoodsPlanList!=null&&top3GzgGoodsPlanList.size()==3){
                            boolean top3Stock = false;
                            int top3Num =0;
                            for(GzgGoodsPlan goodsPlan:top3GzgGoodsPlanList){
                                if(Integer.parseInt(goodsPlan.getStock())>0){
                                    top3Num=top3Num+1;
                                }
                            }
                            if(top3Num>=3){
                                //更新补货理由状态为已完成 去除TOP3商品售罄
                                logger.info("开始更新TOP3商品售罄为已完成"+cabinetCode);
                                callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_UPDATE, updateReasonParam);
                                logger.info("结束更新TOP3商品售罄为已完成"+cabinetCode);
                            }
                        }
                    }else if(reason.equals(CabinetZgReplenishOrderReason.SUIT.getValue())&&suitNum>0){
                        //更新补货理由状态为已完成 去除套装卖完的理由
                        logger.info("开始更新套装售罄为已完成"+cabinetCode);
                        callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_UPDATE, updateReasonParam);
                        logger.info("结束更新套装售罄为已完成"+cabinetCode);
                    }else if(reason.equals(CabinetZgReplenishOrderReason.STOCK_LIMIT.getValue())&&total>=yml.getZgReplenishStock()){
                        //更新补货理由状态为已完成 去除数量小于3
                        logger.info("开始更新数量小于3时为已完成"+cabinetCode);
                        callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_UPDATE, updateReasonParam);
                        logger.info("结束更新数量小于3时为已完成"+cabinetCode);
                    }
                }
                //补货完成以后再次查询 是否补货完成 完成需要更新补货订单状态和数量
                reasonResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_GET, querOrderReasonParam);
                List<CabinetZgReplenishOrderReasonBo> newList = JSONObject.parseObject(reasonResult,new TypeReference<List<CabinetZgReplenishOrderReasonBo>>(){});
                if(newList==null|| newList.size()==0){
                    logger.info("此"+cabinetCode+">展柜都已补货完成,开始更新补货订单状态为已完成");
                    JSONObject updateOrderParam = new JSONObject();
                    updateOrderParam.put("id",cabinetZgReplenishOrderVoList.get(0).getId());
                    updateOrderParam.put("status",CabinetZgReplenishOrderStatus.COMPLETED.getValue());
                    updateOrderParam.put("update_time",DateUtil.getNowTimeStr());
                    callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_UPDATE, updateOrderParam);
                    logger.info("此"+cabinetCode+">展柜都已补货完成,结束更新补货订单状态为已完成");
                }else{//证明还有货没补,则只更新数量
                    logger.info("此"+cabinetCode+">展柜,开始更新待补货数量");
                    JSONObject updateOrderParam = new JSONObject();
                    updateOrderParam.put("id",cabinetZgReplenishOrderVoList.get(0).getId());
                    int replenishNum =Integer.parseInt(cabinetZgReplenishOrderVoList.get(0).getNum());//数据库待补数量
                    int updateNum =replenishNum-addTotal;
                    updateOrderParam.put("num",updateNum);
                    updateOrderParam.put("update_time",DateUtil.getNowTimeStr());
                    callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_UPDATE, updateOrderParam);
                    logger.info("此"+cabinetCode+">展柜都已补货完成,结束更新待补货数量");
                }
            }
        }
    }

    /**
     * 更新gzg_goods_plan库存数量或插入gzg_goods_plan新的skuId商品
     * @param cabinetCode
     * @param goodsAddVos
     */
    private void createZgReplenishOrders(String cabinetCode, List<GoodsAddVo> goodsAddVos) {
        List<GzgGoodsPlan> addList = new ArrayList<>();
        String xid = TxcContext.getCurrentXid();
        //根据格子柜id查询下面的库存数量
        for(GoodsAddVo goodsAddVo:goodsAddVos){
            JSONObject existParam = new JSONObject();
            existParam.put("imei",cabinetCode);
            existParam.put("sku_id",goodsAddVo.getSkuId());
            String existResult = callDataCenter(GZG_GOODS_PLAN_GET, existParam);
            List<GzgGoodsPlanBo> existGoodsPlanBoList = JSONObject.parseObject(existResult,new TypeReference<List<GzgGoodsPlanBo>>(){});
            if(existGoodsPlanBoList!=null&&existGoodsPlanBoList.size()>0){//存在则更新
                //开始更新库存数量
                JSONObject stockParam = new JSONObject();
                stockParam.put("xid", xid);
                stockParam.put("imei", cabinetCode);
                stockParam.put("sku_id", goodsAddVo.getSkuId());
                stockParam.put("stock", goodsAddVo.getStock());
                String updateReulst = callDataCenter(GZG_GOODS_PLAN_UPDATE_STOCK, stockParam);
            }else{
                //封装goodsPlan
                GzgGoodsPlan goodsPlan = new GzgGoodsPlan();
                //主键
                String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_GZG_PLAN);
                goodsPlan.setId(id);
                goodsPlan.setImei(cabinetCode);
                goodsPlan.setPlan_name("ZG_A");
                goodsPlan.setCabinet_no("1");//全都放入暂时1号货道 由于货道编号不能为空
                goodsPlan.setSell_no("0");
                goodsPlan.setSku_id(goodsAddVo.getSkuId());
                goodsPlan.setStock(String.valueOf(goodsAddVo.getStock()));
                goodsPlan.setState("1");
                goodsPlan.setCreate_time(DateUtil.getNowTimeStr());
                goodsPlan.setCabinet_type(CABINET_TYPE_ZAN_GUI.getKey());
                addList.add(goodsPlan);
            }
        }
        if(addList!=null&&addList.size()>0){
            //此展柜之前不存在此商品
            JSONObject planParam = new JSONObject();
            planParam.put("xid",xid);
            planParam.put("goodsPlanList",addList);
            callDataCenter(GZG_GOODS_PLAN_INSERT_BATCHS, planParam);
        }
    }

    /**
     * 获取展柜待配货列表
     * @return
     */
    @Override
    public ResponseJson getReplenishZgOrderList(){
        TokenInfo tokenInfo = getTokenInfo();
        //查询展柜补货订单
        JSONObject querReOrderParam = new JSONObject();
        querReOrderParam.put("user_id",tokenInfo.getUserId());
        querReOrderParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
        String querOrderResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_GET, querReOrderParam);
        List<CabinetZgReplenishOrderVo> cabinetZgReplenishOrderVoList = JSONObject.parseObject(querOrderResult,new TypeReference<List<CabinetZgReplenishOrderVo>>(){});
        //此处与数据库交互过多,后期可改表结构冗余或修改sql修改此方案
        for(CabinetZgReplenishOrderVo cabinetZgReplenishOrderVo:cabinetZgReplenishOrderVoList){
            //查询缺货理由
            //如果有对应的补货订单理由 则需要更新补货理由、补货数量、补货
            JSONObject querOrderReasonParam = new JSONObject();
            querOrderReasonParam.put("zg_replenish_order_id",cabinetZgReplenishOrderVo.getId());
            querOrderReasonParam.put("status", CabinetZgReplenishOrderStatus.GENERATION_OF_REPLENISHMENT.getValue());
            String reasonResult = callDataCenter(GZG_CABINET_ZGREPLENISH_ORDER_REASON_GET, querOrderReasonParam);
            List<CabinetZgReplenishOrderReasonBo> cabinetZgReplenishOrderVoReasonList = JSONObject.parseObject(reasonResult,new TypeReference<List<CabinetZgReplenishOrderReasonBo>>(){});
            List<String> list = new ArrayList<>();
            for(CabinetZgReplenishOrderReasonBo cabinetZgReplenishOrderReasonBo:cabinetZgReplenishOrderVoReasonList) {
                list.add(cabinetZgReplenishOrderReasonBo.getReplenishmentReason());
            }
            cabinetZgReplenishOrderVo.setReplenishmentReason(list);
            //查询格子柜信息
            JSONObject cabinetParam = new JSONObject();
            cabinetParam.put("imei",cabinetZgReplenishOrderVo.getCabinetCode());
            String cabinetResult = callDataCenter(CABINET_INFO_GET_BY_IMEI, cabinetParam);//查询格子柜信息
            if(cabinetResult==null||StringUtil.isEmpty(cabinetResult)){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"未查询此货柜信息");
            }
            CabinetInfo cabinetInfo = JSONObject.parseObject(cabinetResult,CabinetInfo.class);//格子柜信息
            cabinetZgReplenishOrderVo.setOnlineTime(cabinetInfo.getGmt_modify());//上架时间
            //查询合作信息
            //通过店铺id 查询商务合作表 得到商务合作信息 用户userID/BD手机号等
            JSONObject storeParam = new JSONObject();
            storeParam.put("storeId",cabinetZgReplenishOrderVo.getCabinetStoreId());//店铺id
            String cabinetBussinessCoorperationResult = callDataCenter(CABINET_COORPERATION_GET_BY_STRORE, storeParam);//商务合作信息
            if(cabinetBussinessCoorperationResult==null||StringUtil.isEmpty(cabinetBussinessCoorperationResult)){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"未查询此货柜商务信息");
            }
            CabinetBussinessCoorperationBo cabinetBussinessCoorperationVo = JSONObject.parseObject(cabinetBussinessCoorperationResult, CabinetBussinessCoorperationBo.class);
            cabinetZgReplenishOrderVo.setOwnerName(cabinetBussinessCoorperationVo.getOwnerName());//店主姓名
            cabinetZgReplenishOrderVo.setOwnerPhone(cabinetBussinessCoorperationVo.getOwnerPhone());//店主手机号
            //通过店铺id 查询店铺名称
            storeParam.remove("storeId");//店铺id
            storeParam.put("id",cabinetZgReplenishOrderVo.getCabinetStoreId());//店铺id
            String cabinetStoreResult = callDataCenter(CABINET_STORE_GET_BY_ID, storeParam);//查询店铺信息
            CabinetStoreVo cabinetStoreVo = JSONObject.parseObject(cabinetStoreResult,CabinetStoreVo.class);
            cabinetZgReplenishOrderVo.setShopName(cabinetStoreVo.getName());//店铺名称
            cabinetZgReplenishOrderVo.setAddress(cabinetStoreVo.getAddress());//店铺地址
            cabinetZgReplenishOrderVo.setDetailAddress(cabinetStoreVo.getDetailAddress());//店铺详细地址
            cabinetZgReplenishOrderVo.setStockTotal(String.valueOf(yml.getZgStockTotal()));//总库存
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,cabinetZgReplenishOrderVoList);
    }
}