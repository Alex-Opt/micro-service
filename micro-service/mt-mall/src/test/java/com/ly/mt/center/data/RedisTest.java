package com.ly.mt.center.data;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.service.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.redis.RedisKey.REDIS_USER_ENTITY_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_WX_APPLET_FORM_ID;

public class RedisTest extends MallApplicationTest {
    @Autowired
    RedisService redisService;

    @Test
    public void testSendMq() {
        redisService.set(REDIS_USER_ENTITY_ID, "111111");
    }

    @Test
    public void testQueue() {
        String openId = "oOYP80exlPT4zbMVoLLGb1M5ov-s";
        String formIds = "a4,b4,c4,d4,e4,f4";
        List<String> setFromStr = getSetFromStr(formIds);
        //获取老的缓存中的form_id
        String formIdCacheOld = redisService.get(REDIS_WX_APPLET_FORM_ID, openId);
        if (StringUtil.isNotEmpty(formIdCacheOld)) {
            List<String> formIdList = JSONObject.parseArray(formIdCacheOld, String.class);
            //存取的formId够用就行。将旧的form_id添加到新的集合setFromStr后面。addAll方法加入的列表元素的顺序是按照原来的顺序向后加入的
            setFromStr.addAll(formIdList);
        }
        int i =1;
        List<String> formList = new ArrayList<>(10);
        for (String formId : setFromStr) {
            if (i>10){
                break;
            }
            formList.add(formId);
            i++;
        }
        redisService.set(REDIS_WX_APPLET_FORM_ID, openId, JSONObject.toJSONString(formList), 7L, TimeUnit.DAYS);
    }

    /**
     * 将传过来的用逗号分割多个字符串转换成set集合
     *
     * @param formIds
     * @return
     */
    private List<String> getSetFromStr(String formIds) {
        String[] formIdArray = formIds.split(",");
        Set<String> set = new HashSet();
        for (String form_id : formIdArray) {
            set.add(form_id);
        }
        List li = new ArrayList();
        li.addAll(set);
        return li;
    }
}