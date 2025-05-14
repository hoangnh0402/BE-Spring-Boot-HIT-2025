package com.hit.ontap2.domain.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingMeta {
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;
    private String sortBy;
    private String sortType;
}
