package com.skoczek.jLibrary.dto;

import com.skoczek.jLibrary.domain.enums.BookCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private String title;
    private String author;
    private BookCategory bookCategory;
    private UserDTO userDTO;
}
