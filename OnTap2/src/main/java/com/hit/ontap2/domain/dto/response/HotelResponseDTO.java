package com.hit.ontap2.domain.dto.response;

import lombok.Data;

@Data
public class HotelResponseDTO {
    private Long id;
    private String name;
    private String location;
    private Double price;
}

