package org.qboot.common.domain.audit;

import java.time.Instant;

import org.qboot.common.domain.AbstractAuditingEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

/**
 * 实现参照
 * @see https://github.com/quarkusio/quarkus/blob/2.16.1.Final/integration-tests/jpa/src/main/java/io/quarkus/it/jpa/entitylistener/EntityWithListenerThroughEntityListenersAnnotation.java
 */
@RequestScoped
public class AuditingEntityListener {

    private static final Logger LOG = LoggerFactory.getLogger(AuditingEntityListener.class);

    @Inject
    SecurityIdentity identity;

    public AuditingEntityListener() {
        // int ordinal;
        // if (!ClientProxy.class.isAssignableFrom(getClass())) { // Disregard CDI proxies extending this class
        //     ordinal = instanceOrdinalSource.getAndIncrement();
        // } else {
        //     ordinal = -1;
        // }
        // this.ref = ReceivedEvent.objectRef(AuditingEntityListener.class, ordinal);
    }

    @PrePersist
    public void prePersist(AbstractAuditingEntity entity) {
        // receiveEvent(PrePersist.class, entity); 

        String userName = identity.getPrincipal().getName();
        entity.setCreatedBy(userName);
        entity.setLastModifiedBy(userName);
        // entity.setTenantId(jwt.getClaim("tenantId"));
        LOG.info("prePersist entity:{},userName:{}",
                entity.getClass().getName(), userName);
    }

    @PostPersist
    public void postPersist(AbstractAuditingEntity entity) {
        // receiveEvent(PostPersist.class, entity);
        LOG.info("postPersist entity:{}", entity.getClass().getName());
    }

    @PreUpdate
    public void preUpdate(AbstractAuditingEntity entity) {
        // receiveEvent(PreUpdate.class, entity);

        String userName = identity.getPrincipal().getName();
        entity.setLastModifiedBy(userName);
        entity.setLastModifiedDate(Instant.now());
        // entity.setTenantId(jwt.getClaim("tenantId"));
        LOG.info("preUpdate entity:{}", entity.getClass().getName());
    }

    @PostUpdate
    public void postUpdate(AbstractAuditingEntity entity) {
        // receiveEvent(PostUpdate.class, entity);
        LOG.info("postUpdate entity:{}", entity.getClass().getName());
    }

    @PreRemove
    public void preRemove(AbstractAuditingEntity entity) {
        // receiveEvent(PreRemove.class, entity);
        // entity.setTenantId(jwt.getClaim("tenantId"));
        LOG.info("preRemove entity:{}", entity.getClass().getName());
    }

    @PostRemove
    public void postRemove(AbstractAuditingEntity entity) {
        // receiveEvent(PostRemove.class, entity);
        LOG.info("postRemove entity:{}", entity.getClass().getName());
    }

    @PostLoad
    public void postLoad(AbstractAuditingEntity entity) {
        // receiveEvent(PostLoad.class, entity);
        LOG.info("postLoad entity:{}", entity.getClass().getName());
    }
}