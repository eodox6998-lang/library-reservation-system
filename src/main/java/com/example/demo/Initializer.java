package com.example.demo;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component 
public class Initializer {
    private final  MemberRepository memberRepository;
    Initializer(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @PostConstruct
    public void init(){
       memberRepository.addMember(1, "pill");
       memberRepository.addMember(2, "piFF");
       memberRepository.addMember(3, "pl");
       memberRepository.addMember(4, "OLL");
       memberRepository.addMember(5, "KILL");
}
}