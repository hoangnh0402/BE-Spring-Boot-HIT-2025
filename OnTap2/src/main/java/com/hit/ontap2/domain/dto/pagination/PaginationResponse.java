package com.hit.ontap2.domain.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private PagingMeta meta;
}

