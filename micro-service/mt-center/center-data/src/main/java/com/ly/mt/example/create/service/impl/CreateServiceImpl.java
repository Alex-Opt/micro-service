package com.ly.mt.example.create.service.impl;

import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.example.create.mapper.CreateMapper;
import com.ly.mt.example.create.service.CreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

@Service
public class CreateServiceImpl extends BaseServiceImpl implements CreateService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateServiceImpl.class);
    private static final String ENTITY_PACKAGE = "package com.ly.mt.example.create.entity;";
    private static final String CONTROLLER_PACKAGE = "package com.ly.mt.example.create.controller;";
    private static final String SERVICE_PACKAGE = "package com.ly.mt.example.create.service;";
    private static final String IMPL_PACKAGE = "package com.ly.mt.example.create.service.impl;";
    private static final String MAPPER_PACKAGE = "package com.ly.mt.example.create.mapper;";
    @Resource
    CreateMapper mapper;

    /**
     * 根据表名创建基础代码
     *
     * @author taoye
     */
    @Override
    public void createBaseCodeByTableNames(String tableNames, String author) {
        String[] arr = tableNames.split(",");
        for (int i = 0; i < arr.length; i++) {
            createBaseCodeByTableName(arr[i], author);
        }
    }

    private void createBaseCodeByTableName(String tableName, String author) {
        try {
            List<Map<String, String>> list = mapper.getTableColumn(tableName);
            String entityName = getEntityNameByTableName(tableName);
            String controllerName = entityName + "Controller";
            String serviceName = entityName + "Service";
            String implName = serviceName + "Impl";
            String mapperName = entityName + "Mapper";
            String path = CreateServiceImpl.class.getResource("").toString().substring(6);
            String entityPath = path.replace("target/classes/com/ly/mt/example/create/service/impl", "src/main/java/com/ly/mt/example/create/entity") + entityName + ".java";
            String controllerPath = path.replace("target/classes/com/ly/mt/example/create/service/impl", "src/main/java/com/ly/mt/example/create/controller") + controllerName + ".java";
            String servicePath = path.replace("target/classes/com/ly/mt/example/create/service/impl", "src/main/java/com/ly/mt/example/create/service") + serviceName + ".java";
            String implPath = path.replace("target/classes/com/ly/mt/example/create/service/impl", "src/main/java/com/ly/mt/example/create/service/impl") + implName + ".java";
            String mapperPath = path.replace("target/classes/com/ly/mt/example/create/service/impl", "src/main/java/com/ly/mt/example/create/mapper") + mapperName + ".java";
            String xmlPath = path.replace("target/classes/com/ly/mt/example/create/service/impl", "src/main/resources/mapper/example/create") + mapperName + ".xml";
            // entity
            String entityStr = getEntityStr(entityName, list);
            create(entityPath, entityStr);
            // controller
            String controllerStr = getControllerStr(controllerName, entityName);
            create(controllerPath, controllerStr);
            // service
            String serviceStr = getServiceStr(serviceName, entityName, author);
            create(servicePath, serviceStr);
            // impl
            String implStr = getImplStr(implName, serviceName, entityName, mapperName, author);
            create(implPath, implStr);
            // mapper
            String mapperStr = getMapperStr(mapperName, entityName, author);
            create(mapperPath, mapperStr);
            // xml
            String xmlStr = getXmlStr(mapperName, entityName, tableName, list);
            create(xmlPath, xmlStr);
            System.out.println("    /**\n" +
                    "     *  \n" +
                    "     * @author " + author + "\n" +
                    "     */");
            System.out.println(tableName.toUpperCase() + "_INSERT(\"" + transLowerCaseHump(implName) + "\", " + "\"insert" + entityName + "\", " + "\"保存" + entityName + "\"),");
            System.out.println(tableName.toUpperCase() + "_UPDATE(\"" + transLowerCaseHump(implName) + "\", " + "\"update" + entityName + "\", " + "\"修改" + entityName + "\"),");
            System.out.println(tableName.toUpperCase() + "_GET(\"" + transLowerCaseHump(implName) + "\", " + "\"get" + entityName + "\", " + "\"查询" + entityName + "\"),");
            System.out.println("");
            System.out.println("");
        } catch (Exception e) {
            LOGGER.error("根据{}创建基础代码出错:", tableName, e);
        }
    }


    private String getEntityNameByTableName(String name) {
        StringBuilder sb = new StringBuilder();
        String[] arr = name.split("_");
        for (int i = 0; i < arr.length; i++) {
            sb.append(transUpperCaseHump(arr[i]));
        }
        return sb.toString();
    }

    private String getEntityStr(String name, List<Map<String, String>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(ENTITY_PACKAGE).append("\n").append("\n");
        sb.append("import io.swagger.annotations.ApiModel;").append("\n");
        sb.append("import io.swagger.annotations.ApiModelProperty;").append("\n").append("\n");
        sb.append("@ApiModel").append("\n");
        sb.append("public class ").append(name).append(" {").append("\n");
        list.forEach(map -> {
            String column_name = map.get("column_name");
            sb.append("    @ApiModelProperty(");
            if ("id".equals(column_name)) {
                sb.append("\"主键id\")").append("\n");
            } else {
                sb.append("\"").append(map.get("column_comment")).append("\")").append("\n");
            }
            sb.append("    private String ").append(column_name).append(";").append("\n");
        });
        sb.append("\n");
        sb.append("\n");
        list.forEach(map -> {
            String column_name = map.get("column_name");
            String filed = transUpperCaseHump(column_name);
            sb.append("    public String get").append(filed).append("() {").append("\n");
            sb.append("        return ").append(column_name).append(";").append("\n");
            sb.append("    }").append("\n").append("\n");
            sb.append("    public void set").append(filed).append("(String ").append(column_name).append(") {").append("\n");
            sb.append("        this.").append(column_name).append(" = ").append(column_name).append(";").append("\n");
            sb.append("    }").append("\n").append("\n");
            ;
        });
        sb.append("}");
        return sb.toString();
    }

    private String getControllerStr(String controllerName, String entityName) {
        StringBuilder sb = new StringBuilder();
        sb.append(CONTROLLER_PACKAGE).append("\n").append("\n");
        sb.append("import com.ly.mt.example.create.entity.").append(entityName).append(";").append("\n");
        sb.append("import com.ly.mt.core.base.entity.ResponseJson;").append("\n");
        sb.append("import io.swagger.annotations.*;").append("\n");
        sb.append("import org.springframework.web.bind.annotation.PostMapping;").append("\n");
        sb.append("import org.springframework.web.bind.annotation.RequestBody;").append("\n");
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;").append("\n");
        sb.append("import org.springframework.web.bind.annotation.RestController;").append("\n").append("\n");
        sb.append("@Api(description = \"接口\")").append("\n");
        sb.append("@RestController").append("\n");
        sb.append("@RequestMapping(\"/data/center/").append(transLowerCaseHump(entityName)).append("\")\n");
        sb.append("public class ").append(controllerName).append(" {").append("\n");
        sb.append("    @ApiOperation(value = \"保存").append(entityName).append("\")").append("\n");
        sb.append("    @ApiResponses({").append("\n");
        sb.append("            @ApiResponse(code = 0, message = \"保存成功!\"),").append("\n");
        sb.append("            @ApiResponse(code = 1, message = \"保存失败!\")").append("\n");
        sb.append("    })").append("\n");
        sb.append("    @PostMapping(\"/inser").append(entityName).append("\")").append("\n");
        sb.append("    public ResponseJson insert").append(entityName).append("(@RequestBody ").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(") {").append("\n");
        sb.append("        return null;").append("\n");
        sb.append("    }").append("\n").append("\n").append("\n");
        sb.append("    @ApiOperation(").append("\n");
        sb.append("            value = \"更新").append(entityName).append("\",").append("\n");
        sb.append("            notes = \"1、字段值为null时不更新,为空符串时更新数据库字段为空  \\n\" +").append("\n");
        sb.append("                    \"2、更新条件id不能为空  \\n\"").append("\n");
        sb.append("    )").append("\n");
        sb.append("    @ApiResponses({").append("\n");
        sb.append("            @ApiResponse(code = 0, message = \"更新成功!\"),").append("\n");
        sb.append("            @ApiResponse(code = 1, message = \"更新失败!\")").append("\n");
        sb.append("    })").append("\n");
        sb.append("    @PostMapping(\"/update").append(entityName).append("\")").append("\n");
        sb.append("    public ResponseJson update").append(entityName).append("(@RequestBody ").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(") {").append("\n");
        sb.append("        return null;").append("\n");
        sb.append("    }").append("\n").append("\n").append("\n");
        sb.append("    @ApiOperation(").append("\n");
        sb.append("            value = \"查询").append(entityName).append("\",").append("\n");
        sb.append("            notes = \"查询条件id不能为空  \\n\"").append("\n");
        sb.append("    )").append("\n");
        sb.append("    @ApiResponses({").append("\n");
        sb.append("            @ApiResponse(code = 0, message = \"查询成功!\"),").append("\n");
        sb.append("            @ApiResponse(code = 1, message = \"查询失败!\")").append("\n");
        sb.append("    })").append("\n");
        sb.append("    @PostMapping(\"/get").append(entityName).append("\")").append("\n");
        sb.append("    public ResponseJson get").append(entityName).append("(@RequestBody ").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(") {").append("\n");
        sb.append("        return null;").append("\n");
        sb.append("    }").append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String getServiceStr(String serviceName, String entityName, String author) {
        StringBuilder sb = new StringBuilder();
        sb.append(SERVICE_PACKAGE).append("\n").append("\n");
        sb.append("import com.alibaba.fastjson.JSONObject;").append("\n");
        sb.append("import com.ly.mt.core.base.entity.ResponseJson;").append("\n").append("\n");
        sb.append("public interface ").append(serviceName).append(" {").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 保存").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    ResponseJson insert").append(entityName).append("(JSONObject jsonObject);").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 更新").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    ResponseJson update").append(entityName).append("(JSONObject jsonObject);").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 查询").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    ResponseJson get").append(entityName).append("(JSONObject jsonObject);").append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String getImplStr(String implName, String serviceName, String entityName, String mapperName, String author) {
        StringBuilder sb = new StringBuilder();
        sb.append(IMPL_PACKAGE).append("\n").append("\n");
        sb.append("import com.alibaba.fastjson.JSONObject;").append("\n");
        sb.append("import com.ly.mt.example.create.mapper.").append(mapperName).append(";").append("\n");
        sb.append("import com.ly.mt.example.create.service.").append(serviceName).append(";").append("\n");
        sb.append("import com.ly.mt.example.create.entity.").append(entityName).append(";").append("\n");
        sb.append("import com.ly.mt.base.service.impl.BaseServiceImpl;").append("\n");
        sb.append("import com.ly.mt.core.base.entity.ResponseJson;").append("\n");
        sb.append("import com.ly.mt.core.base.util.ResponseUtil;").append("\n");
        sb.append("import com.ly.mt.core.base.util.StringUtil;").append("\n");
        sb.append("import org.slf4j.Logger;").append("\n");
        sb.append("import org.slf4j.LoggerFactory;").append("\n");
        sb.append("import org.springframework.stereotype.Service;").append("\n").append("\n");
        sb.append("import javax.annotation.Resource;").append("\n").append("\n");
        sb.append("import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;").append("\n");
        sb.append("import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;").append("\n").append("\n");
        sb.append("@Service").append("\n");
        sb.append("public class ").append(implName).append(" extends BaseServiceImpl implements ").append(serviceName).append(" {").append("\n");
        sb.append("    private final static Logger LOGGER = LoggerFactory.getLogger(").append(implName).append(".class);").append("\n");
        sb.append("    @Resource").append("\n");
        sb.append("    ").append(mapperName).append(" mapper;").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 保存").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    @Override").append("\n");
        sb.append("    public ResponseJson insert").append(entityName).append("(JSONObject jsonObject) {").append("\n");
        sb.append("        try {").append("\n");
        sb.append("            ").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(" = JSONObject.toJavaObject(jsonObject, ").append(entityName).append(".class);").append("\n");
        sb.append("            if (StringUtil.isEmpty(").append(transLowerCaseHump(entityName)).append(".getId())) {").append("\n");
        sb.append("                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, \"主键id不能为空\");").append("\n");
        sb.append("            }").append("\n");
        sb.append("            mapper.insert").append(entityName).append("(").append(transLowerCaseHump(entityName)).append(");").append("\n");
        sb.append("            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);").append("\n");
        sb.append("        } catch (Exception e) {").append("\n");
        sb.append("            LOGGER.error(\"").append(implName).append(".insert").append(entityName).append("出错:").append("\", e);").append("\n");
        sb.append("            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);").append("\n");
        sb.append("        }").append("\n");
        sb.append("    }").append("\n").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 更新").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    @Override").append("\n");
        sb.append("    public ResponseJson update").append(entityName).append("(JSONObject jsonObject) {").append("\n");
        sb.append("        try {").append("\n");
        sb.append("            ").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(" = JSONObject.toJavaObject(jsonObject, ").append(entityName).append(".class);").append("\n");
        sb.append("            if (StringUtil.isEmpty(").append(transLowerCaseHump(entityName)).append(".getId())) {").append("\n");
        sb.append("                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, \"更新条件不能为空\");").append("\n");
        sb.append("            }").append("\n");
        sb.append("            mapper.update").append(entityName).append("(").append(transLowerCaseHump(entityName)).append(");").append("\n");
        sb.append("            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);").append("\n");
        sb.append("        } catch (Exception e) {").append("\n");
        sb.append("            LOGGER.error(\"").append(implName).append(".update").append(entityName).append("ById出错:").append("\", e);").append("\n");
        sb.append("            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);").append("\n");
        sb.append("        }").append("\n");
        sb.append("    }").append("\n").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 查询").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    @Override").append("\n");
        sb.append("    public ResponseJson get").append(entityName).append("(JSONObject jsonObject) {").append("\n");
        sb.append("        try {").append("\n");
        sb.append("            ").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(" = JSONObject.toJavaObject(jsonObject, ").append(entityName).append(".class);").append("\n");
        sb.append("            if (StringUtil.isEmpty(").append(transLowerCaseHump(entityName)).append(".getId())) {").append("\n");
        sb.append("                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, \"查询条件不能为空\");").append("\n");
        sb.append("            }").append("\n");
        sb.append("            ").append(transLowerCaseHump(entityName)).append(" = mapper.get").append(entityName).append("(").append(transLowerCaseHump(entityName)).append(");").append("\n");
        sb.append("            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, ").append(transLowerCaseHump(entityName)).append(");").append("\n");
        sb.append("        } catch (Exception e) {").append("\n");
        sb.append("            LOGGER.error(\"").append(implName).append(".get").append(entityName).append("出错:").append("\", e);").append("\n");
        sb.append("            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);").append("\n");
        sb.append("        }").append("\n");
        sb.append("    }").append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String getMapperStr(String mapperName, String entityName, String author) {
        StringBuilder sb = new StringBuilder();
        sb.append(MAPPER_PACKAGE).append("\n").append("\n");
        sb.append("import com.ly.mt.example.create.entity.").append(entityName).append(";").append("\n");
        sb.append("import org.apache.ibatis.annotations.Mapper;").append("\n").append("\n");
        sb.append("@Mapper").append("\n");
        sb.append("public interface ").append(mapperName).append(" {").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 保存").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    void insert").append(entityName).append("(").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(");").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 更新").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    int update").append(entityName).append("(").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(");").append("\n").append("\n");
        sb.append("    /**").append("\n");
        sb.append("     * 查询").append(entityName).append("\n");
        sb.append("     *").append("\n");
        sb.append("     * @author ").append(author).append("\n");
        sb.append("     */").append("\n");
        sb.append("    ").append(entityName).append(" get").append(entityName).append("(").append(entityName).append(" ").append(transLowerCaseHump(entityName)).append(");").append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String getXmlStr(String mapperName, String entityName, String tableName, List<Map<String, String>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >").append("\n");
        sb.append("<mapper namespace = \"com.ly.mt.example.create.mapper.").append(mapperName).append("\" >").append("\n");
        sb.append("    <!-- 保存").append(entityName).append(" -->").append("\n");
        sb.append("    <insert id=\"insert").append(entityName).append("\" parameterType=\"com.ly.mt.example.create.entity.").append(entityName).append("\">").append("\n");
        sb.append("        INSERT INTO ").append(tableName).append(" (").append("\n");
        sb.append("            ");
        list.forEach(map -> {
            sb.append(map.get("column_name"));
            sb.append(", ");
        });
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        sb.append("        ) VALUES (").append("\n");
        sb.append("            ");
        list.forEach(map -> {
            sb.append("#{");
            sb.append(map.get("column_name"));
            sb.append("}, ");
        });
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        sb.append("        )").append("\n");
        sb.append("    </insert>");
        sb.append("\n").append("\n").append("\n");
        sb.append("    <!-- 更新").append(entityName).append(" -->").append("\n");
        sb.append("    <update id=\"update").append(entityName).append("\" parameterType=\"com.ly.mt.example.create.entity.").append(entityName).append("\">").append("\n");
        sb.append("        UPDATE ").append(tableName).append("\n");
        sb.append("        <trim prefix=\"set\" suffixOverrides=\",\">").append("\n");
        list.forEach(map -> {
            String column_name = map.get("column_name");
            sb.append("            <if test=\"null != ").append(column_name).append("\">").append(column_name).append(" = #{").append(column_name).append("},</if>").append("\n");
        });
        sb.append("        </trim>").append("\n");
        sb.append("        <where>").append("\n");
        sb.append("            <if test=\"null != id and '' != id\">AND id = #{id}</if>").append("\n");
        sb.append("        </where>").append("\n");
        sb.append("    </update>").append("\n").append("\n").append("\n");
        sb.append("    <!-- 查询").append(entityName).append(" -->").append("\n");
        sb.append("    <select id=\"get").append(entityName).append("\" parameterType=\"com.ly.mt.example.create.entity.").append(entityName).append("\" resultType=\"com.ly.mt.example.create.entity.").append(entityName).append("\">").append("\n");
        sb.append("        SELECT * ").append("\n");
        sb.append("        FROM ").append(tableName).append("\n");
        sb.append("        <where>").append("\n");
        sb.append("            <if test=\"null != id and '' != id\">AND id = #{id}</if>").append("\n");
        sb.append("        </where>").append("\n");
        sb.append("        LIMIT 1").append("\n");
        sb.append("    </select>").append("\n");
        sb.append("</mapper>");
        return sb.toString();
    }

    private String transUpperCaseHump(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    private String transLowerCaseHump(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    private void create(String path, String content) throws Exception {
        // 1：利用File类找到要操作的对象
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //2：准备输出流
        Writer out = new FileWriter(file);
        out.write(content);
        out.close();
    }
}
