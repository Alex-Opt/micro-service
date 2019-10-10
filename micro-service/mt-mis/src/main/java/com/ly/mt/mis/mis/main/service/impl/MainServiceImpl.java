package com.ly.mt.mis.mis.main.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.entity.BasicFunc;
import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.mis.main.service.MainService;
import com.ly.mt.mis.mis.main.vo.LeftMenuVo;
import com.ly.mt.mis.mis.main.vo.TopMenuVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ly.mt.core.base.dict.FuncLevel.FUNC_LEVEL_ONE;
import static com.ly.mt.core.base.dict.FuncLevel.FUNC_LEVEL_THREE;
import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_MIS;
import static com.ly.mt.core.base.dict.RightType.RIGHT_TYPE_FUNC;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 主页
 *
 * @author taoye
 */
@Service
public class MainServiceImpl extends BaseServiceImpl implements MainService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainServiceImpl.class);
    private final static String ADMIN_ID = "0";

    @Override
    public List<TopMenuVo> loadTopMenu() throws Exception {
        List<BasicFunc> basicFuncs = basicFuncDao.listBasicFuncByProjectTypeAndFuncLevelFromRedis(PROJECT_TYPE_MIS.getId(), FUNC_LEVEL_ONE.getId());
        if (basicFuncs.size() <= 0) {
            LOGGER.error("MainServiceImpl.loadTopMenu listBasicFuncByProjectTypeAndFuncLevelFromRedis return null");
            return null;
        }
        List<TopMenuVo> topMenuVos = JSONObject.parseObject(JSONObject.toJSONString(basicFuncs), new TypeReference<List<TopMenuVo>>() {
        });
        String loginUserId = getLoginUserId();
        if (!ADMIN_ID.equals(loginUserId)) {
            // 用户角色权限过滤
            Map<String, String> rightMap = getRightMap(loginUserId);
            int i = topMenuVos.size() - 1;
            for (; i >= 0; i--) {
                if (StringUtil.isEmpty(rightMap.get(topMenuVos.get(i).getId()))) {
                    topMenuVos.remove(i);
                }
            }
        }
        return topMenuVos;
    }


    @Override
    public ResponseJson loadLeftMenu(JSONObject jsonObject) throws Exception {
        String id = jsonObject.getString("id");
        LeftMenuVo vo = new LeftMenuVo();
        vo.setId(id);
        String loginUserId = getLoginUserId();
        if (ADMIN_ID.equals(loginUserId)) {
            vo = getLeftMenu(vo);
        } else {
            // 用户角色权限过滤
            Map<String, String> rightMap = getRightMap(loginUserId);
            vo = getLeftMenu(vo, rightMap);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo.getChildren());
    }


    /**
     * 递归查询左侧菜单-管理员
     *
     * @author taoye
     */
    private LeftMenuVo getLeftMenu(LeftMenuVo vo) {
        if (FUNC_LEVEL_THREE.getId().equals(vo.getFuncLevel())) {
            return vo;
        }
        List<BasicFunc> erpFuncs = basicFuncDao.listBasicFuncByProjectTypeAndParentIdFromRedis(PROJECT_TYPE_MIS.getId(), vo.getId());
        if (null == erpFuncs || erpFuncs.size() <= 0) {
            return vo;
        }
        List<LeftMenuVo> leftMenuVos = JSONObject.parseObject(JSONObject.toJSONString(erpFuncs), new TypeReference<List<LeftMenuVo>>() {
        });
        for (LeftMenuVo leftMenuVo : leftMenuVos) {
            getLeftMenu(leftMenuVo);
        }
        vo.setChildren(leftMenuVos);
        return vo;
    }


    /**
     * 递归查询左侧菜单-用户
     *
     * @author taoye
     */
    private LeftMenuVo getLeftMenu(LeftMenuVo vo, Map<String, String> rightMap) {
        if (FUNC_LEVEL_THREE.getId().equals(vo.getFuncLevel())) {
            return vo;
        }
        List<BasicFunc> basicFuncs = basicFuncDao.listBasicFuncByProjectTypeAndParentIdFromRedis(PROJECT_TYPE_MIS.getId(), vo.getId());
        if (basicFuncs.size() <= 0) {
            return vo;
        }
        List<LeftMenuVo> leftMenuVos = JSONObject.parseObject(JSONObject.toJSONString(basicFuncs), new TypeReference<List<LeftMenuVo>>() {
        });
        for (int i = leftMenuVos.size() - 1; i >= 0; i--) {
            if (StringUtil.isEmpty(rightMap.get(leftMenuVos.get(i).getId()))) {
                leftMenuVos.remove(i);
            }
        }
        for (LeftMenuVo leftMenuVo : leftMenuVos) {
            getLeftMenu(leftMenuVo, rightMap);
        }
        vo.setChildren(leftMenuVos);
        return vo;
    }


    /**
     * 获取用户权限集合
     *
     * @author taoye
     */
    private Map<String, String> getRightMap(String loginUserId) {
        Map<String, String> map = new HashMap<>();
        List<BasicRoleRight> listAll = new ArrayList<>();
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByUserIdFromRedis(loginUserId);
        if (basicUserRoles.size() <= 0) {
            return map;
        }
        for (BasicUserRole basicUserRole : basicUserRoles) {
            String roleId = basicUserRole.getRoleId();
            if (StringUtil.isEmpty(roleId)) {
                continue;
            }
            List<BasicRoleRight> basicRoleRights = basicRoleRightDao.listBasicRoleRightByRoleIdAndRightTypeFromRedis(roleId, RIGHT_TYPE_FUNC.getId());
            if (basicRoleRights.size() > 0) {
                listAll.addAll(basicRoleRights);
            }
        }
        if (listAll.size() <= 0) {
            return map;
        }
        listAll = new ArrayList<>(new LinkedHashSet<>(listAll));
        listAll.forEach(
                basicRoleRight -> {
                    String id = basicRoleRight.getRightId();
                    map.put(id, id);
                    String funcLevel = null;
                    while (!FUNC_LEVEL_ONE.getId().equals(funcLevel)) {
                        BasicFunc basicFunc = basicFuncDao.getBasicFuncByIdFromRedis(id);
                        id = basicFunc.getParentId();
                        if (StringUtil.isEmpty(map.get(id))) {
                            map.put(id, id);
                            funcLevel = basicFunc.getFuncLevel();
                        } else {
                            funcLevel = "1";
                        }
                    }
                }
        );
        return map;
    }
}
