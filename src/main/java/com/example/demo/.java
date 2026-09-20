package com.example.demo;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component 
public class MemberListIntilizer {
    private final MemberRepository memberRepository;
    MemberListIntilizer(MemberRepository memberReposit){
        this.memberRepository = memberReposit;
    }
    @PostConstruct
    public void init(){
        memberRepository.addMember(1, "bill");
        memberRepository.addMember(2, "hill");
        memberRepository.addMember(3, "kll");
        memberRepository.addMember(4, "gill");
        memberRepository.addMember(5, "nill");
    }
    
    
}
