package com.rmkj.microcap.common.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rmkj.microcap.common.modules.sys.utils.DictUtils;
import com.rmkj.microcap.common.utils.Utils;

import java.util.Date;

/**
 * 数据实体bean
 *
 * @author
 * @since 2015-12-22 10:21:12
 */
@JsonIgnoreProperties(value = {"start", "rows", "sortBy", "delFlag"})
public abstract class DataEntity extends PageEntity {
    protected String id;

    //删除标识(0:未删除1：删除)
    protected String delFlag = "0";

    public DataEntity() {
        super();
    }

    public DataEntity(String id) {
        super();
        this.id = id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        this.setId(Utils.uuid());
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    public void preUpdate() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
