package com.ly.mt.mis.basic.area.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.data.basic.entity.BasicArea;
import com.ly.mt.mis.basic.area.service.AreaService;
import com.ly.mt.mis.basic.area.vo.AreaComboboxVo;
import com.ly.mt.mis.basic.area.vo.AreaTreeVo;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 行政区域操作接口
 *
 * @author taoye
 */
@Service
public class AreaServiceImpl extends BaseServiceImpl implements AreaService {
    @Override
    public ResponseJson loadAreaCombobox(JSONObject jsonObject) throws Exception {
        String pid = jsonObject.getString("pid");
        List<BasicArea> list = basicAreaDao.listBasicAreaByMPidFromRedis(pid);
        List<AreaComboboxVo> vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<AreaComboboxVo>>() {
        });
        AreaComboboxVo vo = new AreaComboboxVo();
        vo.setId("-1");
        vo.setName("全部");
        vos.add(0, vo);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vos);
    }


    @Override
    public ResponseJson loadAreaTree(JSONObject jsonObject) throws Exception {
        AreaTreeVo vo = new AreaTreeVo();
        vo.setMId("0");
        vo = getTreeVo(vo, jsonObject.getString("areaLevel"));
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo.getChildren());
    }


    /**
     * 递归查询树
     *
     * @author taoye
     */
    private AreaTreeVo getTreeVo(AreaTreeVo vo, String areaLevel) {
        if (areaLevel.equals(vo.getLevel())) {
            vo.setState("open");
            return vo;
        }
        List<BasicArea> basicAreas = basicAreaDao.listBasicAreaByMPidFromRedis(vo.getMId());
        if (basicAreas.size() <= 0) {
            vo.setState("open");
            return vo;
        }
        List<AreaTreeVo> vos = JSONObject.parseObject(JSONObject.toJSONString(basicAreas), new TypeReference<List<AreaTreeVo>>() {
        });
        for (AreaTreeVo areaTreeVo : vos) {
            getTreeVo(areaTreeVo, areaLevel);
        }
        vo.setChildren(vos);
        vo.setState("closed");
        return vo;
    }
}