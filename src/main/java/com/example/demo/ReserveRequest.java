package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
public class ReserveRequest {
    
    private int bookId;
    private int memberId;

    public ReserveRequest(){}
}
