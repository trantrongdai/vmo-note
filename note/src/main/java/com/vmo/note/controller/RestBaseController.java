package com.vmo.note.controller;

import com.vmo.note.constants.PagingConstant;
import com.vmo.note.enums.StatusCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Restbase controller
 */
public abstract class RestBaseController {
    /**
     * Restful Response Code
     */
    protected static final StatusCode SUCCESS = StatusCode.SUCCESS;
    protected static final StatusCode FAILED = StatusCode.FAILED;

    /**
     * Pagination
     */
    protected static final int DEFAULT_PAGE_SIZE = PagingConstant.DEFAULT_PAGE_SIZE;
    protected static final int MAX_PAGE_SIZE = 100000; //TODO max page is too large, maybe a risk data issue

    protected Pageable pageRequest(Integer pageIndex, Integer pageSize, Sort sort) {
        if (pageIndex == null) {
            pageIndex = 0;
        }

        if (pageSize == null || pageSize < 0 || pageSize > MAX_PAGE_SIZE) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return PageRequest.of(pageIndex, pageSize, sort);
    }

    /**
     * Create a pagination for query
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    protected Pageable pageRequest(Integer pageIndex, Integer pageSize) {
        return pageRequest(pageIndex, pageSize, Sort.unsorted());
    }

}
