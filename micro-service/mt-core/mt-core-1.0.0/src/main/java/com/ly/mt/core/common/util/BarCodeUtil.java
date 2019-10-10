package com.ly.mt.core.common.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author panjingtian
 * @description 条形码工具类
 * 用于生成和解析二维码
 * @date 2019/6/10 3:57 PM
 */
public class BarCodeUtil {

    private static final Map<EncodeHintType, ErrorCorrectionLevel> encodeMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
    //字符集
    private static final String charset = "UTF-8";
    //图片格式
    private static final String  format = "png";
    //生成图片的大小
    private static final int size = 150;


    /**
     * 生成二维码inputStream流
     * ！！！！！！！！！
     * 注意！！！！！调用方需要自行关闭流！！！！！
     *！！！！！！！！！！
     * @param atctivityUrl 活动url
     * @return
     */
    public static InputStream createBarcode(String atctivityUrl) throws Exception {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(new MultiFormatWriter().encode(
                new String(atctivityUrl.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, size, size, encodeMap), format, os);

        byte[] bytes = os.toByteArray();

        //上传到OSS是上传字节的形式
        InputStream ips = new ByteArrayInputStream(bytes);
        return ips;

    }


    /**
     * 解析二维码信息
     *
     * @param inputStream
     * @throws IOException
     */
    public static void readQrCode(InputStream inputStream) throws IOException {
        //从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);
        //将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        try {
            result = reader.decode(bitmap);
        } catch (ReaderException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
    }

}
