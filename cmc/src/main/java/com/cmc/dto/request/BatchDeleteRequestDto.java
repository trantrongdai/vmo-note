package com.cmc.dto.request;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Batch delete request dto
 */
public class BatchDeleteRequestDto {
    @NotNull(message = "Ids is required")
    private List<Long> ids;

    public BatchDeleteRequestDto() {

    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
