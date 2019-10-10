package com.ly.mt.cabinet.c.wechat.entity;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : evan
 * @date : 2019-06-02 17:33
 * create by intellij 2019
 */
@Component
public class XMLUtils {
    /**
     * map转换xml
     * @param map
     * @return
     */
    public String map2Xml(Map<String,Object> map){
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Map.Entry<String,Object> entry : map.entrySet()){
            xml.append("<")
                    .append(entry.getKey()).
                    append(">").
                    append(entry.getValue()).
                    append("</").
                    append(entry.getKey()).
                    append(">").append("\n");

        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * xml转换map
     * @param xml
     * @return
     */
    public Map<String,Object> xml2Map(String xml){
        if (StringUtils.isBlank(xml)){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));
            Element rootElement =
                    document.getRootElement();
            parse(rootElement,map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws IOException
     */
    public Map<String, Object> doXMLParse(String strxml) throws IOException{
        if(null == strxml || "".equals(strxml)) {
            return null;
        }
        Map<String, Object> m = new HashMap<String, Object>();
        try {
            // 创建saxReader对象
            SAXReader reader = new SAXReader();
            // 通过read方法读取一个文件 转换成Document对象
            Document document = reader.read(new ByteArrayInputStream(strxml.getBytes("UTF-8")));
            //获取根节点元素对象
            Element node = document.getRootElement();
            parse(node, m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    /**
     * 递归遍历Element
     * @param rootElement
     * @param m
     * @return
     */
    private void parse(Element rootElement,Map<String,Object> m){
        m.put(rootElement.getName(),rootElement.getTextTrim());
        Iterator<Element> it = rootElement.elementIterator();
        while (it.hasNext()){
            Element next = it.next();
            parse(next,m);
        }
    }


}
