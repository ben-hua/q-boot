package org.qboot.common;

import java.io.IOException;

import org.qboot.common.domain.audit.AuditingEntityListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

// @Provider
public class RequestTenantFilter implements ContainerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(AuditingEntityListener.class);

    // @Context
    // JsonWebToken jwt;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // String username = requestContext.getSecurityContext().getUserPrincipal().getName();

        // final String method = requestContext.getMethod();
        // final String path = info.getPath();
        // final String address = request.remoteAddress().toString();

        // requestContext.getHeaders().add("tenantId", jwt.getClaim("tenantId"));
        // LOG.info("Request principal {}, tenantId= {}", username, jwt.getClaim("tenantId"));
        // LOG.info("Request {}  {} from IP {}", method, path, address);

        /*    if (requestContext.getSecurityContext().getUserPrincipal() != null) {
            requestContext.getHeaders().add("tenantId", jwt.getClaim("tenantId"));
        
            LOG.info("Request principal {}, tenantId= {}",
                    requestContext.getSecurityContext().getUserPrincipal().getName(),
                    jwt.getClaim("tenantId"));
        } */
    }
}
