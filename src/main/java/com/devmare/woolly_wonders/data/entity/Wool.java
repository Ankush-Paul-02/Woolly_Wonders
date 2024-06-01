package com.devmare.woolly_wonders.data.entity;

import com.devmare.woolly_wonders.data.enums.WoolType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "wools")
public class Wool extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "WOOL_TYPE")
    private WoolType woolType;

    @Column(name = "WEIGHT")
    private double weight;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;
}
