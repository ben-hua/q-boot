package org.qboot.common.rest.vo;

import java.util.ArrayList;
import java.util.List;

public class ErrorDetail {
    private String code;
    private String message;
    private String target;
    private List<ErrorDetail> details = new ArrayList<>();

    public ErrorDetail(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorDetail(String code, String message, String target) {
        this.code = code;
        this.message = message;
        this.target = target;
    }

    public ErrorDetail(String code, String message, String target, List<ErrorDetail> details) {
        this.code = code;
        this.message = message;
        this.target = target;
        this.details.addAll(details);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<ErrorDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorDetail> details) {
        this.details = details;
    }
}
