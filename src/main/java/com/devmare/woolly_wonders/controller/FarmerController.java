package com.devmare.woolly_wonders.controller;

import com.devmare.woolly_wonders.business.domain.DefaultResponse;
import com.devmare.woolly_wonders.business.dto.FarmerDto;
import com.devmare.woolly_wonders.business.dto.UpdateFarmerDto;
import com.devmare.woolly_wonders.business.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/farmer")
public class FarmerController {

    private final FarmerService farmerService;

    @PutMapping("/update/{farmerId}")
    public ResponseEntity<DefaultResponse> updateFarmer(
            @PathVariable Long farmerId,
            @RequestBody UpdateFarmerDto updateFarmerDto
    ) {
        FarmerDto farmerDto = farmerService.updateFarmer(farmerId, updateFarmerDto);
        return ResponseEntity.ok(
                new DefaultResponse(
                        DefaultResponse.Status.SUCCESS,
                        Map.of("farmer", farmerDto),
                        "Farmer updated successfully"
                )
        );
    }
}
