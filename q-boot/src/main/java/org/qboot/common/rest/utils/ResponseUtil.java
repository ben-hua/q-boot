package org.qboot.common.rest.utils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

public interface ResponseUtil {
    static Response wrapOrNotFound(Optional<?> maybeResponse) {
        return wrapOrNotFound(maybeResponse, Collections.emptyMap());
    }

    static Response wrapOrNotFound(Optional<?> maybeResponse,
            Map<String, String> headersIfPresent) {
        return maybeResponse.map(value -> {
            Response.ResponseBuilder response = Response.ok(value);
            headersIfPresent.forEach(response::header);
            return response.build();
        }).orElseThrow(NotFoundException::new);
    }
}
