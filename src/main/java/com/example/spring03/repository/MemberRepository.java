package com.example.spring03.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring03.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    
    @EntityGraph(attributePaths = "roles") 
    Optional<Member> findByUsername(String username);
    
    Page<Member> findByOrderByIdDesc(Pageable pageable);
}