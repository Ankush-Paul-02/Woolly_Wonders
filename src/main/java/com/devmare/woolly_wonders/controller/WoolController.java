package com.devmare.woolly_wonders.controller;

import com.devmare.woolly_wonders.business.domain.DefaultResponse;
import com.devmare.woolly_wonders.business.dto.WoolDto;
import com.devmare.woolly_wonders.business.service.WoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wool")
public class WoolController {

    private final WoolService woolService;

    @PostMapping("/create/farmer/{farmerId}")
    public ResponseEntity<DefaultResponse> createWool(
            @PathVariable Long farmerId,
            @Valid @RequestBody WoolDto woolDto
    ) {
        return ResponseEntity.ok(
                new DefaultResponse(
                        DefaultResponse.Status.SUCCESS,
                        Map.of(
                                "wool",
                                woolService.createWool(woolDto, farmerId)
                        ),
                        "Wool created successfully"
                )
        );
    }
}
