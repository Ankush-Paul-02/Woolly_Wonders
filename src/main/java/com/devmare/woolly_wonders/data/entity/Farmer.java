package com.devmare.woolly_wonders.data.entity;

import com.devmare.woolly_wonders.data.enums.FarmerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "farmers")
public class Farmer extends Profile {

    @Column(name = "IS_GOVT_CERTIFIED", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isGovtCertified;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private FarmerStatus status;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wool> wools;

    @OneToOne(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
    private RefreshToken refreshToken;

}
