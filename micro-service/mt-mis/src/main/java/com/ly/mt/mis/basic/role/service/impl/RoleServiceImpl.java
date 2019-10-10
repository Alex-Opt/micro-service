package com.ly.mt.mis.basic.role.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.entity.BasicRole;
import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.role.service.RoleService;
import com.ly.mt.mis.basic.role.vo.RoleCombotreeVo;
import com.ly.mt.mis.basic.role.vo.RoleTreegridVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.RoleLevel.ROLE_LEVEL_FIVE;
import static com.ly.mt.core.base.dict.RoleLevel.ROLE_LEVEL_ONE;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_NO;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 系统管理-角色管理
 *
 * @author taoye
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    @Override
    public ResponseJson insertRole(JSONObject jsonObject) throws Exception {
        BasicRole basicRole = JSONObject.toJavaObject(jsonObject, BasicRole.class);
        String time = DateUtil.getNowTimeStr();
        basicRole.setCreateTime(time);
        basicRole.setUpdateTime(time);
        String loginUserId = getLoginUserId();
        basicRole.setCreateUser(loginUserId);
        basicRole.setUpdateUser(loginUserId);
        basicRole.setValidStatus(VALID_STATUS_YES.getId());
        if (StringUtil.isEmpty(basicRole.getParentId()) && ROLE_LEVEL_ONE.getId().equals(basicRole.getRoleLevel())) {
            basicRole.setParentId(basicRole.getProjectType());
        }
        basicRoleDao.insertBasicRole(basicRole);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson updateRole(JSONObject jsonObject) throws Exception {
        BasicRole basicRole = JSONObject.toJavaObject(jsonObject, BasicRole.class);
        basicRole.setUpdateTime(DateUtil.getNowTimeStr());
        basicRole.setUpdateUser(getLoginUserId());
        basicRoleDao.updateBasicRole(basicRole);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseJson updateValidStatus(JSONObject jsonObject) throws Exception {
        BasicRole basicRole = JSONObject.toJavaObject(jsonObject, BasicRole.class);
        basicRole.setUpdateTime(DateUtil.getNowTimeStr());
        basicRole.setUpdateUser(getLoginUserId());
        if (VALID_STATUS_NO.getId().equals(basicRole.getValidStatus())) {
            //禁用
            StringBuilder ids = new StringBuilder();
            ids = getIdsById(ids, basicRole.getId());
            if (ids.length() > 0) {
                String roleIds = ids.deleteCharAt(ids.length() - 1).toString();
                basicRole.setId(roleIds);
                basicRoleDao.updateBasicRole(basicRole);
                BasicRoleRight basicRoleRight = new BasicRoleRight();
                basicRoleRight.setRoleId(roleIds);
                basicRoleRightDao.deleteBasicRoleRight(basicRoleRight);
                BasicUserRole basicUserRole = new BasicUserRole();
                basicUserRole.setRoleId(roleIds);
                basicUserRoleDao.deleteBasicUserRole(basicUserRole);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "禁用角色出错");
            }
        } else {
            // 启运
            basicRoleDao.updateBasicRole(basicRole);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson loadRoleTreegrid(JSONObject jsonObject) throws Exception {
        BasicRole basicRole = JSONObject.toJavaObject(jsonObject, BasicRole.class);
        basicRole.setId(jsonObject.getString("searchId"));
        basicRole.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        List<BasicRole> basicRoles = basicRoleDao.listBasicRoleFromMysql(basicRole);
        List<RoleTreegridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(basicRoles), new TypeReference<List<RoleTreegridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setState("open");
                    vo.setValidStatusName(EnumUtil.getNameById("ValidStatus", vo.getValidStatus()));
                    vo.setRoleTypeName(EnumUtil.getNameById("RoleType", vo.getRoleType()));
                    String createUser = vo.getCreateUser();
                    if (StringUtil.isNotEmpty(createUser)) {
                        vo.setCreateUserName(userDao.getUserByIdFromRedis(createUser).getUserName());
                    }
                    String roleId = vo.getId();
                    if (StringUtil.isNotEmpty(roleId)) {
                        List<User> list = userDao.listUserByRoleIdFromRedis(roleId);
                        vo.setCountUser(String.valueOf(list.size()));
                    } else {
                        vo.setCountUser("0");
                    }
                }
        );
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vos);
    }


    @Override
    public ResponseJson restRoleTreegrid(JSONObject jsonObject) throws Exception {
        List<RoleTreegridVo> vos;
        String id = jsonObject.getString("id");
        if (StringUtil.isEmpty(id)) {
            List<Map<String, String>> maps = EnumUtil.listDictByDictName("ProjectType");
            vos = JSONObject.parseObject(JSONObject.toJSONString(maps), new TypeReference<List<RoleTreegridVo>>() {
            });
            vos.forEach(
                    vo -> vo.setState("closed")
            );
        } else {
            List<BasicRole> basicRoles = basicRoleDao.listBasicRoleByParentIdFromRedis(id);
            vos = JSONObject.parseObject(JSONObject.toJSONString(basicRoles), new TypeReference<List<RoleTreegridVo>>() {
            });
            vos.forEach(
                    vo -> {
                        if (!ROLE_LEVEL_FIVE.getId().equals(vo.getRoleLevel())) {
                            vo.setState("closed");
                        } else {
                            vo.setState("open");
                        }
                        vo.setValidStatusName(EnumUtil.getNameById("ValidStatus", vo.getValidStatus()));
                        vo.setRoleTypeName(EnumUtil.getNameById("RoleType", vo.getRoleType()));
                        String createUser = vo.getCreateUser();
                        if (StringUtil.isNotEmpty(createUser)) {
                            vo.setCreateUserName(userDao.getUserByIdFromRedis(createUser).getUserName());
                        }
                        String roleId = vo.getId();
                        if (StringUtil.isNotEmpty(roleId)) {
                            List<User> list = userDao.listUserByRoleIdFromRedis(roleId);
                            vo.setCountUser(String.valueOf(list.size()));
                        } else {
                            vo.setCountUser("0");
                        }
                    }
            );
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vos);
    }


    @Override
    public ResponseJson loadRoleCombotree(JSONObject jsonObject) throws Exception {
        String roleLevel = jsonObject.getString("roleLevel");
        String parentId = jsonObject.getString("parentId");
        RoleCombotreeVo vo = new RoleCombotreeVo();
        vo.setId(parentId);
        vo = getComboTreeVo(vo, roleLevel);
        List<?> children = vo.getChildren();
        if (null == children || children.size()<=0) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "请先添加上级角色");
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, children);
    }

    private RoleCombotreeVo getComboTreeVo(RoleCombotreeVo vo, String roleLevel) {
        if (roleLevel.equals(vo.getRoleLevel())) {
            return vo;
        }
        List<BasicRole> basicRoles = basicRoleDao.listBasicRoleByParentIdFromRedis(vo.getId());
        if (basicRoles.size() <= 0) {
            return vo;
        }
        List<RoleCombotreeVo> roleCombotreeVos = JSONObject.parseObject(JSONObject.toJSONString(basicRoles), new TypeReference<List<RoleCombotreeVo>>() {
        });
        for (RoleCombotreeVo roleCombotreeVo : roleCombotreeVos) {
            getComboTreeVo(roleCombotreeVo, roleLevel);
        }
        vo.setChildren(roleCombotreeVos);
        return vo;
    }

    private StringBuilder getIdsById(StringBuilder ids, String id) {
        ids.append(id).append(",");
        List<BasicRole> basicRoles = basicRoleDao.listBasicRoleByParentIdFromRedis(id);
        if (basicRoles.size() <= 0) {
            return ids;
        }
        basicRoles.forEach(
                basicRole -> getIdsById(ids, basicRole.getId())
        );
        return ids;
    }
}