package com.hit.ontap2.domain.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class HotelCreatDTO {
    private String name;

    private String location;

    private Double price;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
