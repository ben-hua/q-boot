package org.qboot.common;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

public class Paged<T> {

    @JsonProperty("current")
    public final long index;
    public final long size;

    @JsonProperty("total")
    public final long totalCount;
    @JsonProperty("pages")
    public final long pageCount;
    @JsonProperty("records")
    public final List<T> value;

    public Paged(PanacheQuery<T> query) {
        this(query.page().index, query.page().size, query.count(), query.pageCount(), query.list());
    }

    public Paged(long index, long size, long totalCount, long pageCount, List<T> value) {
        this.index = index;
        this.size = size;
        this.totalCount = totalCount;
        this.pageCount = pageCount;
        this.value = Collections.unmodifiableList(value);
    }

    public <R> Paged<R> map(Function<T, R> mapper) {
        var mapped = value.stream().map(mapper).collect(Collectors.toList());
        return new Paged<R>(this.index, this.size, this.totalCount, this.pageCount, mapped);
    }
}
