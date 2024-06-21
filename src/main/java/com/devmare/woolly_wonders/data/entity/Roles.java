package com.devmare.woolly_wonders.data.entity;

import com.devmare.woolly_wonders.data.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "roles")
public class Roles extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role name;
}
