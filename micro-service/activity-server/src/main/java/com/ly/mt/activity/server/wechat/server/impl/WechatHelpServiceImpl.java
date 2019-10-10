package com.ly.mt.activity.server.wechat.server.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.server.config.ActivitySMSService;
import com.ly.mt.activity.server.wechat.mapper.*;
import com.ly.mt.activity.server.wechat.server.WechatHelpService;
import com.ly.mt.core.common.constant.FilePathEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.entity.hd.*;
import com.ly.mt.core.common.entity.hd.Rule.RuleCompareResult;
import com.ly.mt.core.common.entity.hd.dto.*;
import com.ly.mt.core.common.entity.hd.model.HdSmsMsg;
import com.ly.mt.core.common.entity.hd.request.*;
import com.ly.mt.core.common.entity.hd.vo.WechatHelpUserSimpVo;
import com.ly.mt.core.common.entity.hd.vo.WechatHelpUserVo;
import com.ly.mt.core.common.entity.hd.vo.WechatSimpMsgVo;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.BarCodeUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.common.util.WechatRuleUtil;
import com.ly.mt.core.oss.OssServer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author panjingtian
 * @description 微信助力活动操作
 * @date 2019/8/13 10:42 PM
 */
//@Slf4j
@Service
public class WechatHelpServiceImpl implements WechatHelpService {

    private static final Logger log = LoggerFactory.getLogger(WechatHelpServiceImpl.class);

    //设置目前过期时间为1年
    private final static Date EXPIRE_DATA = new Date(System.currentTimeMillis() + 1 * 365 * 24 * 3600 * 1000);


    /**
     * 验证码过期时间  单位 /秒
     */
    private final static long EXPIRATION_SECONDS = 90l;

    /**
     * type为用户助力
     */
    private static final String HUMAN_COUNT_RULE_TYPE = "1-1";
    private static final String COMMIT_MOTI_PICTURE = "2-2";

    /**
     * 用户活动主进行中状态
     */
    private static final String WECHAT_HELP_MASTER_TAST_STATUS_RUNNING = "0";

    /**
     * 用户活动子任务进行中
     */
    private static final String WECHAT_HELP_SUB_TAST_STATUS_RUNNING = "0";


    @Resource
    private WechatHelpMasterMapper helpMasterMapper;

    @Resource
    private WechatHelpSubMapper helpSubMapper;

    @Resource
    private ActivityTaskMasterMapper taskMasterMapper;

    @Resource
    private ActivityTaskSubMapper taskSubMapper;

    @Resource
    private WechatUserMapper wechatUserMapper;

    @Resource
    private OssServer ossServer;

    @Resource
    private ActivitySMSService activitySMSService;

    @Autowired
    private RedisServer redisServer;

    @Resource
    private WechatHelpMasterMapper weChatHelperMasterMapper;
    @Resource
    private WechatHelpSubMapper wechatHelpSubMapper;

    @Resource
    private ActivityCouponCodeMapper couponCodeMapper;

    @Resource
    private ActivityCouponCodeToUserMapper toUserMapper;

