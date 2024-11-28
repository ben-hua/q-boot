package com.qadmin.demo.sys.interfaces.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qadmin.demo.common.Paged;
import com.qadmin.demo.common.domain.QueryVM;
import com.qadmin.demo.common.rest.utils.PaginationUtil;
import com.qadmin.demo.common.rest.vo.PageRequest;
import com.qadmin.demo.sys.application.OrganizationService;
import com.qadmin.demo.sys.application.dto.OrganizationDTO;
import com.qadmin.demo.sys.application.mapper.OrganizationDTOMapper;
import com.qadmin.demo.sys.application.query.OrganizationQuery;
import com.qadmin.demo.sys.domain.model.Organization;
import com.qadmin.demo.sys.domain.model.OrganizationRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.rest.data.panache.runtime.sort.SortQueryParamValidator;
import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/api/sys/organizations")
@Authenticated
public class OrganizationResource {
    private final Logger LOG = LoggerFactory.getLogger(OrganizationResource.class);

    @Inject
    OrganizationRepository organizationRepository;

    @Inject
    OrganizationService orgService;

    @POST
    @ResponseStatus(201)
    @InjectRestLinks
    @RestLink(entityType = Organization.class, rel = "add")
    @APIResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Organization.class)) })
    public Response add(@Valid OrganizationDTO cmd, @Context UriInfo uriInfo) {
        Organization org = OrganizationDTOMapper.INSTANCE.fromDTO(cmd);
        OrganizationDTO result = orgService.addOrg(org, cmd.getParentId());

        var location = UriBuilder.fromPath(uriInfo.getPath()).path(result.getId().toString()).build();
        var response = Response.created(location).entity(result);

        return response.build();
    }

    @Path("/{id}")
    @PUT
    @RestLink(entityType = Organization.class, rel = "update")
    public void update(@PathParam("id") UUID id, OrganizationDTO cmd) {
        Organization org = OrganizationDTOMapper.INSTANCE.fromDTO(cmd);
        org.setId(id);
        orgService.update(org);
        return;
    }

    @Path("/{id}")
    @DELETE
    @ResponseStatus(204)
    public void delete(@PathParam("id") UUID id) {
        orgService.delete(id);
    }

    @GET
    @InjectRestLinks
    @Produces(value = { "application/json" })
    @RestLink(entityType = OrganizationDTO.class, rel = "list")
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = OrganizationDTO.class, type = SchemaType.ARRAY)) })
    @SortQueryParamValidator
    @Transactional
    public Response list(@BeanParam OrganizationQuery parameters,
            @BeanParam PageRequest pageRequest, @Context UriInfo uriInfo) {

        var page = pageRequest.toPage();
        var sort = pageRequest.toSort();
        Paged<OrganizationDTO> result;

        QueryVM queryVM = toQueryVM(parameters);
        // LOG.info("queryVM: {},{} ", queryVM.getWhere(), queryVM.getParams().toString());
        PanacheQuery<Organization> livingOrganizations = organizationRepository
                .find(queryVM.getWhere(), sort, queryVM.getParams()).page(page);

        var totalCount = livingOrganizations.count();
        var pageCount = livingOrganizations.pageCount();

        result = new Paged<>(page.index, page.size, totalCount, pageCount,
                livingOrganizations.list()).map(t -> OrganizationDTOMapper.INSTANCE.toDto(t));

        var response = Response.ok().entity(result.value);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, result);

        return response.build();
    }

    @Path(value = "/{id}")
    @GET
    @Produces(value = { "application/json" })
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = OrganizationDTO.class)) })
    @RestLink(entityType = OrganizationDTO.class, rel = "self")
    public OrganizationDTO get(@PathParam("id") UUID id) {
        Optional<Organization> orgOp = organizationRepository.findByIdOptional(id);
        if (orgOp.isEmpty()) {
            return null;
        }
        Organization org = orgOp.get();
        OrganizationDTO organizationDTO = OrganizationDTOMapper.INSTANCE.toDto(org);

        return organizationDTO;
    }

    @GET
    @Path("/count")
    public long count() {
        return organizationRepository.count();
    }

    private QueryVM toQueryVM(OrganizationQuery parameters) {
        QueryVM queryVM = new QueryVM();
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();

        if (parameters.getName() != null && !parameters.getName().isBlank()) {
            arrayList.add("name=:name");
            hashMap.put("name", parameters.getName());
        }
        if (parameters.getCode() != null && !parameters.getCode().isBlank()) {
            arrayList.add("code=:code");
            hashMap.put("code", parameters.getCode());
        }
        if (parameters.getStatus() != null) {
            arrayList.add("status=:status");
            hashMap.put("status", parameters.getStatus());
        }
        if (parameters.getId() != null) {
            arrayList.add("id=:id");
            hashMap.put("id", parameters.getId());
        }
        if (parameters.getTenantId() != null && !parameters.getTenantId().isBlank()) {
            arrayList.add("tenantId=:tenantId");
            hashMap.put("tenantId", parameters.getTenantId());
        }
        if (parameters.getParentId() != null) {
            if (parameters.getParentId()
                    .equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))) {
                arrayList.add("parent.id is null");

            } else {
                arrayList.add("parent.id=:parentId");
                hashMap.put("parentId", parameters.getParentId());
            }
        }
        if (arrayList.size() > 0) {
            queryVM.setWhere(String.join(" AND ", arrayList));
            queryVM.setParams(hashMap);
        }
        return queryVM;
    }

}
