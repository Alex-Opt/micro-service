package com.ly.mt.center.third.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * [处理调用第三方返回数据流，要转码的请求工具类]
 *
 * @author zhanglifeng
 * @date 2019-09-23
 */
public class HttpXmlClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpXmlClient.class);

    public static String postSend(String url, String param) {
        PrintWriter out = null;
        String result = "";
        InputStream inputStream = null;
        //请求数据，自行拼接
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/json;charset-gbk");
            conn.setRequestProperty("responseType", "arraybuffer");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            //获取流数据
            inputStream = conn.getInputStream();
            // 将获取流转为base64格式
            byte[] data = null;
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
            result = new String(Base64.getEncoder().encode(data));
        } catch (Exception e) {
            LOGGER.error("发送 POST 请求出现异常！{}", e);
        } finally {
            //使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                LOGGER.error("Http请求关闭流出现异常！{}", ex);
            }
        }
        return result;
    }
}
