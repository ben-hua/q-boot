package org.qboot.modules.system.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.qboot.common.domain.AbstractAuditingEntity;
import org.qboot.common.domain.UUIDv7Generator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sys_depart")
@RegisterForReflection
public class Organization extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(generator = "UUIDv7_generator")
//    @GeneratedValue(strategy = GenerationType.UUID, generator = "UUIDv7_generator")
    @GenericGenerator(name = "UUIDv7_generator", type = UUIDv7Generator.class)
    private String id;

    @Column(name = "depart_name")//TODO 删除name
    private String name;
    @Column(name = "org_type")//TODO 删除name
    private String type;
    @Column(name = "org_code")//TODO 删除name
    private String code;
    //    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "depart_name_en")//TODO 删除name
    private String nameEn;
    @Column(name = "depart_name_abbr")//TODO 删除name
    private String nameAbbr;
    private String description;
    @Column(name = "depart_order")//TODO 删除name
    private int orderNum;

    @Transient//TODO 删除
    private String website;
    @Transient//TODO 删除
    private String logoFileId;
    @Column(length = 4000)
    private String areasOfInterest;
    @Transient//TODO 删除
    private int level;

    //    @Embedded
    @Transient//TODO 删除
    private Contact contact;

    //    @Embedded
    @Transient//TODO 删除
    private Address address;

    @Column(name = "qywx_identifier")//TODO 删除name
    private String enterpriseWebchatID;

    @JsonIgnoreProperties
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    private Organization parent;

    @OneToMany(targetEntity = Organization.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Organization> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String organizationType) {
        this.type = organizationType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(String areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
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

    public String getEnterpriseWebchatID() {
        return enterpriseWebchatID;
    }

    public void setEnterpriseWebchatID(String enterpriseWebchatID) {
        this.enterpriseWebchatID = enterpriseWebchatID;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public enum Status {
        Alive,
        Deceased
    }

}
