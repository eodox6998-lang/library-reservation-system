package com.example.demo;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {
    @Id
    private int id;
    public enum Status{
        PENDING,
        CONFIRMED,
        CANCALLED
    }
    private Book book;
    private Member member;
    private Status State;
    private LocalDateTime dueDate;
}
