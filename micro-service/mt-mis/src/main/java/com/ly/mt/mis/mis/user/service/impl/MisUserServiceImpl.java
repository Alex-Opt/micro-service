package com.ly.mt.mis.mis.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.mis.user.service.MisUserService;
import com.ly.mt.mis.mis.user.vo.MisUserDatagridVo;
import com.ly.mt.mis.mis.user.vo.MisUserInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_MIS;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_MIS;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_NO;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 系统管理-用户管理
 *
 * @author taoye
 */
@Service
public class MisUserServiceImpl extends BaseServiceImpl implements MisUserService {
    @Override
    public ResponseJson loadUserInfo(JSONObject jsonObject) throws Exception {
        String id = jsonObject.getString("id");
        User user = userDao.getUserByIdFromRedis(id);
        MisUserInfoVo vo = JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(user)), MisUserInfoVo.class);
        String roleNames = getRoleNamesByUserId(id);
        vo.setRoleName(roleNames);
        String validStatus = user.getValidStatus();
        String validUserId = user.getValidUser();
        if (VALID_STATUS_NO.getId().equals(validStatus) && StringUtil.isNotEmpty(validUserId)) {
            User validUser = userDao.getUserByIdFromRedis(validUserId);
            vo.setValidUser(validUser.getUserName());
        } else {
            vo.setValidUser("");
            vo.setValidTime("");
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo);
    }


    @Override
    public ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        user.setLastLoginTime(getBetweenSql(jsonObject.getString("lastLoginTimeStart"), jsonObject.getString("lastLoginTimeEnd")));
        user.setQuickType(QUICK_TYPE_MIS.getId());
        user.setProjectType(PROJECT_TYPE_MIS.getId());
        Datagrid datagrid = new Datagrid();
        String roleId = jsonObject.getString("roleId");
        if (StringUtil.isNotEmpty(roleId)) {
            String userIds = getUserIdsByRoleId(roleId);
            if (StringUtil.isEmpty(userIds)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
            }
            String userId = user.getId();
            if (StringUtil.isNotEmpty(userId) && !userIds.contains(userId)) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
            }
            user.setId(userIds);
        }
        user.setProjectType(PROJECT_TYPE_MIS.getId());
        datagrid = userDao.loadDatagridFromMysql(user, page);
        List<MisUserDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<MisUserDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    String roleName = getRoleNamesByUserId(vo.getId());
                    vo.setRoleNames(roleName);
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}