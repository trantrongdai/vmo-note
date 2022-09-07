package com.vmo.note.dto.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter response dto generic
 * @param <T>
 */
public class FilterResponseDto<T> {
    List<T> data = new ArrayList<>();
    private Long totalElements;
    private Integer totalPages;
    private Integer pageSize;

    public FilterResponseDto() {
    }

    public FilterResponseDto(Long totalElements, Integer totalPages, Integer pageSize, List<T> data) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.data = data;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
