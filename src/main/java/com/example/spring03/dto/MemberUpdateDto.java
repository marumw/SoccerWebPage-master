package com.example.spring03.dto;

import com.example.spring03.domain.Member;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberUpdateDto {

    private Integer id;
    private String password;
    private String email;
    
    public Member toEntity() {
        return Member.builder()
                .id(id).password(password).email(email)
                .build();
    }
    
    
}