package com.example.demo;

import java.util.*;

import org.springframework.stereotype.Component;
@Component
public class MemberRepository {
    private final List<Member> member = new ArrayList<>();

    public Member findMember(int id){ 
        Member b = null;  
        for(int i = 0; i < member.size(); i++){
            if(Integer.valueOf(id).equals(member.get(i).getId())){
                b = member.get(i);
                return b;
            }
        }
        return null;
    }

    public List<Member> findAll(){
        return member;
    }

    public void addMember(int id, String name){
        if(member.contains(findMember(id)))System.out.println("member is already present");
        Member b = new Member(id, name);
        member.add(b);
    }

    public void removeMember(int id){
        if(findMember(id).equals(null)) System.out.println("member of following id is not present");
        member.remove(member.indexOf((findMember(id))));
    }
}
