package org.qboot.common.domain;

import java.util.HashMap;

public class QueryVM {
    private String where;
    private HashMap<String, Object> params;

    public QueryVM() {
    }

    public QueryVM(String where, HashMap<String, Object> params) {
        this.where = where;
        this.params = params;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }
}
