package com.example.demo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
public class Book {
    @Id
    private int id;
    private String title;
    private String author;
    private boolean avaliable;
}
