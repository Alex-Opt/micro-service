package com.ly.mt.mis.basic.func.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.data.basic.entity.BasicFunc;
import com.ly.mt.core.data.basic.entity.BasicRoleRight;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.func.service.FuncService;
import com.ly.mt.mis.basic.func.vo.FuncCombotreeVo;
import com.ly.mt.mis.basic.func.vo.FuncTreeVo;
import com.ly.mt.mis.basic.func.vo.FuncTreegridVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.FuncLevel.FUNC_LEVEL_FOUR;
import static com.ly.mt.core.base.dict.FuncType.*;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BASIC_FUNC;
import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_MIS;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_NO;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME;

/**
 * 系统管理-菜单管理
 *
 * @author taoye
 */
@Service
public class FuncServiceImpl extends BaseServiceImpl implements FuncService {
    @Override
    public ResponseJson insertFunc(JSONObject jsonObject) throws Exception {
        BasicFunc basicFunc = JSONObject.toJavaObject(jsonObject, BasicFunc.class);
        String funcType = basicFunc.getFuncType();
        if (StringUtil.isEmpty(basicFunc.getParentId()) && FUNC_TYPE_MODULE.getId().equals(funcType)) {
            basicFunc.setParentId(basicFunc.getProjectType());
        }
        basicFunc.setFuncLevel(funcType);
        String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BASIC_FUNC);
        basicFunc.setId(id);
        String sort = basicFuncDao.getMaxSortByParentIdFromRedis(basicFunc.getParentId());
        sort = String.valueOf(Integer.valueOf(sort) + 1);
        basicFunc.setSort(sort);
        String time = DateUtil.getNowTimeStr();
        basicFunc.setCreateTime(time);
        basicFunc.setUpdateTime(time);
        String loginUserId = getLoginUserId();
        basicFunc.setCreateUser(loginUserId);
        basicFunc.setUpdateUser(loginUserId);
        basicFunc.setValidStatus(VALID_STATUS_YES.getId());
        List<BasicFunc> basicFuncs = new ArrayList<>();
        basicFuncs.add(basicFunc);
        if (FUNC_TYPE_MENU.getId().equals(funcType)) {
            // 新增菜单时自动新增查看按钮
            BasicFunc five = new BasicFunc();
            five.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_BASIC_FUNC));
            five.setName("查看");
            five.setParentId(id);
            five.setSort("0");
            five.setFuncType(FUNC_TYPE_BUTTON.getId());
            five.setFuncLevel(FUNC_TYPE_BUTTON.getId());
            five.setProjectType(basicFunc.getProjectType());
            five.setCode("0");
            five.setCreateTime(time);
            five.setUpdateTime(time);
            five.setCreateUser(loginUserId);
            five.setUpdateUser(loginUserId);
            five.setValidStatus(VALID_STATUS_YES.getId());
            basicFuncs.add(five);
        }
        basicFuncDao.insertBasicFunc(basicFuncs);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseJson deleteFunc(JSONObject jsonObject) throws Exception {
        BasicFunc basicFunc = JSONObject.toJavaObject(jsonObject, BasicFunc.class);
        basicFunc.setValidStatus(VALID_STATUS_NO.getId());
        basicFunc.setUpdateTime(DateUtil.getNowTimeStr());
        basicFunc.setUpdateUser(getLoginUserId());
        basicFuncDao.updateBasicFunc(basicFunc);
        // 相关缓存处理，涉及用户注销登录
        String id = basicFunc.getId();
        redisRefresh(id);
        // 删除相关角色权限
        BasicRoleRight basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRightId(id);
        basicRoleRightDao.deleteBasicRoleRight(basicRoleRight);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseJson updateFunc(JSONObject jsonObject) throws Exception {
        BasicFunc basicFunc = JSONObject.toJavaObject(jsonObject, BasicFunc.class);
        basicFunc.setUpdateTime(DateUtil.getNowTimeStr());
        basicFunc.setUpdateUser(getLoginUserId());
        basicFuncDao.updateBasicFunc(basicFunc);
        // 相关缓存处理，涉及用户注销登录
        String id = basicFunc.getId();
        redisRefresh(id);
        // 删除相关角色权限
        BasicRoleRight basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRightId(id);
        basicRoleRightDao.deleteBasicRoleRight(basicRoleRight);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseJson sortFunc(JSONObject jsonObject) throws Exception {
        String ids = jsonObject.getString("ids");
        String sorts = jsonObject.getString("sorts");
        String[] idArr = ids.split(",");
        String[] sortArr = sorts.split(",");
        // 交换sort第一条数据处理
        BasicFunc basicFunc = new BasicFunc();
        basicFunc.setId(idArr[0]);
        basicFunc.setSort(sortArr[0]);
        basicFunc.setUpdateTime(DateUtil.getNowTimeStr());
        basicFunc.setUpdateUser(getLoginUserId());
        basicFuncDao.updateBasicFunc(basicFunc);
        // 相关缓存处理，涉及用户注销登录
        String id = basicFunc.getId();
        redisRefresh(id);
        // 删除相关角色权限
        BasicRoleRight basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRightId(id);
        basicRoleRightDao.deleteBasicRoleRight(basicRoleRight);
        // 交换sort第二条数据处理
        basicFunc.setId(idArr[1]);
        basicFunc.setSort(sortArr[1]);
        basicFunc.setUpdateTime(DateUtil.getNowTimeStr());
        basicFunc.setUpdateUser(getLoginUserId());
        basicFuncDao.updateBasicFunc(basicFunc);
        // 相关缓存处理，涉及用户注销登录
        id = basicFunc.getId();
        redisRefresh(id);
        // 删除相关角色权限
        basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRightId(id);
        basicRoleRightDao.deleteBasicRoleRight(basicRoleRight);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson loadFuncTreegrid(JSONObject jsonObject) throws Exception {
        List<FuncTreegridVo> vos;
        String id = jsonObject.getString("id");
        if (StringUtil.isEmpty(id)) {
            List<Map<String, String>> maps = EnumUtil.listDictByDictName("ProjectType");
            vos = JSONObject.parseObject(JSONObject.toJSONString(maps), new TypeReference<List<FuncTreegridVo>>() {
            });
            vos.forEach(
                    vo -> {
                        vo.setState("closed");
                        vo.setFuncLevelName(EnumUtil.getNameById("FuncLevel", vo.getFuncLevel()));
                        vo.setFuncTypeName(EnumUtil.getNameById("FuncType", vo.getFuncType()));
                    }
            );
        } else {
            List<BasicFunc> basicFuncs = basicFuncDao.listBasicFuncByProjectTypeAndParentIdFromRedis(PROJECT_TYPE_MIS.getId(), id);
            vos = JSONObject.parseObject(JSONObject.toJSONString(basicFuncs), new TypeReference<List<FuncTreegridVo>>() {
            });
            vos.forEach(
                    vo -> {
                        if (!FUNC_LEVEL_FOUR.getId().equals(vo.getFuncLevel())) {
                            vo.setState("closed");
                        } else {
                            vo.setState("open");
                        }
                        vo.setFuncLevelName(EnumUtil.getNameById("FuncLevel", vo.getFuncLevel()));
                        vo.setFuncTypeName(EnumUtil.getNameById("FuncType", vo.getFuncType()));
                    }
            );
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vos);
    }


    @Override
    public ResponseJson loadFuncCombotree(JSONObject jsonObject) throws Exception {
        String funcLevel = jsonObject.getString("funcLevel");
        String parentId = jsonObject.getString("parentId");
        FuncCombotreeVo vo = new FuncCombotreeVo();
        vo.setId(parentId);
        vo = getComboTreeVo(vo, funcLevel);
        List<?> children = vo.getChildren();
        if (null == children || children.size() <= 0) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "请先添加上级角色");
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo.getChildren());
    }


    @Override
    public ResponseJson loadFuncTree() throws Exception {
        List<FuncTreeVo> list = new ArrayList<>();
        List<Map<String, String>> maps = EnumUtil.listDictByDictName("ProjectType");
        maps.forEach(
                map -> {
                    FuncTreeVo vo = new FuncTreeVo();
                    vo.setId(map.get("id"));
                    vo = getTreeVo(vo);
                    List<FuncTreeVo> children = (List<FuncTreeVo>) vo.getChildren();
                    if (null != children && children.size() > 0) {
                        list.addAll(children);
                    }
                }
        );
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }


    /**
     * 递归查询下拉树
     *
     * @author taoye
     */
    private FuncCombotreeVo getComboTreeVo(FuncCombotreeVo vo, String funcLevel) {
        if (funcLevel.equals(vo.getFuncLevel())) {
            return vo;
        }
        List<BasicFunc> basicFuncs = basicFuncDao.listBasicFuncByProjectTypeAndParentIdFromRedis(PROJECT_TYPE_MIS.getId(), vo.getId());
        if (basicFuncs.size() <= 0) {
            return vo;
        }
        List<FuncCombotreeVo> funcCombotreeVos = JSONObject.parseObject(JSONObject.toJSONString(basicFuncs), new TypeReference<List<FuncCombotreeVo>>() {
        });
        for (FuncCombotreeVo funcCombotreeVo : funcCombotreeVos) {
            getComboTreeVo(funcCombotreeVo, funcLevel);
        }
        vo.setChildren(funcCombotreeVos);
        return vo;
    }


    /**
     * 递归查询树
     *
     * @author taoye
     */
    private FuncTreeVo getTreeVo(FuncTreeVo vo) {
        List<BasicFunc> basicFuncs = basicFuncDao.listBasicFuncByProjectTypeAndParentIdFromRedis(PROJECT_TYPE_MIS.getId(), vo.getId());
        if (basicFuncs.size() <= 0) {
            vo.setState("open");
            return vo;
        }
        List<FuncTreeVo> funcTreeVos = JSONObject.parseObject(JSONObject.toJSONString(basicFuncs), new TypeReference<List<FuncTreeVo>>() {
        });
        for (FuncTreeVo funcTreeVo : funcTreeVos) {
            getTreeVo(funcTreeVo);
        }
        vo.setChildren(funcTreeVos);
        vo.setState("closed");
        return vo;
    }


    /**
     * 缓存处理
     *
     * @author taoye
     */
    private void redisRefresh(String id) {
        BasicRoleRight basicRoleRight = new BasicRoleRight();
        basicRoleRight.setRightId(id);
        List<BasicRoleRight> list = basicRoleRightDao.listBasicRoleRightFromMysql(basicRoleRight);
        Map<String, String> roleIdMap = new HashMap<>();
        list.forEach(
                brr -> roleIdMap.put(brr.getRoleId(), brr.getRoleId())
        );
        Map<String, String> loginNameMap = new HashMap<>();
        roleIdMap.forEach(
                (k, v) -> {
                    List<BasicUserRole> burs = basicUserRoleDao.listBasicUserRoleByRoleIdFromRedis(v);
                    burs.forEach(
                            bur -> {
                                User user = userDao.getUserByIdFromRedis(bur.getUserId());
                                loginNameMap.put(user.getLoginName(), user.getLoginName());
                            }
                    );
                }
        );
        loginNameMap.forEach(
                (k, v) -> redisService.del(REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME, v)
        );
    }
}