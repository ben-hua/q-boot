package org.qboot;

import io.quarkus.security.PermissionsAllowed;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Path("/secured")
public class TokenSecuredResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    SecurityIdentity identity;

    @GET
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext ctx) {
        return getResponseString(ctx);
    }

    @GET
    @Path("genToken")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String genToken(@RestQuery List<String> groups) {
        return Jwt.issuer("https://example.com/issuer")
                .upn("jdoe@quarkus.io")
                .groups(new HashSet<>(groups))
                .claim(Claims.birthdate.name(), "2001-07-13")
                .claim("permissions", "delete")
                .scope(new HashSet<>(Arrays.asList("delete", "query")))
                .sign();
    }

    @GET
    @Path("roles-allowed")
    @RolesAllowed({"User", "Admin"})
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRolesAllowed(@Context SecurityContext ctx) {
        return getResponseString(ctx) + ", birthdate: " + jwt.getClaim("birthdate").toString()
                + " ,groups: " + Arrays.toString(jwt.getGroups().toArray());
    }

    @GET
    @Path("permission-allowed")
    @PermissionsAllowed({"delete"})
    @Produces(MediaType.TEXT_PLAIN)
    public String permissionAllowed(@Context SecurityContext ctx) {
        return getResponseString(ctx) + ", birthdate: " + jwt.getClaim("birthdate").toString()
                + " ,groups: " + Arrays.toString(jwt.getGroups().toArray());
    }

    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) {
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) {
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName();
        }

        return String.format("hello %s,"
                        + " isHttps: %s,"
                        + " authScheme: %s,"
                        + " hasJWT: %s",
                name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt());
    }

    private boolean hasJwt() {
        return jwt.getClaimNames() != null;
    }
}