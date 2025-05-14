package com.hit.ontap2.domain.mapper;

import com.hit.ontap2.domain.dto.request.HotelCreatDTO;
import com.hit.ontap2.domain.dto.response.HotelResponseDTO;
import com.hit.ontap2.domain.entity.Hotel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelResponseDTO toDTO(Hotel hotel);
    List<HotelResponseDTO> toDTOList(List<Hotel> hotels);
    Hotel toHotel(HotelCreatDTO hotelCreatDTO);
}

