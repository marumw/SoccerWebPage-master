package com.example.spring03.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.spring03.domain.Member;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberSecurityDto extends User {
    
    private String username;
    private String password;
    private String email;
    

    public MemberSecurityDto(String username, String password, String email,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        
        this.username = username;
        this.password = password;
        this.email = email;
       
    }

    public static MemberSecurityDto fromEntity(Member m) {
        List<GrantedAuthority> authorities = m.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRole()))
                .collect(Collectors.toList());
        MemberSecurityDto dto = new MemberSecurityDto(m.getUsername(), 
                m.getPassword(), m.getEmail(), authorities);
        
        return dto;
    }
    
}