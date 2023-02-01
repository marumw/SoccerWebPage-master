package com.example.spring03.dto;

import com.example.spring03.domain.Member;
import com.example.spring03.domain.MemberRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberRegisterDto {
    
    private String username;
    private String password;
    private String email;
 

    public Member toEntity() {
        return Member.builder()
                .username(username).password(password).email(email)
                .build()
                .addRole(MemberRole.USER);
    }
    
}