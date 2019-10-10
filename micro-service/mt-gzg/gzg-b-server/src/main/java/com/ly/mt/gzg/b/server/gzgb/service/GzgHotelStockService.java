package com.ly.mt.gzg.b.server.gzgb.service;

import com.alibaba.fastjson.JSONObject;

public interface GzgHotelStockService  {

    JSONObject getSkus(String jsonParam);

    JSONObject saveHotelStock(String jsonParam);

    JSONObject findHotelSocks(String jsonParam);
}
