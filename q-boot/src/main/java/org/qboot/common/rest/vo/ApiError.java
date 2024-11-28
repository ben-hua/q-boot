package org.qboot.common.rest.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiError {
    private ErrorDetail error;

    public ApiError() {
    }

    public ApiError(String code, String message) {
        this.error = new ErrorDetail(code, message);
    }

    public ApiError(String code, String message, String target) {
        this.error = new ErrorDetail(code, message, target);
    }

    public ApiError(String code, String message, String target, List<ErrorDetail> details) {
        this.error = new ErrorDetail(code, message, target, details);
    }

    public ErrorDetail getError() {
        return error;
    }

    public void setError(ErrorDetail error) {
        this.error = error;
    }
}
