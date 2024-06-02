package com.devmare.woolly_wonders.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long farmerId;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotNull(message = "Pin code cannot be null")
    @Digits(integer = 6, fraction = 0, message = "Pin code must be a 6-digit number")
    private Long pinCode;

    private String latitude;

    private String longitude;

    @Override
    public String toString() {
        if (address == null && city == null && state == null && pinCode == null) return "Address not updated";
        return address + ", " + city + ", " + state + ", " + pinCode;
    }
}
