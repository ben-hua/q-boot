package org.qboot.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.qboot.common.domain.audit.AuditingEntityListener;

import java.time.Instant;

/**
 * Base abstract class for entities which will hold definitions for created, last modified, created by,
 * last modified by attributes.
 */

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractAuditingEntity {

    @Schema(readOnly = true)
    @Column(nullable = false, updatable = false, name = "create_by") //TODO 删除name
    private String createdBy;

    @Schema(readOnly = true)
    @CreationTimestamp
    @Column(updatable = false, name = "create_time")//TODO 删除name
    private Instant createdDate = Instant.now();

    @Schema(readOnly = true)
    @Column(length = 50, name = "update_by")//TODO 删除name
    private String lastModifiedBy;

    @Schema(readOnly = true)
    @UpdateTimestamp
    @Column(nullable = false, name = "update_time")//TODO 删除name
    private Instant lastModifiedDate = Instant.now();

    // @Schema(readOnly = true)
    // @Column(nullable = false, updatable = false)
    // @TenantId
    public String tenantId;

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

    // public String getTenantId() {
    //     return tenantId;
    // }

    // public void setTenantId(String tenantId) {
    //     this.tenantId = tenantId;
    // }

}
