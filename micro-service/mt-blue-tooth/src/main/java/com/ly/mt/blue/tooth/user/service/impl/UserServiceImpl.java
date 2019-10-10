package com.ly.mt.blue.tooth.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.dict.CouponInfoEnum;
import com.ly.mt.blue.tooth.base.dict.UserRegisterStatusEnum;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.base.token.service.impl.TokenServiceImpl;
import com.ly.mt.blue.tooth.base.util.JsonUtils;
import com.ly.mt.blue.tooth.log.vo.BluetoothLogChartVo;
import com.ly.mt.blue.tooth.subsidary.vo.SubsidaryVo;
import com.ly.mt.blue.tooth.user.service.UserService;
import com.ly.mt.blue.tooth.user.vo.*;
import com.ly.mt.core.base.dict.CouponUseScope;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.dict.UserCertifacationStatus;
import com.ly.mt.core.base.dict.UserStatus;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.entity.YHHDHttpResponse.TripartiteIdCardResultDto;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.feign.ThirdCenterMethod;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ly.mt.blue.tooth.base.dict.CouponInfoEnum.*;
import static com.ly.mt.blue.tooth.base.dict.TokenEnum.TOKEN_EXPIRE;
import static com.ly.mt.blue.tooth.base.util.CalcUntils.calcAvgSmokingMouth;
import static com.ly.mt.blue.tooth.base.util.CalcUntils.calcTotalSaveMoney;
import static com.ly.mt.core.base.dict.AlOssPath.*;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_COUPON_INFO;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_BLUETOOTH;
import static com.ly.mt.core.base.dict.ThipartiteCardCode.THIPARTITE_CARD_CODE_200;
import static com.ly.mt.core.base.dict.ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_1;
import static com.ly.mt.core.base.dict.UserCertifacationStatus.USER_CERTIFICATION_NOT;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.*;


