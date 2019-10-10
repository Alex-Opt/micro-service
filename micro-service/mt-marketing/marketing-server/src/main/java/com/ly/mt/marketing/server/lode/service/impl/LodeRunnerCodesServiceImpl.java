package com.ly.mt.marketing.server.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.marketing.LodeRunnerCodes;
import com.ly.mt.core.common.util.DateUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.marketing.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.marketing.server.lode.mapper.LodeRunnerCodesServiceMapper;
import com.ly.mt.marketing.server.lode.service.LodeRunnerCodesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/22
 * @描述
 */
@Service
public class LodeRunnerCodesServiceImpl extends BaseServiceImpl implements LodeRunnerCodesService {

    private static final String CODE = "code";

    @Resource
    private LodeRunnerCodesServiceMapper mapper;

    @Override
    public JSONObject getCode(String json) throws Exception {
        // 查询当前人是否存在邀请码
        LodeRunnerCodes param = new LodeRunnerCodes();
        param.setUserId(getUserId());
        LodeRunnerCodes code = mapper.getByUId(param);

        Map<String, Object> result = new HashMap<>();
        // 不存在生成一个邀请码
        if (code != null){
            result.put(CODE, code.getCode());
        } else {
            Long shopId = mapper.getShopId(param);
            if (shopId == null){
                throw new Exception("您还没有店铺！");
            }
            param.setShopId(shopId);
            param.setNums(0);
            param.setCreateTime(DateUtil.getNowDateTimeStr());
            String newCode = buildCode(0);
            param.setCode(newCode);
            mapper.save(param);
            result.put(CODE, newCode);
        }
        // 存在直接返回邀请码
        return JsonUtil.getSuccessJson(result);
    }

    /**
     *@Description 生成一个八位数不重复的编码
     *@Author  zhuyh
     */
    private String buildCode(int num) throws Exception{
        if (num == 5){
            throw new Exception("生成专属码失败！");
        }
        String code = getNewCode();
        // 判断此编码数据库是否存在，如果存在再生产一个;
        LodeRunnerCodes checkParam = new LodeRunnerCodes();
        checkParam.setCode(code);
        int count = mapper.getCountByCode(checkParam);
        if (count > 1){
            num++;
            return buildCode(num);
        } else {
            return code;
        }
    }

    /**
     *@Description　生成一个新的code
     *@Author  zhuyh
     */
    private String getNewCode() {
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f","g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z" };
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
}
