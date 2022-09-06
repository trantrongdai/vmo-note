package com.cmc.dto.request.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * Student filter request
 */
public class StudentFilterRequest extends BaseFilter {
    private Map<String, String> sortBy = new HashMap<>();

    public StudentFilterRequest() {
    }

    public Map<String, String> getSortBy() {
        return sortBy;
    }

    public void setSortBy(Map<String, String> sortBy) {
        this.sortBy = sortBy;
    }
}
