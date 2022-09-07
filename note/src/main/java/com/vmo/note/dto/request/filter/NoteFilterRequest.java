package com.vmo.note.dto.request.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * Student filter request
 */
public class NoteFilterRequest extends BaseFilter {
    private Map<String, String> sortBy = new HashMap<>();

    public NoteFilterRequest() {
    }

    public Map<String, String> getSortBy() {
        return sortBy;
    }

    public void setSortBy(Map<String, String> sortBy) {
        this.sortBy = sortBy;
    }
}
