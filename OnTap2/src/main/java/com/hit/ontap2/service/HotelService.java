package com.hit.ontap2.service;

import com.hit.ontap2.domain.dto.request.HotelCreatDTO;
import com.hit.ontap2.domain.dto.response.HotelResponseDTO;
import com.hit.ontap2.domain.dto.pagination.PaginationRequestDTO;
import com.hit.ontap2.domain.dto.pagination.PaginationResponse;

public interface HotelService {
    PaginationResponse<HotelResponseDTO> getAllHotels(PaginationRequestDTO request);
    HotelResponseDTO createHotel(HotelCreatDTO hotelCreatDTO);
}
