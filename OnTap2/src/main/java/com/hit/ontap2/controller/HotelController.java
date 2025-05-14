package com.hit.ontap2.controller;

import com.hit.ontap2.domain.dto.request.HotelCreatDTO;
import com.hit.ontap2.domain.dto.response.HotelResponseDTO;
import com.hit.ontap2.domain.dto.pagination.PaginationRequestDTO;
import com.hit.ontap2.domain.dto.pagination.PaginationResponse;
import com.hit.ontap2.domain.entity.Hotel;
import com.hit.ontap2.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "Lấy danh sách khách sạn theo trang")
    @GetMapping("/getAll")
    public ResponseEntity<PaginationResponse<HotelResponseDTO>> getAllHotels(
            @Parameter(description = "Số trang (bắt đầu từ 0)") @RequestParam int page,
            @Parameter(description = "Kích thước trang") @RequestParam int size) {
        PaginationRequestDTO request = new PaginationRequestDTO(page, size);
        return ResponseEntity.ok(hotelService.getAllHotels(request));
    }

    @Operation(summary = "Tạo mới khách sạn")
    @PostMapping("/create")
    public ResponseEntity<HotelResponseDTO> createHotel(
            @Parameter(description = "Thông tin khách sạn cần tạo") @RequestBody HotelCreatDTO hotelCreatDTO) {
        return ResponseEntity.ok(hotelService.createHotel(hotelCreatDTO));
    }
}
