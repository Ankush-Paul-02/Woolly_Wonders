package com.devmare.woolly_wonders.controller;

import com.devmare.woolly_wonders.business.domain.DefaultResponse;
import com.devmare.woolly_wonders.business.service.AddressService;
import com.devmare.woolly_wonders.data.entity.Address;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@Validated
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/create/{farmerId}")
    public ResponseEntity<DefaultResponse> createAddress(
            @PathVariable Long farmerId,
            @Valid @RequestBody Address address
    ) {
        return ResponseEntity.ok(
                new DefaultResponse(
                        DefaultResponse.Status.SUCCESS,
                        Map.of(
                                "address",
                                addressService.createAddress(farmerId, address)
                        ),
                        "Address created successfully"
                )
        );
    }
}
