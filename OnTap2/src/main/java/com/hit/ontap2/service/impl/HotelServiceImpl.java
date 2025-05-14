package com.hit.ontap2.service.impl;

import com.hit.ontap2.constant.ErrorMessage;
import com.hit.ontap2.domain.dto.request.HotelCreatDTO;
import com.hit.ontap2.domain.dto.response.HotelResponseDTO;
import com.hit.ontap2.domain.dto.pagination.PaginationRequestDTO;
import com.hit.ontap2.domain.dto.pagination.PaginationResponse;
import com.hit.ontap2.domain.dto.pagination.PagingMeta;
import com.hit.ontap2.domain.entity.Hotel;
import com.hit.ontap2.domain.mapper.HotelMapper;
import com.hit.ontap2.exception.ResourceNotFoundException;
import com.hit.ontap2.repository.HotelRepository;
import com.hit.ontap2.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    @Override
    public PaginationResponse<HotelResponseDTO> getAllHotels(PaginationRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPageNum(), request.getPageSize(), Sort.by("createdDate").descending());
        Page<Hotel> pageResult = hotelRepository.findAll(pageable);

        if (pageResult.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessage.Hotel.ERR_NOT_FOUND);
        }

        List<HotelResponseDTO> hotelResponseDTOS = hotelMapper.toDTOList(pageResult.getContent());
        PaginationResponse<HotelResponseDTO> response = new PaginationResponse<>(
                hotelResponseDTOS,
                new PagingMeta(pageResult.getTotalElements(), pageResult.getTotalPages(), request.getPageNum() + 1, request.getPageSize(), "createdDate", "desc")
        );
        return response;
    }

    @Override
    public HotelResponseDTO createHotel(HotelCreatDTO hotelCreatDTO) {
        Hotel hotel = hotelMapper.toHotel(hotelCreatDTO);
        hotelRepository.save(hotel);
        return hotelMapper.toDTO(hotel);
    }
}
