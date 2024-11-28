package org.qboot.modules.system.core.application.query;

import java.util.UUID;


import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.QueryParam;
import org.qboot.modules.system.core.domain.Organization;

public class OrganizationQuery {
    @QueryParam(value = "id")
    private UUID id;

    @QueryParam(value = "name")
    private String name;

    @QueryParam(value = "code")
    private String code;

    @QueryParam(value = "status")
    private Organization.Status status;

    @QueryParam(value = "parentId")
    private UUID parentId;

    @HeaderParam(value = "tenantId")
    private String tenantId;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Organization.Status getStatus() {
        return status;
    }

    public void setStatus(Organization.Status status) {
        this.status = status;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getParentId() {
        return parentId;
    }

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }
}
