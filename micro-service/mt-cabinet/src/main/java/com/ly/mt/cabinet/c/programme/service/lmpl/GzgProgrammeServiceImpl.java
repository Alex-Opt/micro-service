package com.ly.mt.cabinet.c.programme.service.lmpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.programme.entity.GzgProgramme;
import com.ly.mt.cabinet.c.programme.service.GzgProgrammeService;
import com.ly.mt.cabinet.c.rujin.entity.RujinUrlEnum;
import com.ly.mt.cabinet.c.tools.HttpClientUtil;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.feign.DataCenterMethod.GZG_RUJIN_RELATION_GET_BY_NAME;

@Service
@PropertySource(value = {"classpath:myconfig-c.properties"},ignoreResourceNotFound=false,encoding="UTF-8")
public class GzgProgrammeServiceImpl extends BaseServiceImpl implements GzgProgrammeService {
    private final static Logger log = LoggerFactory.getLogger(GzgProgrammeServiceImpl.class);
    @Value("${rujin.skey}")
    private String skey;


    @Override
    public void bindRunjinDevice(String tid) throws Exception {
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
            return;
        }
        JSONObject data = jsonObject.getJSONObject("data");
        String id = data.getString("id");
        String tname = data.getString("tname");
        JSONArray hglist = data.getJSONArray("hglist");
        String hgsn = hglist.getJSONObject(0).getString("hgsn");
        String hdtol = hglist.getJSONObject(0).getString("hdtol");
        JSONObject jsonGzgRujinRelation = new JSONObject();
        jsonGzgRujinRelation.put("name",tid);
        jsonGzgRujinRelation.put("tid",id);
        jsonGzgRujinRelation.put("tname",tname);
        jsonGzgRujinRelation.put("hg",hgsn);
        jsonGzgRujinRelation.put("hd",hdtol);
        jsonGzgRujinRelation.put("create_time", DateUtil.getNowDateStr());
        updateAndInsertGzgRunjin(jsonGzgRujinRelation);
    }

    @Override
    public Boolean isOnline(String tid) throws Exception {
        return null;
    }

    @Override
    public ResponseJson openRujinDevice(String orderId, String tid, String hd, GzgPayTypeEnum gzgPayTypeEnum) throws Exception {
        return null;
    }

    @Override
    public ResponseJson maintainDevice(String tid) throws Exception {
        return null;
    }


    /**
     * 通过name获取GzgRujinRelation
     * @param name
     * @return
     */
    public GzgProgramme getGzgRujinRelationByName(String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        String result = callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME, jsonObject);
        GzgProgramme gzgRujinRelation = JSONObject.toJavaObject(JSONObject.parseObject(result), GzgProgramme.class);
        return gzgRujinRelation;
    }


    /**
     * 通过name获取配货方案
     * @param name
     * @return
     */
//    public List<GzgProgramme> getGzgRujinRelationByName(String name) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("name",name);
//        String result = callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME, jsonObject);
//        GzgProgramme gzgRujinRelation = JSONObject.toJavaObject(JSONObject.parseObject(result), GzgProgramme.class);
//        return gzgRujinRelation;
//    }



    public void updateAndInsertGzgRunjin(JSONObject jsonObject){
        GzgProgramme rujinRelation = getGzgRujinRelationByName(jsonObject.getString("name"));
        String plan_name = rujinRelation.getName();

    }

}
