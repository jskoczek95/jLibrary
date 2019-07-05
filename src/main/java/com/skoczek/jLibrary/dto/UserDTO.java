package com.skoczek.jLibrary.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Set<BookDTO> bookDTOS;
}