@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TokenServiceImpl tokenService;

    /**
     * 蓝牙-用户注册
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson buletoothRegister(String loginName, String password, String dynamicCode) throws Exception {
        // 校验动态验证码
        String redisCode = (String) redisService.get(REDIS_CODE_BLUETOOTH_REGIST, loginName);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效！");
        }
        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
        }
        //通过登录名是否注册
        JSONObject jsonLoginName = new JSONObject();
        jsonLoginName.put("login_name", loginName);
        //查询用户是否注册 验证码是否正确
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonLoginName);
        List<BlueToothUserVo> list = JSONObject.parseObject(result, new TypeReference<List<BlueToothUserVo>>() {
        });
        //判断用户是否注册
        if (!list.isEmpty()) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已注册,不可重复注册!");
        }
        String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("quick_type", QUICK_TYPE_BLUETOOTH.getId());
        jsonObject.put("login_name", loginName);
        jsonObject.put("mobile", loginName);
        jsonObject.put("create_time", DateUtil.getNowTimeStr());
        jsonObject.put("id", id);
        jsonObject.put("password", MD5Util.md5(password));//数据加密
        jsonObject.put("user_status", UserStatus.USER_STATUS_USING.getId());
        String result2 = callDataCenter(USER_INSERT_USER, jsonObject);
        LOGGER.info("蓝牙APP-注册,开始生成token入库");
        String token = MD5Util.md5(DateUtil.getNowTimeStr() + password).toLowerCase();
        tokenService.insert(id, token, TOKEN_EXPIRE.getValue());
        LOGGER.info("蓝牙APP-注册,结束生成token入库");
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "注册成功!");
    }

    /**
     * 蓝牙APP-重置密码
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson resetPwd(String mobile, String dynamicCode, String password) throws Exception {
        //通过手机号验证是否注册
        JSONObject jsonLoginName = new JSONObject();
        jsonLoginName.put("login_name", mobile);
        //查询用户是否注册 验证码是否正确
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonLoginName);
        List<BlueToothUserVo> list = JSONObject.parseObject(result, new TypeReference<List<BlueToothUserVo>>() {
        });
        //判断用户是否注册
        if (list.isEmpty()) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "该手机号未注册!");
        } else {
            // 校验动态验证码
            String redisCode = (String) redisService.get(REDIS_CODE_BLUETOOTH_PASSWORD, mobile);
            LOGGER.info("蓝牙APP-重置密码,发送验证为userId=", redisCode);
            if (StringUtil.isEmpty(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效！");
            }
            if (!dynamicCode.equals(redisCode)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
            }
            BlueToothUserVo blueToothUserVo = list.get(0);
            LOGGER.info("蓝牙APP-重置密码,userId=", blueToothUserVo.getId());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("password", MD5Util.md5(password));
            jsonObject.put("id", blueToothUserVo.getId());
            String result2 = callDataCenter(USER_UPDATE_USER, jsonObject);

        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "重置密码成功!");
    }

    /**
     * 验证登录名是否被注册
     *
     * @throws Exception
     */
    @Override
    public ResponseJson checkUserLoginName(String loginName) throws Exception {
        // 调用service接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_name", loginName);
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonObject);
        BlueToothUserStatus blueToothUserStatus = new BlueToothUserStatus();
        blueToothUserStatus.setStatus(UserRegisterStatusEnum.UNREGISTER.getId());//未注册
        List<BlueToothUserVo> maps = JSONObject.parseObject(result, List.class);
        if (!maps.isEmpty()) {
            blueToothUserStatus.setStatus(UserRegisterStatusEnum.REGISTER.getId());//已注册
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, blueToothUserStatus);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, blueToothUserStatus);
    }


    /**
     * 修改用户信息
     *
     * @throws Exception
     */
    @Override
    public ResponseJson modifyUserInfo(String nickName, String sex, String birthday) throws Exception {
        String id = getLoginUserId();
        LOGGER.info("修改用户信息BlueToothUserid=" + id);
        if (StringUtil.isEmpty(id)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nick_name", nickName);
        jsonObject.put("sex", sex);
        jsonObject.put("birthday", birthday);
        jsonObject.put("id", id);
        String result = callDataCenter(USER_UPDATE_USER, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "");
    }

    /**
     * 修改用户密码
     *
     * @throws Exception
     */
    @Override
    public ResponseJson modifyPassword(String password) throws Exception {
        String id = getLoginUserId();
        LOGGER.info("修改用户密码BlueToothUserid=", id);
        if (StringUtil.isEmpty(id)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", MD5Util.md5(password));
        jsonObject.put("id", id);
        String result = callDataCenter(USER_UPDATE_USER, jsonObject);
        LOGGER.info("蓝牙APP-修改密码开始更新token入库");
        String token = MD5Util.md5(DateUtil.getNowTimeStr() + password).toLowerCase();
        int i = tokenService.updateToken(id, token);
        LOGGER.info("蓝牙APP-修改密码结束更新token入库，结果{}", i);

        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "密码修改成功");
    }

    /**
     * @Description 头像修改
     * @Author whl
     */
    @Override
    public ResponseJson uploadAvatarPic(MultipartFile file) throws Exception {
        String url = callThirdCenter(file, AL_OSS_PATH_IMAGE_BLUETOOTH_AVATAR.getPath());
        if (StringUtil.isEmpty(getLoginUserId())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getLoginUserId());
        jsonObject.put("avatar_pic_src", url);
        callDataCenter(USER_UPDATE_USER, jsonObject);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, url);
    }

    /**
     * @Description 加载用户信息
     * @Author whl
     */
    @Override
    public ResponseJson loadUserInfo() throws Exception {
        String userId = getLoginUserId();
        // 查询用户信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userId);
        String userJson = callDataCenter(USER_GET_USER_BY_ID, jsonObject);
        if (StringUtil.isEmpty(userJson)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户不存在!");
        }
        BlueToothUserVo user = JSONObject.toJavaObject(JSONObject.parseObject(userJson), BlueToothUserVo.class);
        user.setBirthday(DateUtil.timeFormat(user.getBirthday()));//格式化日期
        user.setHexadecimalUserId(JsonUtils.toHexInt(userId));//十六进制userId/绑定时传给蓝牙产商
        //下面查询目标设定
        JSONObject targetObeject = new JSONObject();
        targetObeject.put("user_id", userId);
        //获取用户目标设定
        String targetJson = callDataCenter(BLUETOOTH_SUBSIDARY_GET, targetObeject);
        SubsidaryVo subsidaryVo = JSONObject.toJavaObject(JSONObject.parseObject(targetJson), SubsidaryVo.class);
        if (subsidaryVo == null) {
            user.setComplianceDaysTarget(0);//达标天数 默认0
            user.setSmokingTarget(0);//抽烟不高于默认0
        } else {
            user.setComplianceDaysTarget(subsidaryVo.getComplianceDaysTarget());//达标天数
            user.setSmokingTarget(subsidaryVo.getSmokingTarget());//抽烟不高于
        }
        //计算用户抽烟总口数
        String totalReulst = callDataCenter(BLUETOOTH_LOG_SMOKING_MONTH_NUMBER_TOTAL, targetObeject); //统计用户抽烟总数
        int totalNumberMounth = Integer.parseInt(totalReulst);//总口数
        int userComplianceDays = 0; //用户达标天数
        //统计抽烟口数大于0在按小时统计数据
        if (totalNumberMounth > 0) {
            String dataCountResult = callDataCenter(BLUETOOTH_LOG_SMOKING_HAVE_DATA_DAY_COUNT, targetObeject); //统计有数据的天数
            List<BluetoothLogChartVo> chartList = JSONObject.parseArray(dataCountResult, BluetoothLogChartVo.class);
            int dataCount = chartList.size();
            int avg = calcAvgSmokingMouth(totalNumberMounth, dataCount);//用户日均抽烟口数
            int saveTotal = calcTotalSaveMoney(totalNumberMounth);//累计节省
            user.setAvgSmoking(avg);//日均抽烟口数
            user.setTotalMoney(saveTotal == 0 ? 1 : saveTotal);//累计节省0元
            //用户设置每日抽烟不高于或者达标为0 不去设置用户达标天数
            if (subsidaryVo == null || subsidaryVo.getSmokingTarget() == 0 || subsidaryVo.getComplianceDaysTarget() == 0) {
                user.setUserComplianceDays(0);//用户达标数
            } else {
                for (BluetoothLogChartVo bluetoothLogChartVo : chartList) {
                    if (bluetoothLogChartVo.getY() < subsidaryVo.getSmokingTarget()) {
                        userComplianceDays = userComplianceDays + 1;//累加达标天数
                    }
                }
                user.setUserComplianceDays(userComplianceDays);//用户达标数
            }
        } else {
            user.setAvgSmoking(0);//日均抽烟口述0
            user.setTotalMoney(0);//累计节省0元
            user.setUserComplianceDays(userComplianceDays);//用户达标数
        }
        //查询用户实名状态
        String certificationReulst = callDataCenter(USER_CERTIFACATION_GET, targetObeject);
        BlueToothUserCertficationVo userCertification = JSONObject.toJavaObject(JSONObject.parseObject(certificationReulst), BlueToothUserCertficationVo.class);
        if (userCertification == null) { //未认证
            user.setCertificationStatus(USER_CERTIFICATION_NOT.getId());
        } else {
            user.setCertificationStatus(userCertification.getStatus());
        }
        //查询用户报修状态
        // 查询用户信息
        JSONObject paramObject = new JSONObject();
        paramObject.put("userId", userId);
        String result = callDataCenter(BLUETOOTH_REPAIRS_GET, paramObject);
        if(StringUtil.isEmpty(result)){
            user.setRepairsStatus("3");//未申请
        }else{
            BlueToothRepairsResultVo blueToothRepairsResultVo = JSONObject.parseObject(result,BlueToothRepairsResultVo.class);
            user.setRepairsStatus(blueToothRepairsResultVo.getStatus());
            user.setRepairsId(blueToothRepairsResultVo.getId());
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, user);
    }

    /**
     * 蓝牙-更换手机号/效验验证码是否正确
     *
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson validateCode(String phone, String dynamicCode) throws Exception {
        // 校验动态验证码
        String redisCode = (String) redisService.get(REDIS_CODE_BLUETOOTH_OLD_PHONE, phone);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效！");
        }
        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "验证通过");
    }

    /**
     * 修改用户绑定手机号
     *
     * @throws Exception
     */
    @Override
    public ResponseJson modifyBindPhone(String phone, String dynamicCode) throws Exception {
        String id = getLoginUserId();
        LOGGER.info("修改用户绑定手机号BlueToothUserid=", id);
        // 校验动态验证码
        String redisCode = (String) redisService.get(REDIS_CODE_BLUETOOTH_NEW_PHONE, phone);
        if (StringUtil.isEmpty(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码失效！");
        }
        if (!dynamicCode.equals(redisCode)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "验证码错误!");
        }
        /**
         *手机号是否被注册
         */
        JSONObject jsonLoginName = new JSONObject();
        jsonLoginName.put("login_name", phone);
        //查询用户是否注册 验证码是否正确
        String result = callDataCenter(USER_GET_USER_BY_CONDTIONS, jsonLoginName);
        List<BlueToothUserVo> list = JSONObject.parseObject(result, new TypeReference<List<BlueToothUserVo>>() {
        });
        //判断用户是否注册
        if (!list.isEmpty()) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "该手机号已绑定其它账号！");
        }
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_name", phone);
        jsonObject.put("mobile", phone);
        jsonObject.put("id", id);
        String upResult = callDataCenter(USER_UPDATE_BY_ID, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "更换绑定手机号成功!");
    }

    /**
     * 身份证OCR扫描
     * @param frontImage 正面
     * @param backImage  反面
     * @param frontExt   正面后缀
     * @param backExt    反面后缀
     * @return
     */
    @Override
    public ResponseJson scanIdCard(String frontImage, String backImage, String frontExt, String backExt, MultipartFile[] files) {
        try {
            //扫描身份证正面
            String frontResp = getHttpResponseEntity(frontImage, frontExt);
            //扫描身份证反面
            String backResp = getHttpResponseEntity(backImage, backExt);
            //三方ocr图像识别返回结果(正面结果)
            TripartiteIdCardResultDto tripartiteIdCardResultFrontDto = JSONObject.parseObject(frontResp, TripartiteIdCardResultDto.class);
            //三方ocr图像识别返回结果(反面结果)
            TripartiteIdCardResultDto tripartiteIdCardResultBackDto = JSONObject.parseObject(frontResp, TripartiteIdCardResultDto.class);
            LOGGER.info("蓝牙APP-调用OCR图像识别结果:"+JSONObject.toJSONString(tripartiteIdCardResultFrontDto));
            //正反面都验证成功
           if (THIPARTITE_CARD_CODE_200.getCode().equals(tripartiteIdCardResultFrontDto.getCode())&&THIPARTITE_CARD_CODE_200.getCode().equals(tripartiteIdCardResultBackDto.getCode())) {
                //身份证正面返回结果
                final TripartiteIdCardResultDto.data data = tripartiteIdCardResultFrontDto.getData();
                String[] paths =new String[2];
                paths[0]=AL_OSS_PATH_IMAGE_USER_IDCARD_FRONT.getPath();//正面
                paths[1]=AL_OSS_PATH_IMAGE_USER_IDCARD_BACK.getPath();//反面
                //开始上传身份证正反面
                String uploadResult = callThirdCenter(files,paths);
                if(StringUtil.isEmpty(uploadResult)){
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "蓝牙APP-上传身份证正反面失败");
                }
               List<String> returnUrls = JSONObject.parseObject(uploadResult,new TypeReference<List<String>>(){});
                String fronturl =returnUrls.get(0);
                String backturl =returnUrls.get(1);
               //返回ocr扫描结果
                BlueToothCertficationOcrResultVo blueToothCertficationOcrResultVo = new BlueToothCertficationOcrResultVo();
                blueToothCertficationOcrResultVo.setIdCardFrontUrl(fronturl);
                blueToothCertficationOcrResultVo.setIdCardBackUrl(backturl);
                blueToothCertficationOcrResultVo.setCardName(data.getName());
                blueToothCertficationOcrResultVo.setCardId(data.getIdcard());
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, blueToothCertficationOcrResultVo);
            } else {
                LOGGER.error("调用二代身份证扫描身份证背面返回状态异常");
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "扫描身份证正反面异常!");
            }
        } catch (Exception e) {
            LOGGER.error("调用二代身份证扫描异常: ", e);
        }
        return null;
    }


    /**
     * 调用第三方身份证扫描OCR接口
     *
     * @param ocrImg
     * @param ocrImgExt
     * @return
     * @throws Exception
     */
    private String getHttpResponseEntity(String ocrImg, String ocrImgExt) throws Exception {
        JSONObject object = new JSONObject();
        object.put("ocrImg", ocrImg);
        object.put("ocrImgExt", ocrImgExt);
        return callThirdCenter(ThirdCenterMethod.USER_CERTIFICATION_SCAN_IDCARD, object);
    }
    /**
     * 调用第三方银河互动运营商三要素效验
     * @param json
     * @return
     */
    @Override
    @TxcTransaction(appName = "moti")
    public ResponseJson threeElementCheck(JSONObject json) {
            JSONObject object = new JSONObject();
            object.put("mobile", json.getString("mobile"));
            object.put("card_id", json.getString("idcard"));
            object.put("card_name", json.getString("name"));
            String userStr = callDataCenter(USER_CERTIFACATION_GET_ISCERTIFACATE, object);
            if (StringUtil.isNotEmpty(userStr)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "该身份证已被使用，请使用其它身份证");
            }
            try {
                //开启事务
                String xid = TxcContext.getCurrentXid();

                String birthday = json.getString("idcard").substring(6, 13);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Date birtDate = new Date(formatter.parse(birthday).getTime());
                //年龄
                int age = DateUtil.getAgeByBirth(birtDate);
                LOGGER.info("蓝牙APP-调用云和互动三要素接口入参:"+JSONObject.toJSONString(object));
                //调用第三方
                String result = callThirdCenter(ThirdCenterMethod.USER_CERTIFICATION_THREE_ELEMENT_CHECK, object);
                JSONObject resultJson = JSONObject.parseObject(result);
                String resultCode =String.valueOf(resultJson.get("code"));
                String resultMsg=String.valueOf(resultJson.get("msg"));
                if (!resultCode.equals(THIPARTITE_THREE_CHECK_CODE_1.getCode())) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,resultMsg);
                }
                JSONObject certifacationObject = new JSONObject();
                certifacationObject.put("card_front_url", json.getString("idCardFront"));
                certifacationObject.put("card_reverse_url", json.getString("idCardBack"));
                certifacationObject.put("user_id", getLoginUserId());
                String id = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_CERTIFICATION);
                certifacationObject.put("id", id);
                certifacationObject.put("create_time", DateUtil.getNowTimeStr());
                certifacationObject.put("mobile", json.getString("mobile"));
                certifacationObject.put("card_name", json.getString("name"));
                certifacationObject.put("card_id", json.getString("idcard"));
                certifacationObject.put("status", (age >= 18) ? UserCertifacationStatus.USER_CERTIFICATION_ADULT.getId() :
                        UserCertifacationStatus.USER_CERTIFICATION_YOUNG.getId());
                certifacationObject.put("modify_time", DateUtil.getNowTimeStr());//修改时间
                certifacationObject.put("xid", xid);
                callDataCenter(USER_CERTIFACATION_INSERT, certifacationObject);
                //生成一张优惠券
                JSONObject couponObject = new JSONObject();
                String couponId = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_INFO);
                couponObject.put("id", couponId);
                couponObject.put("coupon_name", COUPON_NAME.getValue());//优惠卷名称
                couponObject.put("start_time", DateUtil.getNowTimeStr());//优惠卷开始时间
                String endTime = DateUtil.date2TimeStr(DateUtil.offsetDate(new Date(),180));
                couponObject.put("end_time", endTime);//优惠卷结束时间
                couponObject.put("validity_day", VALIDITY_DAY.getValue());//优惠卷有效天数
                couponObject.put("denomination", DENOMINATION.getValue());//优惠卷面额
                couponObject.put("limit_type", CouponUseScope.COUPON_FOR_PART_GOODS.getId());//适用于部分商品
                couponObject.put("coupon_use_system", "10");//在到家C中使用
                couponObject.put("coupon_type", "30");//普通优惠券
                couponObject.put("remark", "蓝牙用户认证，优惠券,针对蓝牙烟弹产品");//优惠券描述
                couponObject.put("creater_id", getLoginUserId());//优惠券创建人
                couponObject.put("create_time", DateUtil.getNowTimeStr());//创建时间
                couponObject.put("xid", xid);
                callDataCenter(COUPON_INFO_INSERT, couponObject);
                BlueToothUserCertficationVo blueToothUserCertficationVo = new BlueToothUserCertficationVo();
                blueToothUserCertficationVo.setStatus((age >= 18) ? UserCertifacationStatus.USER_CERTIFICATION_ADULT.getId() :
                        UserCertifacationStatus.USER_CERTIFICATION_YOUNG.getId());
                blueToothUserCertficationVo.setCouponId(couponId);//优惠卷id
                blueToothUserCertficationVo.setCouponName(COUPON_NAME.getValue());//优惠卷名称
                blueToothUserCertficationVo.setDenomination(DENOMINATION.getValue());//优惠卷面额
                blueToothUserCertficationVo.setValidityDay(endTime);//有效期
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, blueToothUserCertficationVo);
            } catch (Exception e) {
                LOGGER.error("蓝牙APP-调用三要素检测异常: ", e);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
    }

}