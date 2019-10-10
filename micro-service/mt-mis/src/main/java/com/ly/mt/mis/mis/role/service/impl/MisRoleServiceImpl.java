package com.ly.mt.mis.mis.role.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Tree;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.mis.role.service.MisRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.FuncLevel.FUNC_LEVEL_FOUR;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BASIC_ROLE_RIGHT;
import static com.ly.mt.core.base.dict.RightType.RIGHT_TYPE_FUNC;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 运营系统角色权限
 *
 * @author taoye
 */
@Service
public class MisRoleServiceImpl extends BaseServiceImpl implements MisRoleService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseJson insertRoleFunc(JSONObject jsonObject) throws Exception {
        // 删除原有权限
        String roleId = jsonObject.getString("roleId");
        BasicRoleRight basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRoleId(roleId);
        basicRoleRightDao.deleteBasicRoleRight(basicRoleRight);
        // 新权限
        String nodes = jsonObject.getString("nodes");
        List<Tree> vos = JSONObject.parseObject(nodes, new TypeReference<List<Tree>>() {
        });
        List<BasicRoleRight> basicRoleRights = new ArrayList<>();
        for (Tree vo : vos) {
            if (!FUNC_LEVEL_FOUR.getId().equals(vo.getAttributes())) {
                continue;
            }
            BasicRoleRight roleRight = new BasicRoleRight();
            roleRight.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BASIC_ROLE_RIGHT));
            roleRight.setRoleId(roleId);
            roleRight.setRightId(vo.getId());
            roleRight.setRightLevel(vo.getAttributes());
            roleRight.setRightType(RIGHT_TYPE_FUNC.getId());
            basicRoleRights.add(roleRight);
        }
        basicRoleRightDao.insertBasicRoleRights(basicRoleRights);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson listRoleFunc(JSONObject jsonObject) throws Exception {
        String roleId = jsonObject.getString("roleId");
        if (StringUtil.isEmpty(roleId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "角色ID错误");
        }
        List<BasicRoleRight> basicRoleRights = basicRoleRightDao.listBasicRoleRightByRoleIdAndRightTypeFromRedis(roleId, RIGHT_TYPE_FUNC.getId());
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, basicRoleRights);
    }
}