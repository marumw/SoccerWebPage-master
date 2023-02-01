package com.example.spring03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring03.domain.Files;

public interface FileRepository extends JpaRepository<Files, Long> {

}
