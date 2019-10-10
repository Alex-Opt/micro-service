package com.ly.mt.mis.basic.role.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.role.service.RoleUserService;
import com.ly.mt.mis.basic.role.vo.RoleUserDatagridVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 系统管理-角色用户管理
 *
 * @author taoye
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl implements RoleUserService {
    @Override
    public ResponseJson loadRoleUserDatagrid(JSONObject jsonObject) throws Exception {
        Datagrid datagrid = new Datagrid();
        String roleId = jsonObject.getString("roleId");
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByRoleIdFromRedis(roleId);
        List<RoleUserDatagridVo> vos = new ArrayList<>();
        basicUserRoles.forEach(
                basicUserRole -> {
                    String userId = basicUserRole.getUserId();
                    if (StringUtil.isNotEmpty(userId)) {
                        User user = userDao.getUserByIdFromRedis(userId);
                        RoleUserDatagridVo vo = JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(user)), RoleUserDatagridVo.class);
                        vos.add(vo);
                    }
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}