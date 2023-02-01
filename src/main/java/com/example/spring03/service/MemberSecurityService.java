package com.example.spring03.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring03.domain.Member;
import com.example.spring03.domain.MemberRole;
import com.example.spring03.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
    
    private final MemberRepository memberRepository;
    
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Member> member = this.memberRepository.findByUsername(username);
        if(member.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
       Member mem = member.get();
       List<GrantedAuthority> authorities = new ArrayList<>();
       if("admin".equals(username)) {
           authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getRole()));
       } else {
           authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getRole()));
       }
       
       return new User(mem.getUsername(), mem.getPassword(), authorities);
    }
}