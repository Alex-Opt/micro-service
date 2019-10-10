package com.ly.mt.center.data.trade.mapper;

import com.ly.mt.center.data.trade.entity.TradeInvoice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeInvoiceMapper {
    /**
     * @Description 保存TradeInvoice
     * @Author taoye
     */
    void insertTradeInvoice(TradeInvoice tradeInvoice);

    /**
     * @Description 删除TradeInvoice
     * @Author taoye
     */
    void deleteTradeInvoice(TradeInvoice tradeInvoice);

    /**
     * @Description 更新TradeInvoice
     * @Author taoye
     */
    int updateTradeInvoice(TradeInvoice tradeInvoice);

    /**
     * @Description 查询TradeInvoice
     * @Author taoye
     */
    TradeInvoice getTradeInvoice(TradeInvoice tradeInvoice);
}