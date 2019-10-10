package com.ly.mt.center.data.runnerprofit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.runnerprofit.entity.dto.RElasticLoadRunnerTreesDto;
import com.ly.mt.center.data.runnerprofit.entity.dto.RSimpRunnerTreesDto;
import com.ly.mt.center.data.runnerprofit.mapper.RLodeRunnerUserTreesMapper;
import com.ly.mt.center.data.runnerprofit.service.RLodeRunnerUserTreesService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @author panjingtian
 * @description 到家c赚钱人操作
 * @date 2019/6/28 5:55 PM
 */
@Service("rLodeRunnerUserTreesServiceImpl")
public class RLodeRunnerUserTreesServiceImpl implements RLodeRunnerUserTreesService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RLodeRunnerUserTreesServiceImpl.class);
    @Resource
    private RLodeRunnerUserTreesMapper treesMapper;


    @Override
    public ResponseJson fiveTrees(JSONObject jsonObject) {
        try {
            String stringUserId = jsonObject.getString("userId");
            Long userId = Long.valueOf(stringUserId);
            List<RSimpRunnerTreesDto> list = treesMapper.findRunnerTressByUserId(userId, Thread.currentThread().getName());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, convertTreesDto(list));
        } catch (Exception e) {
            LOGGER.error("RLodeRunnerUserTreesServiceImpl.fiveTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 包装数据
     * 不满5级别把自己放在1级别里，满了5级不算自己在里边
     *
     * @param list
     * @return
     */
    private JSONObject convertTreesDto(List<RSimpRunnerTreesDto> list) {

        JSONObject result = new JSONObject(1);
        if (list == null || list.size() < 1) {
            return result;
        } else {
            RElasticLoadRunnerTreesDto treesDto = new RElasticLoadRunnerTreesDto();
            Map<Integer, Long> map = new TreeMap<>();
            list.forEach(dto -> {
                map.put(dto.getRank(), dto.getFatherId());
            });
            treesDto.setUserId(list.get(0).getUserId());
            treesDto.setTrees(map);
            result = (JSONObject) JSONObject.toJSON(treesDto);
            return result;
        }
    }


}
