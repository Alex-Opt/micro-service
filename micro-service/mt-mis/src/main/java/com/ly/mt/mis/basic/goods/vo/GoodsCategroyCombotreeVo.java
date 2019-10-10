package com.ly.mt.mis.basic.goods.vo;

import com.ly.mt.core.base.eaeyui.Combotree;

/**
 * 商品类目下拉树
 *
 * @author taoye
 */
public class GoodsCategroyCombotreeVo extends Combotree {
    /**
     * 商品类目名称
     */
    private String name;
    /**
     * 商品类目父id
     */
    private String parentId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setText(name);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}