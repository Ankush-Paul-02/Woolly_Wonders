package com.devmare.woolly_wonders.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
