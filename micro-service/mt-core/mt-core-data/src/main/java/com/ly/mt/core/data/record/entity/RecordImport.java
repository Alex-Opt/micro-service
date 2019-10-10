package com.ly.mt.core.data.record.entity;

/**
 * record_import
 *
 * @author taoye
 */
public class RecordImport {
    /**
     * 主键id
     */
    private String id;
    /**
     * 上传条数
     */
    private String rowCount;
    /**
     * 上传类型
     *
     * @see com.ly.mt.core.base.dict.ImportType
     */
    private String importType;
    /**
     * 创建人id
     */
    private String importId;
    /**
     * 创建人name
     */
    private String importName;
    /**
     * 创建时间
     */
    private String importTime;
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 删除人id
     */
    private String deleteId;
    /**
     * 删除时间
     */
    private String deleteTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getImportId() {
        return importId;
    }

    public void setImportId(String importId) {
        this.importId = importId;
    }

    public String getImportName() {
        return importName;
    }

    public void setImportName(String importName) {
        this.importName = importName;
    }

    public String getImportTime() {
        return importTime;
    }

    public void setImportTime(String importTime) {
        this.importTime = importTime;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(String deleteId) {
        this.deleteId = deleteId;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }
}