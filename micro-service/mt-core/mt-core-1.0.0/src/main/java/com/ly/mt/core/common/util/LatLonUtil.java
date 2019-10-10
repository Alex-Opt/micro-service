package com.ly.mt.core.common.util;

import com.ly.mt.core.common.entity.shop.ShopInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglifeng
 * @description 经纬度计算工具类
 */
public class LatLonUtil {
    private static double PI = 3.14159265;
    private static double EARTH_RADIUS = 6378137;
    private static double RAD = Math.PI / 180.0;

    /**
     * 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
     * @param lat 纬度
     * @param lon 经度
     * @param radius 半径(米)
     * @return
     */
    public static double[] getAround(double lat, double lon, int radius) {
        Double latTemp = lat;
        Double lonTemp = lon;
        Double degree = (24901 * 1609) / 360.0;
        double radiusMile = radius;
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * radiusMile;
        Double minLat = latTemp - radiusLat;
        Double maxLat = latTemp + radiusLat;
        Double mpdLng = degree * Math.cos(latTemp * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * radiusMile;
        Double minLon = lonTemp - radiusLng;
        Double maxLon= lonTemp + radiusLng;
        return new double[]{minLon,maxLon,minLat,maxLat};
    }

    /**
     * 根据提供的两个经纬度计算距离(米)
     * @param lng1 经度1
     * @param lat1 维度1
     * @param lng2 经度2
     * @param lat2 维度2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        double b = (lng1 - lng2) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

/*    public static void main(String args[]) {
       *//* System.out.println("Hello World!");
        double lat = 116.405285;
        double lon = 39.904989;
        double[] doubles = GetAround(lat, lon, 6000);
        System.out.println(doubles);*//*
       double min=200.00;
       int i=0;
        Map<Double, Integer> map = new HashMap();
       Double[] aa = new Double[]{12.023,10.145,85.123,56.23,45.21,80.21,2.32};
        for (double max: aa) {
            if(min>max){
                min=max;
            }
            i++;
            map.put(max,i);
        }
        Integer integer = map.get(min);
        System.out.print(min);
        System.out.print(integer);

    }*/
}
