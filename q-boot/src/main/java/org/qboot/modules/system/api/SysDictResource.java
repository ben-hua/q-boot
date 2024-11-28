package org.qboot.modules.system.api;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.resteasy.reactive.links.InjectRestLinks;
import io.quarkus.resteasy.reactive.links.RestLink;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.qboot.common.Paged;
import org.qboot.common.domain.QueryVM;
import org.qboot.common.rest.utils.PaginationUtil;
import org.qboot.common.rest.vo.PageRequest;
import org.qboot.common.rest.vo.Result;
import org.qboot.modules.system.core.application.SysDictService;
import org.qboot.modules.system.core.domain.SysDict;
import org.qboot.modules.system.core.domain.SysDictItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Path("/sys/dicts")
@Produces(value = {"application/json"})
public class SysDictResource {
    private final Logger LOG = LoggerFactory.getLogger(SysDictResource.class);

    @Inject
    SysDictService sysDictService;


    @GET
    // @InjectRestLinks
    // @RestLink(entityType = SysDict.class, rel = "list")
    // @APIResponse(responseCode = "200", content = {
    // 		@Content(mediaType = "application/json", schema = @Schema(implementation = SysDict.class, type = SchemaType.ARRAY)) })
    public Response list(
            @QueryParam("dictName") String dictName,
            @QueryParam("dictCode") String dictCode,
            @BeanParam PageRequest pageRequest,
            @Context UriInfo uriInfo) {
        Result<Paged<SysDict>> result = new Result<>();
        var page = pageRequest.toPage();
        var sort = pageRequest.toSort();
        Paged<SysDict> pagedList;

        QueryVM queryVM = toQueryVM(dictName, dictCode);
        LOG.info("queryVM: {},{} ", dictName, dictCode


        );
        // LOG.info("queryVM: {},{} ", queryVM.getWhere(), queryVM.getParams().toString());
        PanacheQuery<SysDict> livingOrganizations = SysDict.find(queryVM.getWhere(), sort, queryVM.getParams())
                .page(page);

        var totalCount = livingOrganizations.count();
        var pageCount = livingOrganizations.pageCount();

        pagedList = new Paged<>(page.index, page.size, totalCount, pageCount,
                livingOrganizations.list());

        //.map(t -> OrganizationDTOMapper.INSTANCE.toDto(t));

        result.setSuccess(true);
        result.setResult(pagedList);

        var response = Response.ok().entity(result);
        response = PaginationUtil.withPaginationInfo(response, uriInfo, pagedList);

        return response.build();
    }

    private QueryVM toQueryVM(String dictName, String dictCode) {
        QueryVM queryVM = new QueryVM();
        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();

        hashMap.put("delFlag", "0");
        arrayList.add("delFlag=:delFlag");

        if (dictName != null && !dictName.isBlank()) {
            arrayList.add("dictName=:dictName");
            hashMap.put("dictName", dictName);
        }
        if (dictCode != null && !dictCode.isBlank()) {
            arrayList.add("dictCode=:dictCode");
            hashMap.put("dictCode", dictCode);
        }

        queryVM.setWhere(String.join(" AND ", arrayList));
        queryVM.setParams(hashMap);
        return queryVM;
    }

    @POST
    @ResponseStatus(201)
    @InjectRestLinks
    @RestLink(entityType = SysDict.class, rel = "add")
    @APIResponse(responseCode = "201", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SysDict.class))})
    public Response add(@Valid SysDict sysDict, @Context UriInfo uriInfo) {
        Result<SysDict> result = new Result<SysDict>();
        sysDictService.save(sysDict);

        var location = UriBuilder.fromPath(uriInfo.getPath()).path(sysDict.getId().toString()).build();
        result.success("保存成功");
        var response = Response.created(location).entity(result);

        return response.build();
    }

    @Path("/{id}")
    @PUT
    @RestLink(entityType = SysDict.class, rel = "update")
    public Result<SysDict> update(@PathParam("id") String id, SysDict dict) {
        dict.setId(id);
        sysDictService.update(dict);
        return Result.ok();  // FIXME 替换成void,前台通过判断httpcode 204完成验证
    }

    @Path("/{id}")
    @DELETE
    @ResponseStatus(204)  // FIXME 替换成void,前台通过判断httpcode 204完成验证
    public Result<SysDict> delete(@PathParam("id") String id) {
        sysDictService.delete(id);
        return Result.ok();
    }

    @DELETE
    @ResponseStatus(204)  // FIXME 替换成void,前台通过判断httpcode 204完成验证
    public Result<SysDict> batchDelete(@QueryParam("ids") List<String> ids) { //FIXME 这里逗号分隔的参数没有展开成列表
        long count = sysDictService.delete(ids);
        return Result.ok("deleted count:" + count);
    }

    @Path("/{dictId}/items")
    @GET
    public Response listItems(
            @PathParam("dictId") String dictId,
            @Context UriInfo uriInfo) {
        Result<List<SysDictItem>> result = new Result<>();
        SysDict sysdict = SysDict.findById(dictId);

        if (sysdict == null) {
            throw new NotFoundException();
        }

        result.setSuccess(true);
        result.setResult(sysdict.getItems());

        var response = Response.ok().entity(result);
        return response.build();
    }

    @Path("/{dictId}/items")
    @POST
    public Response addItems(
            @PathParam("dictId") String dictId,
            SysDictItem dictItem,
            @Context UriInfo uriInfo) {
        Result<List<SysDictItem>> result = new Result<>();

        SysDict dict = sysDictService.addItem(dictId, dictItem);

        result.setSuccess(true);
        result.setResult(dict.getItems());

        var response = Response.ok().entity(result);
        return response.build();
    }


    @Path("/{dictId}/items/{itemId}")
    @POST
    public Response deleteItem(
            @PathParam("dictId") String dictId,
            @PathParam("itemId") String itemId,
            @Context UriInfo uriInfo) {
        Result<List<SysDictItem>> result = new Result<>();

        SysDict dict = sysDictService.deleteItem(dictId, itemId);

        result.setSuccess(true);
        result.setResult(dict.getItems());

        var response = Response.ok().entity(result);
        return response.build();
    }

    @Path("/{dictId}/items/{itemId}")
    @PUT
    public Response updateItem(
            @PathParam("dictId") String dictId,
            @PathParam("itemId") String itemId,
            SysDictItem dictItem,
            @Context UriInfo uriInfo) {
        Result<List<SysDictItem>> result = new Result<>();

        SysDict dict = sysDictService.updateItem(dictId, itemId, dictItem);

        result.setSuccess(true);
        result.setResult(dict.getItems());

        var response = Response.ok().entity(result);
        return response.build();
    }
}
