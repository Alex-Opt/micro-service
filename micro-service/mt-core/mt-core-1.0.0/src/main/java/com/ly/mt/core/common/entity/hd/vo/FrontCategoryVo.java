package com.ly.mt.core.common.entity.hd.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrontCategoryVo {


    private Long id;

    private String name;

    private Integer appType;

    private Long areaId;

    private Long status;

    private Date createTime;

    private Date modifyTime;
    private String pictureUrl;

    private List<FrontSkuVo> skus;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<FrontSkuVo> getSkus() {
        return skus;
    }

    public void setSkus(List<FrontSkuVo> skus) {
        this.skus = skus;
    }

    public static List<FrontCategoryVoFace> converts(List<FrontCategoryVo> source){
        List<FrontCategoryVoFace> target = new ArrayList<>(source.size());
        source.forEach(s->{
            FrontCategoryVoFace f = new FrontCategoryVoFace();
            f.setId(s.getId());
            f.setPictureUrl(s.getPictureUrl());
            f.setName(s.getName());
            f.setSkus(s.getSkus());
            target.add(f);
        });
        return target;
    }

    public static FrontCategoryVoFace convert(FrontCategoryVo s){
        FrontCategoryVoFace f = new FrontCategoryVoFace();
        f.setId(s.getId());
        f.setPictureUrl(s.getPictureUrl());
        f.setName(s.getName());
        f.setSkus(s.getSkus());
        return f;
    }


}
