package org.qboot.modules.system.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.qboot.common.domain.CustomIdGenerator;
import org.qboot.common.domain.UUIDv7Generator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sys_dict")
@RegisterForReflection
public class SysDict extends PanacheEntityBase {

    @Id
    @GeneratedValue( generator = "UUIDv7_generator")
//    @GeneratedValue(strategy = GenerationType.UUID, generator = "UUIDv7_generator")
    @GenericGenerator(name = "UUIDv7_generator", type = UUIDv7Generator.class)
    private String id;

    /**
     * [预留字段，暂时无用]
     * 字典类型,0 string,1 number类型,2 boolean
     * 前端js对stirng类型和number类型 boolean 类型敏感，需要区分。在select 标签匹配的时候会用到
     * 默认为string类型
     */
    private Integer type;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除状态
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**租户ID*/
    private java.lang.Integer tenantId;

    /** 关联的低代码应用ID */
    private java.lang.String lowAppId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dict_id", referencedColumnName = "id")
    public List<SysDictItem> items = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public java.lang.Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(java.lang.Integer tenantId) {
        this.tenantId = tenantId;
    }

    public java.lang.String getLowAppId() {
        return lowAppId;
    }

    public void setLowAppId(java.lang.String lowAppId) {
        this.lowAppId = lowAppId;
    }

    public List<SysDictItem> getItems() {
        return items;
    }

    public void setItems(List<SysDictItem> items) {
        this.items = items;
    }

    public void addItem(SysDictItem dictItem) {
        // 领域逻辑，例如检查项是否已经存在
        items.add(dictItem);
    }

    public void removeItem(String itemId) {
        items.removeIf(n->n.getId().equals(itemId));
    }

    public SysDictItem updateDictItem(String itemId, SysDictItem dictItem) {
        Optional<SysDictItem> itemToUpdate = items.stream()
                .filter(n -> n.getId().equals(itemId))
                .findFirst();

        if (itemToUpdate.isPresent()) {
            SysDictItem item = itemToUpdate.get();
            item.setItemText(dictItem.getItemText());
            item.setItemValue(dictItem.getItemValue());
            item.setItemColor(dictItem.getItemColor());
            item.setDescription(dictItem.getDescription());
            item.setSortOrder(dictItem.getSortOrder());
            item.setStatus(dictItem.getStatus());
            return dictItem;
        } else {
            throw new IllegalArgumentException("Item not found");
        }
    }
}
