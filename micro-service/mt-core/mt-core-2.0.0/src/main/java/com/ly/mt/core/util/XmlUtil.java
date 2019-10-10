package com.ly.mt.core.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * @Description xml操作工具类
 * @Author taoye
 */
public class XmlUtil {
    /**
     * @Description map转xml
     * @Author taoye
     */
    public static String transMap2Xml(Map<String, Object> map) {
        if (null == map) {
            return null;
        }
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        map.forEach(
                (k, v) -> {
                    xml.append("<");
                    xml.append(k);
                    xml.append(">");
                    xml.append(v);
                    xml.append("</");
                    xml.append(k);
                    xml.append(">");
                }
        );
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * @Description xml转map
     * @Author taoye
     */
    public static Map<String, Object> transXml2Map(String xml) throws Exception {
        Document doc = DocumentHelper.parseText(xml);
        Map<String, Object> map = new HashMap<>();
        if (doc == null) {
            return map;
        }
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Element e = (Element) iterator.next();
            List list = e.elements();
            if (list.size() > 0) {
                map.put(e.getName(), transXml2Map(e));
            } else {
                map.put(e.getName(), e.getText());
            }
        }
        return map;
    }

    /**
     * @Description xml转map
     * @Author taoye
     */
    private static Map<String, Object> transXml2Map(Element e) {
        Map<String, Object> map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List<Object> mapList = new ArrayList();
                if (iter.elements().size() > 0) {
                    Map m = transXml2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), m);
                    }
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), iter.getText());
                    }
                }
            }
        } else {
            map.put(e.getName(), e.getText());
        }
        return map;
    }
}