    @Override
    public JSONObject addWechatHelpuser(JSONObject jsonObject) {
        try {
            WechatHelpUserRequestBody body = JSONObject.toJavaObject(jsonObject, WechatHelpUserRequestBody.class);
            List<WechatHelpMaster> masters = helpMasterMapper.findByActivityIdAndWechatId(body.getActivityId(), body.getWechatId());
            if (masters.size() > 0 && !String.valueOf(masters.get(0).getTaskId()).equals("null")) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_REGIST_EXIST);
            } else {

                WechatHelpMaster master = new WechatHelpMaster();
                BeanUtils.copyProperties(body, master);
                Long id = Long.valueOf(StringUtil.getRandomIntByLength(10));
                master.setId(id);
                master.setTaskStatus(WECHAT_HELP_MASTER_TAST_STATUS_RUNNING);
                //TODO 邀请别人的url，进行组装，然后oss进行数据组装
                InputStream activityImage = BarCodeUtil.createBarcode(body.getShareUrl());
                String ossImageUrl = ossServer.upload(activityImage, FilePathEnum.FILE_PATH_ACTIVITY, EXPIRE_DATA);
                master.setShareOssUrl(ossImageUrl);
                master.setCreateTime(new Date());
                master.setCreateTime(new Date());
                int flag = 0;
                int flag2 = 0;
                if (masters.size() == 1 && (String.valueOf(masters.get(0).getTaskId()).equals("null"))) {
                    //更新
                    flag = helpMasterMapper.updateTaskById(master);
                    flag2 = flag;
                } else if (masters.size() == 0) {
                    //新建插入
                    flag = helpMasterMapper.insertSelective(master);
                }
                if (flag > 0) {
                    List<ActivityTaskSub> taskSubs = taskSubMapper.findByTaskMasterId(body.getTaskId());
                    Integer finalFlag = flag2;
                    taskSubs.forEach(t -> {
                        WechatHelpSub helpSub = new WechatHelpSub();
                        helpSub.setId(Long.valueOf(StringUtil.getRandomIntByLength(10)));
                        helpSub.setTaskId(body.getTaskId());
                        helpSub.setTaskSubId(t.getId());
                        if (finalFlag > 0) {
                            helpSub.setHelpMasterId(masters.get(0).getId());
                        } else {
                            helpSub.setHelpMasterId(id);
                        }
                        helpSub.setStatus(WECHAT_HELP_SUB_TAST_STATUS_RUNNING);
                        helpSub.setCreateTime(new Date());
                        helpSub.setUpdateTime(new Date());
                        helpSub.setContent(t.getSubRule());
                        helpSubMapper.insertSelective(helpSub);
                    });
                }
                JSONObject json = new JSONObject(1);
                json.put("helpMasterId", id);
                return JsonUtil.getSuccessJson(json);

            }
        } catch (Exception e) {
            log.error("addWechatHelpuser,添加用，e={}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ADD_ERROR);
        }
    }


    /**
     * 1、包装助力
     * 先查询出主体用户
     * 查询出子规则然后便利判断条件
     * 查询出他的点赞用户进行包装
     * <p>
     * 2、校验任务
     * 校验任务数量
     * 校验上传图片
     */
    @Override
    public JSONObject findWechatHelpuser(JSONObject jsonObject) {
        //查询微信助力用户信息 openId作为条件，包装有微信助力的用户信息及其任务完成信息
        BaseWechatHelpReauesyBody body = JSONObject.toJavaObject(jsonObject, BaseWechatHelpReauesyBody.class);
        WechatHelpUserVo vo = new WechatHelpUserVo();
        WechatHelpUserSimpVo userMsg = new WechatHelpUserSimpVo();

        //包装用户信息
        List<WechatHelpMasterMsgDto> masterMsgs = helpMasterMapper.findByActivityIdAndWechatIdToMsg(body.getActivityId(), body.getWechatId());

        if (masterMsgs.size() > 1 || masterMsgs.size() < 1) {
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_FIND_ERROR);
        }
        WechatHelpMasterMsgDto msgDto = masterMsgs.get(0);
        userMsg.setWechatHeadeImgUrl(msgDto.getWechatHeadeImgUrl());
        userMsg.setWechatNickname(msgDto.getWechatNickname());
        userMsg.setShareOssUrl(msgDto.getShareOssUrl());
        userMsg.setShareUrl(msgDto.getShareUrl());
        userMsg.setTaskStatus(msgDto.getTaskStatus());
        vo.setUserMsg(userMsg);

        //包装用户助力集合
        List<WechatSimpMsgVo> wechatVos = new ArrayList<>();
        wechatVos = wechatUserMapper.findByActivityIdAndWechatMasterId(body.getActivityId(), msgDto.getId());
        vo.setWechatSubs(wechatVos);

        //包装规则进度
        WechatHelpSubRuleDto taskDto = new WechatHelpSubRuleDto();
        List<WechatHelpSub> helpSubs = helpSubMapper.findByMasterId(masterMsgs.get(0).getId());
        if (helpSubs.size() < 1) {
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SELECT_HELP_EXIST);
        }
        taskDto.setTaskId(helpSubs.get(0).getTaskId());
        ActivityTaskMasterDto byId = taskMasterMapper.findById(helpSubs.get(0).getTaskId());
        taskDto.setTaskName(byId.getTaskName());
        List<WechatHelpSubContentDto> subContentDtos = new ArrayList<>(6);
        taskSubMapper.findByTaskMasterId(helpSubs.get(0).getTaskId()).forEach(n -> {
            helpSubs.forEach(h -> {
                if (h.getTaskSubId() == n.getId()) {
                    WechatHelpSubContentDto dto = new WechatHelpSubContentDto();
                    dto.setSubTaskId(h.getTaskSubId());
                    dto.setSubTaskName(n.getSubName());
                    dto.setStatus(h.getStatus());
                    dto.setContent(h.getContent());
                    subContentDtos.add(dto);
                }
            });
        });

        taskDto.setTaskContents(subContentDtos);
        vo.setTask(taskDto);
        return JsonUtil.getSuccessJson(vo);
    }

    @Override
    public JSONObject updateWechatHelp(JSONObject jsonObject) {
        return null;
    }

    /**
     * 1，先查询是不是注册过用户且masterId不为空
     * 2、没有注册的话进行助力，已经注册返回已经给别人助力了
     * private Long activityId;
     * private Long masterId;
     * private Long wechatId;
     * private String openId;
     */
    @Override
    public JSONObject doHelp(JSONObject jsonObject) {
        DoHelpRequestBody body = JSONObject.toJavaObject(jsonObject, DoHelpRequestBody.class);
        long start = System.currentTimeMillis();

        //查询自己是否是注册用户
        List<WechatHelpMaster> sourMaster = helpMasterMapper.findByActivityIdAndWechatId(body.getActivityId(), body.getWechatId());
        log.info("sorMaster={}", JSON.toJSONString(sourMaster));
        if (sourMaster != null) {
            if (sourMaster.size() > 0) {

                if (!String.valueOf(sourMaster.get(0).getWechatMasterId()).equals("null") &&
                        !String.valueOf(sourMaster.get(0).getWechatMasterId()).equals("NULL") &&
                        !String.valueOf(sourMaster.get(0).getWechatMasterId()).equals("0")) {
                    return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_HELP_EXIST);
                }
            }
        }

        Long id = Long.valueOf(StringUtil.getRandomIntByLength(10));

        WechatHelpMaster wechatHelpMaster2 = new WechatHelpMaster();

        if (sourMaster.size() == 0) {
            wechatHelpMaster2.setId(id);
            wechatHelpMaster2.setOpenId(body.getOpenId());
            wechatHelpMaster2.setWechatId(body.getWechatId());
            wechatHelpMaster2.setActivityId(body.getActivityId());
            wechatHelpMaster2.setWechatMasterId(body.getMasterId());
            wechatHelpMaster2.setCreateTime(new Date());
            wechatHelpMaster2.setUpdateTime(new Date());

            helpMasterMapper.insertSelective(wechatHelpMaster2);
        } else {
            helpMasterMapper.updateWechatMasterId(sourMaster.get(0).getId(), body.getMasterId());
        }

        RuleCompareResult compareResult = null;
        try {
            List<WechatHelpSub> weChatHelpSubs = wechatHelpSubMapper.findByMasterId(body.getMasterId());
            boolean flag = false;

            //v2
            List<Boolean> listFlag = new ArrayList<>(6);
            listFlag = getFlagList(listFlag,weChatHelpSubs,HUMAN_COUNT_RULE_TYPE);

            for (WechatHelpSub x : weChatHelpSubs) {
                if (HUMAN_COUNT_RULE_TYPE.equals(JSON.parseObject(x.getContent()).getString("ruleType"))) {
                    flag = dealHandler(x, jsonObject);
                    //listFlag.add(flag);
                }
            }

            log.info("方法外部+" + flag);
            //v2
            listFlag.add(flag);
            List<Boolean> collect = listFlag.stream().filter(l -> l == false).collect(Collectors.toList());
            if (collect.size() > 0) {
                flag = false;
            }

            //V3,不绑定券的问题
            Thread.sleep(200);
            List<WechatHelpSub> weChatHelpSubs2 = wechatHelpSubMapper.findByMasterId(body.getMasterId());
            List<String> v3Flag = weChatHelpSubs2.stream().map(WechatHelpSub::getStatus).collect(Collectors.toList());
            boolean contains = v3Flag.contains("0");
            if (contains) {
                flag = false;
            } else {
                flag = true;
            }

            WechatHelpMaster wechatHelpMaster = weChatHelperMasterMapper.selectByWeChatId(body.getWechatId());//selectByPrimaryKey(wechatHelpMaster2.getId());

            if (flag) {
                wechatHelpMaster.setTaskStatus(1 + "");
                //weChatHelperMasterMapper.updateByPrimaryKeySelective(wechatHelpMaster);
                weChatHelperMasterMapper.updateStatus(wechatHelpMaster.getId(), "1");
                //进行优惠码绑定，
                    List<ActivityCouponCodeToUser> sss = toUserMapper.findByUserIdActivityId(body.getActivityId(), wechatHelpMaster.getId());
                    if (sss.size() < 1) {
                        ActivityCouponCode couponCode = couponCodeMapper.findRandomByStatusOk();
                        ActivityCouponCodeToUser toUser = new ActivityCouponCodeToUser();
                        toUser.setCode(couponCode.getCode());
                        toUser.setCouponId(couponCode.getId());
                        toUser.setOpenId(wechatHelpMaster.getOpenId());
                        toUser.setUserId(wechatHelpMaster.getId());
                        toUser.setActivityid(body.getActivityId());
                        toUserMapper.insertSelective(toUser);
                        couponCode.setStatus("0");
                        couponCodeMapper.updateByPrimaryKey(couponCode);
                    }
            }
            long end = System.currentTimeMillis();
            log.info("call method doHelp of WechatHelpServiceImpl cost {} mills", (end - start));
        } catch (Exception e) {
            log.error("call method doHelp of WeChatHelpServiceImpl has errors, the errors message is {}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR);
        }
        log.info("doHelp result jsonObject={}", JSON.toJSONString(compareResult));
        return JsonUtil.getSuccessJson(compareResult);
    }


    /**
     * 返回具体用户任务状态集合
     * @param source 空集合
     * @param subs 用户任务完成程度
     * @param type 当前任务类型 目前写死，后期迭代成配置话在做灵活配置
     * @return
     */
    private List<Boolean> getFlagList(List<Boolean> source ,  List<WechatHelpSub> subs , String type){
        subs.stream().map(w -> {
            JSONObject jsonObject1 = JSONObject.parseObject(w.getContent());
            String ruleType = jsonObject1.getString("ruleType");
            if (!ruleType.equals(type)) {
                source.add(true);
                return Boolean.valueOf(jsonObject1.getString("status"));
            }
            return true;
        }).collect(Collectors.toList());
        return source;
    }

    @Override
    public JSONObject motiPicCommit(JSONObject jsonObject) {
        log.info("motiPicCommit jsonParamerter={}", jsonObject.toJSONString());
        long start = System.currentTimeMillis();
        String picUrl = jsonObject.getString("picUrl");
        long weChatHelpId = jsonObject.getLongValue("weChatHelpId");
        RuleCompareResult compare = null;
        try {
            boolean flag = false;

            List<WechatHelpSub> weChatHelpSubs = wechatHelpSubMapper.findByMasterId(weChatHelpId);

            //v2
            List<Boolean> listFlag = new ArrayList<>(6);
            listFlag = getFlagList(listFlag,weChatHelpSubs,COMMIT_MOTI_PICTURE);
            List<WechatHelpSub> wechatHelpSubs = wechatHelpSubMapper.findByMasterId(weChatHelpId);
            for (WechatHelpSub wechatHelpSub : wechatHelpSubs) {
                if (COMMIT_MOTI_PICTURE.equals(JSON.parseObject(wechatHelpSub.getContent()).getString("ruleType"))) {
                    flag = dealHandler(wechatHelpSub, jsonObject);
                }
            }

            log.info("方法外部+" + flag);

            //v2
            listFlag.add(flag);
            List<Boolean> collect = listFlag.stream().filter(l -> l == false).collect(Collectors.toList());
            if (collect.size() > 0) {
                flag = false;
            }

            //if (flag) {
            WechatHelpMaster wechatHelpMaster = weChatHelperMasterMapper.selectByPrimaryKey(weChatHelpId);
            wechatHelpMaster.setTaskStatus(1 + "");
            weChatHelperMasterMapper.updateStatus(weChatHelpId, "1");
            //weChatHelperMasterMapper.updateByPrimaryKeySelective(wechatHelpMaster);
            //TODO 添加优惠券
            ActivityCouponCode couponCode = couponCodeMapper.findRandomByStatusOk();
            ActivityCouponCodeToUser toUser = new ActivityCouponCodeToUser();
            toUser.setCode(couponCode.getCode());
            toUser.setCouponId(couponCode.getId());
            toUser.setOpenId(wechatHelpMaster.getOpenId());
            toUser.setUserId(wechatHelpMaster.getId());
            toUser.setActivityid(423784446l);
            toUserMapper.insertSelective(toUser);

            couponCode.setStatus("0");
            couponCodeMapper.updateByPrimaryKey(couponCode);
            //}
            long end = System.currentTimeMillis();
            log.info("call method motiPicCommit cost {} mills", (end - start));
        } catch (Exception e) {
            log.error("call method motiPicCommit has some errors , the error message is {}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR);
        }
        return JsonUtil.getSuccessJson();
    }

    private Boolean dealHandler(WechatHelpSub wechatHelpSub, JSONObject parameter) {
        boolean flag = false;
        JSONObject content = JSON.parseObject(wechatHelpSub.getContent());
        RuleCompareResult compare = null;
        flag = false;
        if (StringUtils.equals(COMMIT_MOTI_PICTURE, content.getString("ruleType"))) {
            content.put("imageUrl", parameter.getString("picUrl"));
            content.put("status", 1);
            wechatHelpSub.setContent(content.toJSONString());
            wechatHelpSub.setStatus(1 + "");
            wechatHelpSubMapper.updateByPrimaryKeySelective(wechatHelpSub);
            flag = true;
        } else if (StringUtils.equals(HUMAN_COUNT_RULE_TYPE, content.getString("ruleType"))) {
            Integer.valueOf(content.getString("countCondition"));
            Integer.valueOf(content.getString("countData"));
            content.put("countData", content.getInteger("countData") + 1);
            compare = WechatRuleUtil.compare(content.toJSONString());
            if (compare.getFlag()) {
                flag = true;
                content.put("status", 1);
                wechatHelpSub.setContent(content.toJSONString());
                wechatHelpSub.setStatus(1 + "");
                wechatHelpSubMapper.updateByPrimaryKeySelective(wechatHelpSub);
                flag = true;
            } else {
                wechatHelpSub.setContent(content.toJSONString());
                wechatHelpSubMapper.updateByPrimaryKeySelective(wechatHelpSub);
                flag = false;
            }
        }
        return flag;
    }


    @Override
    public JSONObject lookHelpUserStatus(JSONObject jsonObject) {
        BaseWechatHelpReauesyBody body = JSONObject.toJavaObject(jsonObject, BaseWechatHelpReauesyBody.class);
        List<WechatHelpMaster> masters = helpMasterMapper.findByActivityIdAndWechatId(body.getActivityId(), body.getWechatId());
        if (masters.size() > 0) {
            return JsonUtil.getSuccessJson(masters.get(0));
        }
        return JsonUtil.getSuccessJson();
    }


    @Override
    public JSONObject findUserCouponCode(JSONObject jsonObject) {
        BaseWechatHelpReauesyBody body = JSONObject.toJavaObject(jsonObject, BaseWechatHelpReauesyBody.class);


        List<ActivityCouponCodeToUser> codes = toUserMapper.findByUserIdActivityId(body.getActivityId(), body.getWechatId());
        if (codes.size() > 0) {
            JSONObject vo = new JSONObject(1);
            vo.put("couponCode", codes.get(0).getCode());
            return JsonUtil.getSuccessJson(vo);
        }
        return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_COUPON_CODE_NO_EXIST);
    }

    /**
     * 验证手机号 更新助力用户手机号
     *
     * @param jsonObject 手机号，验证码，
     * @return
     */
    @Override
    public JSONObject updatePhone(JSONObject jsonObject) {
        //验证码
        try {
            PhoneCodeRequestBody body = JSONObject.toJavaObject(jsonObject, PhoneCodeRequestBody.class);
            String dynamicCode = body.getCode();
            String redisDynamicCode = redisServer.getVauel(RedisEnum.ACTIVITY_BUYER_ORDER_CODE.getKey() + body.getPhone());
            if (org.springframework.util.StringUtils.isEmpty(redisDynamicCode) || !redisDynamicCode.equals(dynamicCode)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_PHONE_CODE_NO_EXIST);
            }

            int result = helpMasterMapper.updateByActivityIdAndWechatId(body.getPhone(), body.getActivityId(), body.getWechatId());
            return JsonUtil.getSuccessJson();
        } catch (Exception e) {
            log.error("updatePhone，e={}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_UPDATE_ERROR);
        }
    }


    @Override
    public JSONObject findUserCount(JSONObject jsonObject) {
        Long count = wechatUserMapper.findUserCount();
        JSONObject jsonObject1 = new JSONObject(1);
        jsonObject1.put("count", count + 10984l);
        return JsonUtil.getSuccessJson(jsonObject1);
    }

    @Override
    public JSONObject sendCode(JSONObject jsonObject) {
        HdSmsMsg hdSmsMsg = new HdSmsMsg();
        try {
            UserSendCOdeRequestDto dto = JSONObject.toJavaObject(jsonObject, UserSendCOdeRequestDto.class);
            String dynamicCode = StringUtil.getRandomIntByLength(6);
            hdSmsMsg = activitySMSService.sendDynamicCode(dto.getPhone(), SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, dynamicCode, EXPIRATION_SECONDS);
            redisServer.setExString(RedisEnum.ACTIVITY_BUYER_ORDER_CODE.getKey() + dto.getPhone(), dynamicCode, EXPIRATION_SECONDS);
        } catch (Exception e) {
            log.error("发送失败，e={}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR);
        }
        return JsonUtil.getSuccessJson(hdSmsMsg);
    }

    @Override
    public JSONObject findCodeByopenId(JSONObject jsonObject) {
        try {
            QueryCodeRequestBody body = JSONObject.toJavaObject(jsonObject, QueryCodeRequestBody.class);
            List<String> list = toUserMapper.findByActivityOpenid(body.getActivityId(), body.getOpenId());
            if (list.size()>0){
                return JsonUtil.getSuccessJson(list.get(0));
            }
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_COUPON_CODE_NO_EXIST);
        } catch (Exception e) {
            log.error("查询兑换码异常，e={}",e);
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR);
        }
    }
}
