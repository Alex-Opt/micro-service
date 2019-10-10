package com.ly.mt.center.data.goods.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.entity.GoodsCategroyInfo;
import com.ly.mt.core.base.entity.ResponseJson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsCategroyInfoMapper {
    /**
     * @Description 保存GoodsCategroyInfo
     * @Author taoye
     */
    void insertGoodsCategroyInfo(GoodsCategroyInfo goodsCategroyInfo);

    /**
     * @Description 删除GoodsCategroyInfo
     * @Author taoye
     */
    void deleteGoodsCategroyInfo(GoodsCategroyInfo goodsCategroyInfo);

    /**
     * @Description 更新GoodsCategroyInfo
     * @Author taoye
     */
    int updateGoodsCategroyInfo(GoodsCategroyInfo goodsCategroyInfo);

    /**
     * @Description 查询GoodsCategroyInfo
     * @Author taoye
     */
    GoodsCategroyInfo getGoodsCategroyInfo(GoodsCategroyInfo goodsCategroyInfo);

    /**
     * 根据上级id查询子级数据
     * @param goodsCategroyInfo
     * @return
     */
    List<GoodsCategroyInfo> getGoodsCategroyInfoByParentId(GoodsCategroyInfo goodsCategroyInfo);

    /**
     * 根据上级id查询所有自己类目数据
     * @param jsonObject
     * @return
     */
    ResponseJson getGoodsCategroyInfoByParentId(JSONObject jsonObject);
}