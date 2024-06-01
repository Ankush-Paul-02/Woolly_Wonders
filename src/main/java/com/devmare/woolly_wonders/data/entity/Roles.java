package com.devmare.woolly_wonders.data.entity;

import com.devmare.woolly_wonders.data.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Roles {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Role name;
}
