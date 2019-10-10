//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ly.mt.core.feign.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.*;

/**
* @program: mt-blue-tooth
* @description: 此处修改源码为了支持多文件传输到不同路径下
* @author: wanghongliang
* @create: 2019/8/20 21:27
**/
public class SpringFormEncoder extends FormEncoder {
    public SpringFormEncoder() {
        this(new Default());
    }

    public SpringFormEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor)this.getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }

    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        HashMap data;
        if (bodyType.equals(MultipartFile[].class)) {
            //此处修改源码 为了支持多文件传输到第三方center-third服务
            List<MultipartFile> list = new ArrayList<>();
            MultipartFile[] files = (MultipartFile[])((MultipartFile[])object);
            data = new HashMap(files.length, 1.0F);
            MultipartFile[] var6 = files;
            int var7 = files.length;
            String key="files";
            for(int var8 = 0; var8 < var7; ++var8) {
                MultipartFile file = var6[var8];
                list.add(file);
            }
            data.put(key, list);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            MultipartFile file = (MultipartFile)object;
            Map<String, Object> data1 = Collections.singletonMap(file.getName(), object);
            super.encode(data1, MAP_STRING_WILDCARD, template);
        } else if (this.isMultipartFileCollection(object)) {
            Iterable<?> iterable = (Iterable)object;
            data = new HashMap();
            Iterator var13 = iterable.iterator();

            while(var13.hasNext()) {
                Object item = var13.next();
                MultipartFile file = (MultipartFile)item;
                data.put(file.getName(), file);
            }

            super.encode(data, MAP_STRING_WILDCARD, template);
        } else {
            super.encode(object, bodyType, template);
        }

    }

    private boolean isMultipartFileCollection(Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        } else {
            Iterable<?> iterable = (Iterable)object;
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext() && iterator.next() instanceof MultipartFile;
        }
    }
}
