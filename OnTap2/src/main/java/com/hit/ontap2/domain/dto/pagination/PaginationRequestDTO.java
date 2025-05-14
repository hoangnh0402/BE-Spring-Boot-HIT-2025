package com.hit.ontap2.domain.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequestDTO {
    private int pageNum;
    private int pageSize;
}

