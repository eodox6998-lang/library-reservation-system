package com.example.demo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    @Id
    private int id;
    private String name;
}
