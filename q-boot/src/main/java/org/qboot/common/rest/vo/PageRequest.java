package org.qboot.common.rest.vo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.validation.constraints.Positive;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

public class PageRequest {

    // @QueryParam("page")  // FIXME jeecg  切换字段名
    @QueryParam("pageNo")
    @DefaultValue("1") //FIXME jeecg 这里改为0
    @Positive
    private int index;

    // @QueryParam("size")
    @QueryParam("pageSize")
    @DefaultValue("10")
    @Positive
    private int size;

    @QueryParam("sort")
    private List<String> sort;

    @QueryParam("keywords")
    private String keywords;

    public Page toPage() {
        return Page.of(index-1, size); // FIXME jeecg 改为Page.of(index, size);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getSort() {
        return sort;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    public Sort toSort() {
        Sort resultSort = Sort.empty();
        LinkedList<String> linkedList = new LinkedList<>();
        for (String currentSort : sort) {
            var sortDetail = Arrays.asList(currentSort.split(","));
            linkedList.addAll(sortDetail);
        }

        for (String item : linkedList) {
            var sortDetails = item.split(" ");
            var columnName = sortDetails[0];
            var direction = toDirection(sortDetails.length > 1 ? sortDetails[1] : null);

            if (columnName.startsWith("-")) {
                columnName = columnName.substring(1);
                direction = Sort.Direction.Descending;
            }

            resultSort.and(columnName, direction);
        }

        return resultSort;
    }

    private Sort.Direction toDirection(String direction) {
        return "desc".equals(direction) ? Sort.Direction.Descending : Sort.Direction.Ascending;
    }

    @Override
    public String toString() {
        return "PageRequest{" + "page=" + index + ", size=" + size + '}';
    }

}
