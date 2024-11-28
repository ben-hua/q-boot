package org.qboot.common.rest;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.qboot.common.rest.vo.ApiError;

import jakarta.ws.rs.core.Response;

public class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<ApiError> mapException(IllegalArgumentException x) {
        return RestResponse.status(Response.Status.BAD_REQUEST,
                new ApiError(Response.Status.BAD_REQUEST.toString(), x.getMessage()));
    }
}
