package com.qadmin.demo.sys.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qadmin.demo.common.domain.AbstractAuditingEntity;
import com.qadmin.demo.common.domain.UUIDv7Generator;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sys_organization")
@RegisterForReflection
public class Organization extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID, generator = "UUIDv7_generator")
    @GenericGenerator(name = "UUIDv7_generator", type = UUIDv7Generator.class)
    private UUID id;

    private String name;
    private String type;
    private String code;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String nameEn;
    private String nameAbbr;
    private String description;
    private int orderNum;

    private String website;
    private String logoFileId;
    @Column(length = 4000)
    private String areasOfInterest;
    private int level;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;

    private String enterpriseWebchatID;

    @JsonIgnoreProperties
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    private Organization parent;

    @OneToMany(targetEntity = Organization.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Organization> children = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
