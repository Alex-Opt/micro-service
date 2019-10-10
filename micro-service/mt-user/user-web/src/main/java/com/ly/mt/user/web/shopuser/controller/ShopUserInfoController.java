package com.ly.mt.user.web.shopuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.FilePathEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.basic.BasicFileVo;
import com.ly.mt.core.common.entity.shop.ShopInfoVo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import static com.ly.mt.core.common.method.UserMethodEnum.*;

@Api(description = "b端用户信息操作接口")
@RestController
@RequestMapping("/user/shopUser/info")
public class ShopUserInfoController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserInfoController.class);

    @ApiOperation("b端基本信息补全")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendLon",value = "发货地址经度",paramType = "query",required = true),
            @ApiImplicitParam(name = "sendLat",value = "发货地址纬度",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendAddress",value = "发货地址",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendProvinceCode",value = "发货地址省编码",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendProvinceName",value = "发货地址省名称",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendCityCode",value = "发货地址市编码",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendCityName",value = "发货地址市名称",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendDistrictCode",value = "发货地址县区代码",paramType = "insert",required = true),
            @ApiImplicitParam(name = "sendDistrictName",value = "发货地址县区名称",paramType = "insert",required = true),
            @ApiImplicitParam(name = "shopType",value = "店家角色{0:实体店主,1:实体店员,2:电商店主,3:共享烟民}",paramType = "insert",required = true),
            @ApiImplicitParam(name = "shopName",value = "店铺名称",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopLon",value = "店铺地址经度",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopLat",value = "店铺地址纬度",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopCountry",value = "店铺国家",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopAddress",value = "店铺地址",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopProvinceCode",value = "店铺地址省编码",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopCityCode",value = "店铺地址城市",paramType = "insert",required = false),
            @ApiImplicitParam(name = "eShopName",value = "电商店主名称",paramType = "insert",required = false),
            @ApiImplicitParam(name = "eShopUrl",value = "电商url",paramType = "insert",required = false),
            @ApiImplicitParam(name = "shopId",value = "店铺编号",paramType = "insert",required = false),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/uploadBasicInfo")
    public JSONObject finishBasicInfo(MultipartHttpServletRequest multipartHttpServletRequest){

        JSONObject resultJsonObject;
        Object[] reqParams = getParamters(multipartHttpServletRequest);
        //参数
        Map<String,String> paramsMap = (Map<String,String>)reqParams[0];
        ShopInfoVo shopInfoVo = packageShopInfo(paramsMap);
        JSONObject paramJsonObject = new JSONObject();
        paramJsonObject.put("shopBasicInfo",shopInfoVo);
        resultJsonObject = callUserServer(B_SHOP_USER_FINISH_INFO,paramJsonObject);
        //文件
        MultipartFile file = reqParams[1] == null ? null : (MultipartFile)reqParams[1];
        if(file != null){

            try {
               String  uploadResultJson  =  ossServer.upload(file, FilePathEnum.FILE_PATH_SHOP_LICENSES,null);
                BasicFileVo basicFileVo = JSONObject.parseObject(uploadResultJson, BasicFileVo.class);
               //保存用户营业执照
                JSONObject param = new JSONObject();
                param.put("shopId",paramsMap.get("shopId"));
                param.put("path",basicFileVo.getUrl());
                resultJsonObject =  callUserServer(B_SHOP_USER_SAVE_LICENSES,param);
            } catch (Exception e) {
                LOGGER.error("营业执照上传阿里云出错");
                return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
            }

        }
        return resultJsonObject;
    }


    @ApiOperation("b端上传身份信息(扫描证件)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺编号",paramType = "query",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!返回身份信息(真实姓名，证件期限,身份证号)"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/uploadIdCardPhoto/{shopId}")
    JSONObject uploadIdCardPhoto(MultipartRequest request,@PathVariable(value = "shopId",required = true)String shopId){
   // JSONObject uploadIdCardPhoto(@RequestParam("images")MultipartFile[] images,@PathVariable(value = "shopId",required = true)String shopId){
  //  JSONObject uploadIdCardPhoto(@RequestParam("images0")MultipartFile images0,
    //                             @RequestParam("images1")MultipartFile images1,
      //                           @PathVariable(value = "shopId",required = true)String shopId){
        List<MultipartFile> multipartFileList = new ArrayList<>();
        JSONObject param = new JSONObject();
        MultipartFile file1 = request.getFile("images0");
        MultipartFile file2 = request.getFile("images1");
        try {
           // param.put("idcardFront", getImageBase64EncodeStr(images[0]));
            //param.put("idcardBack",getImageBase64EncodeStr(images[1]));

            param.put("idcardFront", getImageBase64EncodeStr(file1));
            param.put("idcardBack",getImageBase64EncodeStr(file2));
        } catch (Exception e) {
            LOGGER.error("文件base64编码异常",e.getMessage());
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }

        param.put("idcardFrontExt",file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf(".")+1));
        param.put("idcardBackExt",file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf(".")+1));
        multipartFileList.add(file1);
        multipartFileList.add(file2);
       JSONObject jsonObject =  callUserServer(B_SHOP_USER_SCAN_IDCARD,param);
        if(RESULT_CODE_OPERATION_SUCCESS.getCode().equals(jsonObject.getString("code"))){
            //返回成功 存储OSS
            List<String> urlList = new ArrayList<>();
            for(MultipartFile file:multipartFileList){
                try{
                    String uploadResultJson = ossServer.upload(file,FilePathEnum.FILE_PATH_SHOP_IDCARD,null);
                    BasicFileVo basicFileVo = JSONObject.parseObject(uploadResultJson, BasicFileVo.class);
                    urlList.add(basicFileVo.getUrl());
                }catch (Exception e){
                    LOGGER.error("身份信息上传oss异常",e.getMessage());
                    return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
                }
            }
            //保存商家 信息
            JSONObject paramsJsonObject = new JSONObject();
            if(urlList != null && urlList.size() >0){
                paramsJsonObject.put("idcardFront",urlList.get(0));
                paramsJsonObject.put("idcardBack",urlList.get(1));
            }
            paramsJsonObject.put("shopId",shopId);
            callUserServer(B_SHOP_USER_SAVE_IDCARD,paramsJsonObject);
            return jsonObject;
        }
       return jsonObject;
    }

    public String getImageBase64EncodeStr(MultipartFile file) throws Exception{
        BASE64Encoder base64Encoder =new BASE64Encoder();
        String base64EncoderImg = file.getOriginalFilename()+","+ base64Encoder.encode(file.getBytes());
        return base64EncoderImg.replace(file.getOriginalFilename()+",","").replace("\r\n","");
    }




    @ApiOperation("b端上传身份信息(信息录入)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "realName",value = "真实姓名",paramType = "insert",required = true),
            @ApiImplicitParam(name = "idcard",value = "身份证号码",paramType = "insert",required = true),
            @ApiImplicitParam(name = "vaildType",value = "证件期限",paramType = "insert",required = true),
            @ApiImplicitParam(name = "vaildStartAt",value = "有效期开始日期",paramType = "insert",required = true),
            @ApiImplicitParam(name = "vaildEndAt",value = "有效期结束日期",paramType = "insert",required = true),
            @ApiImplicitParam(name = "mobile",value = "电话号码",paramType = "query",required = true),
            @ApiImplicitParam(name = "shopId",value = "店铺编号",paramType = "query",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/uploadIdCardInfo")
    JSONObject uploadIdCard(HttpServletRequest request){
      //  Object[] reqParams = getParamters(request);
        //参数
      //  Map<String,String> paramsMap = (Map<String,String>)reqParams[0];
        JSONObject paramsJsonObject = new JSONObject();
        paramsJsonObject.put("name",request.getParameter("realName"));
        paramsJsonObject.put("mobile",request.getParameter("mobile"));
        paramsJsonObject.put("idcard",request.getParameter("idcard"));
        //调用三要素检测
        JSONObject jsonObject = callUserServer(B_SHOP_USER_THREE_ElEMENT_CHECK,paramsJsonObject);
        if(RESULT_CODE_IDCARD_SAME.getCode().equals(jsonObject.get("code"))){
            //如果检测成功 插入用户表
            paramsJsonObject.put("vaildType",request.getParameter("vaildType"));
            paramsJsonObject.put("vaildStartAt",request.getParameter("vaildStartAt"));
            paramsJsonObject.put("vaildEndAt",request.getParameter("vaildEndAt"));
            paramsJsonObject.put("shopId",request.getParameter("shopId"));
            return callUserServer(B_SHOP_USER_SAVE_IDCARD,paramsJsonObject);
        }
        return jsonObject;
    }



    @ApiOperation("b端人脸比对,如果人脸照片和证件不匹配，返回比对失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "店铺编码", paramType = "query", required = true),
            @ApiImplicitParam(name = "facePhoto", value = "人脸照片,SDK收集生成的活体检测加密数据", paramType = "query", required = true)

    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "身份信息未知!"),
            @ApiResponse(code = 201, message = "一致！"),
            @ApiResponse(code = 202, message = "证件不一致，姓名不一致！"),
            @ApiResponse(code = 203, message = "证件一致，姓名不一致!"),
            @ApiResponse(code = 204, message = "证件不一致，姓名一致!"),
            @ApiResponse(code = 205, message = "证件不一致，姓名一致!"),
            @ApiResponse(code = 1, message = "系统异常!"),
    })
    @PostMapping("/portraitCheck")
    public JSONObject updateShopStatus(@RequestParam(value = "shopId",required = true)String shopId,
                                        @RequestParam(value = "facePhoto",required = true)String facePhoto){
        if(StringUtil.isEmpty(shopId)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject param = new JSONObject();
        param.put("shopId",shopId);
        param.put("facePhoto",facePhoto);
        return  callUserServer(B_SHOP_USER_PORTRAIT_CHECK,param);
    }

    private ShopInfoVo packageShopInfo(Map<String, String> params) {
        ShopInfoVo shopInfoVo = new ShopInfoVo();
        shopInfoVo.setSendAddress((params.get("sendAddress")!=null)?params.get("sendAddress"):"");
        shopInfoVo.setSendLat((params.get("sendLat")!=null)?params.get("sendLat"):"");
        shopInfoVo.setSendLon((params.get("sendLon")!=null)?params.get("sendLon"):"");
        shopInfoVo.setSendProvinceCode((params.get("sendProvinceCode")!=null)?params.get("sendProvinceCode"):"");
        shopInfoVo.setSendProvinceName((params.get("sendProvinceName")!=null)?params.get("sendProvinceName"):"");
        shopInfoVo.setSendCityCode((params.get("sendCityCode")!=null)?params.get("sendCityCode"):"");
        shopInfoVo.setSendCityName((params.get("sendCityName")!=null)?params.get("sendCityName"):"");
        shopInfoVo.setSendDistrictCode((params.get("sendDistrictCode")!=null)?params.get("sendDistrictCode"):"");
        shopInfoVo.setSendDistrictName((params.get("sendDistrictName")!=null)?params.get("sendDistrictName"):"");

        shopInfoVo.setShopType((params.get("shopType")!=null)?params.get("shopType"):"");
        shopInfoVo.setShopName((params.get("shopName")!=null)?params.get("shopName"):"");
        shopInfoVo.setShopLon((params.get("shopLon")!=null)?params.get("shopLon"):"");
        shopInfoVo.setShopLat((params.get("shopLat")!=null)?params.get("shopLat"):"");
        shopInfoVo.setShopAddress((params.get("shopAddress")!=null)?params.get("shopAddress"):"");
        shopInfoVo.setShopCountry((params.get("shopCountry")!=null)?params.get("shopCountry"):"");

        shopInfoVo.setShopProvinceCode((params.get("shopProvinceCode")!=null)?params.get("shopProvinceCode"):"");
        shopInfoVo.setShopCityCode((params.get("shopCityCode")!=null)?params.get("shopCityCode"):"");
        shopInfoVo.seteShopName((params.get("eShopName")!=null)?params.get("eShopName"):"");
        shopInfoVo.seteShopUrl((params.get("eShopUrl")!=null)?params.get("eShopUrl"):"");
        shopInfoVo.setShopId(params.get("shopId"));
        return shopInfoVo;
    }

    /**
     * 处理带文件的表单
     * @param request
     * @return
     * @throws Exception
     */
    private  Object[] getParamters(MultipartHttpServletRequest request){
        Object[] result = new Object[2];
        Map<String, String> mapParameter = new HashMap<>();
        Iterator<Map.Entry<String, String[]>> itor = request.getParameterMap().entrySet().iterator();
        Map.Entry<String, String[]> entry = null;
        while(itor.hasNext()) {
            entry = itor.next();
            String value = entry.getValue()[0];
            mapParameter.put(entry.getKey(), value == "" ? null : value);
        }
        result[0] = mapParameter;
        MultiValueMap<String, MultipartFile> filemap = request.getMultiFileMap();
        String key = "";
        MultipartFile file = null;
        if(!filemap.isEmpty()){
            Iterator<String> iterator = filemap.keySet().iterator();
            while (iterator.hasNext()){
                key = iterator.next();
                file = filemap.getFirst(key);
                if(!(file.isEmpty() || file.getSize() <= 0)){
                    result[1] = file;
                }
            }
        }
        return result;
    }

    @ApiOperation("b端重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "用户手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "用户新密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "dynamicCode", value = "手机动态码", paramType = "query", required = true),
            @ApiImplicitParam(name = "verifyCode", value = "图片验证码", paramType = "query", required = true),

    })
    @ApiResponses({
            @ApiResponse(code = 13, message = "图片码错误!"),
            @ApiResponse(code = 14, message = "图片验证码错误!"),
    })
    @PostMapping("/resetPassword")
    JSONObject resetPassword(@RequestParam(value = "mobile",required = true)String mobile,
                             @RequestParam(value = "password",required = true)String password,
                             @RequestParam(value = "verifyCode",required = true)String verifyCode,
                             @RequestParam(value = "dynamicCode",required = true)String dynamicCode){

        //校验参数
        if(StringUtil.isEmpty(mobile) ||
                StringUtil.isEmpty(password)||
                StringUtil.isEmpty(verifyCode)||
                StringUtil.isEmpty(dynamicCode)){
            //返回参数错误
            return  JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }

        JSONObject param = new JSONObject();
        param.put("mobile",mobile);
        param.put("password",password);
        param.put("dynamicCode",dynamicCode);
        param.put("verifyCode",verifyCode);

        return callUserServer(B_SHOP_USER_MODIFY_PASSWORD,param);
    }


    @PostMapping("/bindWx")
    JSONObject bindWx(@RequestParam(value = "wxOpenId",required = true) String wxOpenId,
                      @RequestParam(value = "mobile",required = true)String mobile){
        if(StringUtil.isEmpty(wxOpenId) || !StringUtil.isPhoneNumber(mobile)){
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        JSONObject object = new JSONObject();
        object.put("wxOpenId",wxOpenId);
        object.put("mobile",mobile);
        return callUserServer(B_SHOP_USER_BIND_WX,object);
    }


    @ApiOperation(value = "用户反馈接口", notes = "用户反馈接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "backType", value = "问题类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "content", value = "内容", paramType = "query", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 1, message = "系统异常")
    })
    @PostMapping("/userFeedBack")
    //public JSONObject userFeedBack(@RequestBody String json) {
    public JSONObject userFeedBack(MultipartHttpServletRequest request) {
        List<String> urlList = new ArrayList<>();
        final Map<String, MultipartFile> fileMap = request.getFileMap();
        if(!fileMap.isEmpty()){
            for (Map.Entry<String,MultipartFile> entity: fileMap.entrySet()) {
                try {
                    String uploadJson = ossServer.upload(entity.getValue(), FilePathEnum.FILE_PATH_SHOP_FEEDBAK, null);
                    BasicFileVo basicFileVo = JSONObject.parseObject(uploadJson, BasicFileVo.class);
                    urlList.add(basicFileVo.getUrl());
                } catch (Exception e) {
                    LOGGER.error("上传oss异常",e.getMessage());
                    return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR);
                }
            }
        }
        JSONObject param = new JSONObject();
        param.put("backType",request.getParameter("backType"));
        param.put("content",request.getParameter("content"));
        param.put("urlList",urlList);
        //request.getParameter("content");
        return callUserServer(B_SHOP_USER_FEED_BACK, param);
    }

}
