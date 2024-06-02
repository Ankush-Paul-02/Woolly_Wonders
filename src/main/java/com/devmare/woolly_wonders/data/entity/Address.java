package com.devmare.woolly_wonders.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Address")
@Table(name = "address")
public class Address extends BaseEntity {

    private String address;
    private String city;
    private String state;
    private Long pinCode;
    private String latitude;
    private String longitude;

    @Override
    public String toString() {
        if (address == null && city == null && state == null && pinCode == null) return "Address not updated";
        return address + ", " + city + ", " + state + ", " + pinCode;
    }
}
