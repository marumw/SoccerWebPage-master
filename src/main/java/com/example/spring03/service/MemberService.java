package com.example.spring03.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03.domain.Member;
import com.example.spring03.dto.MemberRegisterDto;
import com.example.spring03.dto.MemberUpdateDto;
import com.example.spring03.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    
    public String checkUsername(String username) {
        log.info("checkUsername(username={})", username);
        
        Optional<Member> result = memberRepository.findByUsername(username);
        if (result.isPresent()) { 
            return "nok"; 
        } else {
            return "ok"; 
        }
        
    }

    public Member registerMember(MemberRegisterDto dto) {
        log.info("registerMember(dto={})", dto);
        
        
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member entity = memberRepository.save(dto.toEntity());
        log.info("entity = {}", entity);
        
        return entity;
    }
    
    // 회원 목록
    public Page<Member> read(Pageable pageable) {
        log.info("read()");

        return memberRepository.findByOrderByIdDesc(pageable);
    }
    
    
    @Transactional(readOnly = true)
    public Member read(String username) {
        log.info("read(id={})", username);

        return memberRepository.findByUsername(username).get();
    }
    
    public Member readByMember(Integer id) {
        log.info("read(id = {})", id);
        
        return memberRepository.findById(id).get();
    }

    //비번변경
    @Transactional
    public Integer update(MemberUpdateDto dto) {
        log.info("update(dto={})", dto);
        
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
   
        Member entity = memberRepository.findById(dto.getId()).get();
        
        entity.update(dto.getPassword(),dto.getEmail());
        
        return entity.getId();
    }

    public Integer delete(Integer id) {
        log.info("delete(id={})", id);

        memberRepository.deleteById(id);
        return id;
    }

}