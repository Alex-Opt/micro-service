package com.ly.mt.core.common.method;

/**
 * @Description 后台服务接口枚举
 * @Author taoye
 */
public enum SystemMethodEnum {
    ROTATION_INFO_SAVE("rotationInfoServiceImpl", "rotationInfoSave", "轮播图上传"),
    ROTATION_INFO_LIST("rotationInfoServiceImpl", "rotationInfoList", "轮播图上传"),

    GOODS_SPU_AEROSOL("goodsSpuServiceImpl", "queryGoodsSpuByAerosol", "查询雾化弹类目商品spu-查询接口"),
    GOODS_SKU_PICTURE("goodsSkuServiceImpl","saveSkuPicture","商品SKU-图片-添加接口");
    /**
     * @Description 接口名字
     */
    private final String serviceName;
    /**
     * @Description 方法名字
     */
    private final String functionName;
    /**
     * @Description 方法描述
     */
    private final String describe;

    SystemMethodEnum(String serviceName, String functionName, String describe) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.describe = describe;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }
}