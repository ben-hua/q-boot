package org.qboot.modules.system.core.domain;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.qboot.common.domain.UUIDv7Generator;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sys_dict_item")
@RegisterForReflection
public class SysDictItem { //extends AbstractAuditingEntity

    @Id
    @GeneratedValue( generator = "UUIDv7_generator")
    @GenericGenerator(name = "UUIDv7_generator", type = UUIDv7Generator.class)
    public String id;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态（1启用 0不启用）
     */
    private Integer status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    /**
     * 字典项颜色 
     */
    private String itemColor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

}
