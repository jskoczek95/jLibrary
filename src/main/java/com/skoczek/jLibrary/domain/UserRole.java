package com.skoczek.jLibrary.domain;

import com.skoczek.jLibrary.domain.enums.Roles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Roles role;
    private String description;
}