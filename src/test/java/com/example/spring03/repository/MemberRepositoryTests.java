package com.example.spring03.repository;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.FetchType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03.domain.Member;
import com.example.spring03.domain.MemberRole;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberRepositoryTests {
    
    @Autowired
    private MemberRepository memberRepository;
    
   @Test
    public void test() {
        Assertions.assertNotNull(memberRepository);
        
       
        Member user1 = Member.builder()
                .username("user3").password("333").email("user3@test.com")
                .build();
        user1.addRole(MemberRole.USER);
        memberRepository.save(user1); 
        
    }

//    @Test
//    public void test2() {
//       
//        Member admin1 = Member.builder()
//                .username("admin1").password("admin1").email("admin1@itwill.co.kr")
//               .build();
//        admin1.addRole(MemberRole.USER).addRole(MemberRole.ADMIN);
//        memberRepository.save(admin1);
//    }
//    
//    @Test
//    @Transactional 
//    public void test3() {
//      
//        List<Member> list = memberRepository.findAll();
//        for (Member m : list) {
//            log.info(m.toString());
//        }
//    }
//    
//    @Test
//    public void test4() {
//       
//        try {
//            Member m = memberRepository.findByUsername("TEST").get();
//            log.info("일치하는 사용자 있음: {}", m);
//        } catch (NoSuchElementException e) {
//            log.info("일치하는 사용자 없음.");
//        }
//    }
    
}