package com.devmare.woolly_wonders.data.entity;

import com.devmare.woolly_wonders.data.enums.WoolType;
import com.devmare.woolly_wonders.data.enums.WoolVerificationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wools")
public class Wool extends BaseEntity {

    @NotNull(message = "Wool type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "WOOL_TYPE")
    private WoolType woolType;

    @NotNull(message = "Weight is required")
    @Min(value = 0, message = "Weight must be positive")
    @Column(name = "WEIGHT")
    private double weight;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonIgnore
    private Farmer farmer;

    @Enumerated(EnumType.STRING)
    @Column(name = "VERIFICATION_STATUS")
    private WoolVerificationStatus woolVerificationStatus;
}
