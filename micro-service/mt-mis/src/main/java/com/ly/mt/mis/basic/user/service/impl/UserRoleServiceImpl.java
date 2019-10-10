package com.ly.mt.mis.basic.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.entity.BasicRole;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.user.service.UserRoleService;
import com.ly.mt.mis.basic.user.vo.UserRoleDatagridVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BASIC_USER_ROLE;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 系统管理-用户角色管理
 *
 * @author taoye
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService {
    @Override
    public ResponseJson insertUserRole(JSONObject jsonObject) throws Exception {
        BasicUserRole basicUserRole = JSONObject.toJavaObject(jsonObject, BasicUserRole.class);
        basicUserRole.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BASIC_USER_ROLE));
        basicUserRoleDao.insertBasicUserRole(basicUserRole);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson deleteUserRole(JSONObject jsonObject) throws Exception {
        BasicUserRole basicUserRole = JSONObject.toJavaObject(jsonObject, BasicUserRole.class);
        basicUserRoleDao.deleteBasicUserRole(basicUserRole);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson loadUserRoleDatagrid(JSONObject jsonObject) throws Exception {
        String userId = jsonObject.getString("userId");
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByUserIdFromRedis(userId);
        Datagrid datagrid = new Datagrid();
        List<UserRoleDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(basicUserRoles), new TypeReference<List<UserRoleDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    BasicRole br = basicRoleDao.getBasicRoleByIdFromRedis(vo.getRoleId());
                    vo.setRoleName(br.getName());
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public ResponseJson loadRoleDatagrid(JSONObject jsonObject) throws Exception {
        BasicRole basicRole = JSONObject.toJavaObject(jsonObject, BasicRole.class);
        basicRole.setValidStatus(VALID_STATUS_YES.getId());
        List<BasicRole> basicRoles = basicRoleDao.listBasicRoleFromMysql(basicRole);
        Datagrid datagrid = new Datagrid();
        List<UserRoleDatagridVo> vos = new ArrayList<>();
        String userId = jsonObject.getString("userId");
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByUserIdFromRedis(userId);
        Map<String, String> map = new HashMap<>();
        basicUserRoles.forEach(
                basicUserRole -> map.put(basicUserRole.getRoleId(), basicUserRole.getRoleId())
        );
        basicRoles.forEach(
                br -> {
                    if (StringUtil.isEmpty(map.get(br.getId()))) {
                        UserRoleDatagridVo vo = new UserRoleDatagridVo();
                        vo.setRoleId(br.getId());
                        vo.setRoleName(br.getName());
                        vo.setUserId(userId);
                        vos.add(vo);
                    }
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}