package com.ly.mt.mis.gzg.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.entity.BasicArea;
import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.gzg.user.service.GzgUserService;
import com.ly.mt.mis.gzg.user.vo.GzgUserDatagridVo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_GZG_B;
import static com.ly.mt.core.base.dict.RightType.RIGHT_TYPE_AREA;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * MOTI售卖柜-用户管理-用户列表
 *
 * @author taoye
 */
@Service
public class GzgUserServiceImpl extends BaseServiceImpl implements GzgUserService {
    @Override
    public ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(0);
        datagrid.setRows(null);
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
        user.setProjectType(PROJECT_TYPE_GZG_B.getId());
        datagrid = userDao.loadDatagridFromMysql(user, page);
        List<GzgUserDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<GzgUserDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    String userId = vo.getId();
                    String roleNames = getRoleNamesByUserId(userId);
                    vo.setRoleNames(roleNames);
                    String areaNames = getAreaNamesByUserId(userId);
                    vo.setAreaName(areaNames);
                    String createUserId = vo.getCreateUser();
                    if (StringUtil.isNotEmpty(createUserId)) {
                        User creatUser = userDao.getUserByIdFromRedis(vo.getCreateUser());
                        vo.setCreateUserName(creatUser.getUserName());
                    }
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    /**
     * 根据userId查询areaNames
     *
     * @author taoye
     */
    private String getAreaNamesByUserId(String userId) {
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByUserIdFromRedis(userId);
        StringBuilder sb = new StringBuilder();
        for (BasicUserRole basicUserRole : basicUserRoles) {
            List<BasicRoleRight> basicRoleRights = basicRoleRightDao.listBasicRoleRightByRoleIdAndRightTypeFromRedis(basicUserRole.getRoleId(), RIGHT_TYPE_AREA.getId());
            for (BasicRoleRight basicRoleRight : basicRoleRights) {
                String rightId = basicRoleRight.getRightId();
                if (StringUtil.isEmpty(rightId)) {
                    continue;
                }
                BasicArea basicArea = basicAreaDao.getBasicAredByMIdFromRedis(rightId);
                String mId = basicArea.getMId();
                if (StringUtil.isEmpty(mId)) {
                    continue;
                }
                String cityName = basicArea.getMName();
                BasicArea parentArea = basicAreaDao.getBasicAredByMIdFromRedis(mId);
                String provinceName = parentArea.getMName();
                if (StringUtil.isEmpty(provinceName)) {
                    continue;
                }
                sb.append(provinceName).append("-").append(cityName).append("、");
            }
        }
        if (sb.length() > 0) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}