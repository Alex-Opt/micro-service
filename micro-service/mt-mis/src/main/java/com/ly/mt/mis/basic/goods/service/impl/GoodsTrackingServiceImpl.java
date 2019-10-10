package com.ly.mt.mis.basic.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.goods.entity.GoodsSkuCode;
import com.ly.mt.core.data.record.entity.RecordImport;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.base.util.ExcelUtil;
import com.ly.mt.mis.basic.goods.service.GoodsTrackingService;
import com.ly.mt.mis.basic.goods.util.GoodsExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.ImportType.IMPORT_TYPE_GOODS_SKU_CODE;
import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_NO;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.REDIS_MIS_LIST_TRACKING_CODE_UPLOAD_TEMP_KEY;

/**
 * 追踪码管理
 *
 * @author taoye
 */
@Service
public class GoodsTrackingServiceImpl extends BaseServiceImpl implements GoodsTrackingService {
    @Override
    public ResponseJson loadRecordDatagrid(JSONObject jsonObject) {
        RecordImport record = JSONObject.toJavaObject(jsonObject, RecordImport.class);
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        record.setImportTime(getBetweenSql(jsonObject.getString("importTimeStart"), jsonObject.getString("importTimeEnd")));
        record.setImportType(IMPORT_TYPE_GOODS_SKU_CODE.getId());
        record.setValidStatus(VALID_STATUS_YES.getId());
        Datagrid datagrid = recordImportDao.loadDatagridFromMysql(record, page);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public ResponseJson deleteRecordImport(JSONObject jsonObject) throws Exception {
        RecordImport record = JSONObject.toJavaObject(jsonObject, RecordImport.class);
        record.setValidStatus(VALID_STATUS_NO.getId());
        record.setDeleteId(getLoginUserId());
        record.setDeleteTime(DateUtil.getNowTimeStr());
        recordImportDao.updateRecordImport(record);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson trackingCodeUpload(MultipartFile file) throws Exception {
        // 读取文件内容
        List<String[]> list = ExcelUtil.getExcelData(file);
        int size = list.size();
        if (size <= 1) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "上传文件内容为空,请正确填写内容后重新上传!");
        }
        // 校验标题行
        StringBuilder sb = GoodsExcelUtil.trackingCodeTitleCheck(list.get(0));
        // 校验内容行
        for (int i = 1; i < size; i++) {
            String[] row = list.get(i);
            String sku = row[0];
            if (!StringUtil.isNumber(sku)) {
                sb.append("第").append(i + 1).append("行：SKU格式不正确").append(";");
            }
        }
        // 有错误信息提示，无错误信息校验通过
        if (sb.length() > 0) {
            ResponseJson rj = ResponseUtil.getResponseObj(RESPONSE_CODE_ERROR, sb.deleteCharAt(sb.length() - 1).toString());
            return rj;
        }
        // 将导入数据放入redis
        List<GoodsSkuCode> goodsSkuCodes = new ArrayList<>();
        list.remove(0);
        list.forEach(
                row -> {
                    GoodsSkuCode goodsSkuCode = new GoodsSkuCode();
                    goodsSkuCode.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_GOODS_SKU_CODE));
                    goodsSkuCode.setSkuId(row[0]);
                    goodsSkuCode.setBarCode(row[1]);
                    goodsSkuCode.setParentCode(row[2]);
                    goodsSkuCode.setCode(row[3]);
                    goodsSkuCode.setCheckNum("0");
                    String time = DateUtil.getNowTimeStr();
                    goodsSkuCode.setCreateTime(time);
                    goodsSkuCode.setModifyTime(time);
                    goodsSkuCodes.add(goodsSkuCode);
                }
        );
        String tempKey = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM);
        redisService.setEntity(REDIS_MIS_LIST_TRACKING_CODE_UPLOAD_TEMP_KEY, tempKey, goodsSkuCodes, 2, TimeUnit.MINUTES);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, tempKey);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseJson trackingCodeImport(JSONObject jsonObject) throws Exception {
        String tempKey = jsonObject.getString("tempKey");
        if (StringUtil.isEmpty(tempKey)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误");
        }
        String json = redisService.get(REDIS_MIS_LIST_TRACKING_CODE_UPLOAD_TEMP_KEY, tempKey);
        if (StringUtil.isEmpty(json)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "临时文件已过时,请重新上传");
        }
        List<GoodsSkuCode> goodsSkuCodes = JSONObject.parseObject(json, new TypeReference<List<GoodsSkuCode>>() {
        });
        if (null == goodsSkuCodes || goodsSkuCodes.size() <= 0) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "临时文件内容错误,请重新上传");
        }
        goodsSkuCodeDao.insertGoodsSkuCodes(goodsSkuCodes);
        RecordImport record = new RecordImport();
        record.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_GOODS_SKU_CODE_IMPORT_RECORD));
        record.setRowCount(String.valueOf(goodsSkuCodes.size()));
        record.setImportId(getLoginUserId());
        record.setImportName(getLoginUserName());
        record.setImportTime(DateUtil.getNowTimeStr());
        record.setImportType(IMPORT_TYPE_GOODS_SKU_CODE.getId());
        record.setValidStatus(VALID_STATUS_YES.getId());
        recordImportDao.insertRecordImport(record);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }
}