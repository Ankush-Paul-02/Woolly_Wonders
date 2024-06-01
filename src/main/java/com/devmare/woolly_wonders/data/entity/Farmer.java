package com.devmare.woolly_wonders.data.entity;

import com.devmare.woolly_wonders.data.enums.FarmerStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "farmers")
public class Farmer extends Profile {

    @Column(name = "IS_GOVT_CERTIFIED", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isGovtCertified;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(32) DEFAULT 'ACTIVE'")
    private FarmerStatus status;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wool> wools;
}
