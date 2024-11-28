package org.qboot.modules.system.core.application.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.qboot.modules.system.core.domain.Address;
import org.qboot.modules.system.core.domain.Contact;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OrganizationDTO {

    @Schema(readOnly = true)
    private UUID id;

    private String name;
    private String type;
    private String code;
    private String status;
    private String nameEn;
    private String nameAbbr;
    private String description;
    private int orderNum;

    private String website;
    private String logoFileId;
    private String areasOfInterest;
    private int level;

    private Contact contact;

    private Address address;

    private String enterpriseWebchatID;
    private String tenantId;

    private UUID parentId;

    @Schema(readOnly = true)
    private List<OrganizationDTO> children = new ArrayList<>();

    @Schema(readOnly = true)
    private String createdBy = "";

    @Schema(readOnly = true)
    private Instant createdDate;

    @Schema(readOnly = true)
    private String lastModifiedBy;

    @Schema(readOnly = true)
    private Instant lastModifiedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAbbr() {
        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {
        this.nameAbbr = nameAbbr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoFileId() {
        return logoFileId;
    }

    public void setLogoFileId(String logoFileId) {
        this.logoFileId = logoFileId;
    }

    public String getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(String areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEnterpriseWebchatID() {
        return enterpriseWebchatID;
    }

    public void setEnterpriseWebchatID(String enterpriseWebchatID) {
        this.enterpriseWebchatID = enterpriseWebchatID;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public List<OrganizationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationDTO> children) {
        this.children = children;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
}
