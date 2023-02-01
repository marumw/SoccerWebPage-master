package com.example.spring03.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass 
@EntityListeners(value = { AuditingEntityListener.class })

public class BaseTimeEntity {
    
    @CreatedDate
    private LocalDateTime createdTime;
    
    @LastModifiedDate // 최종 수정 날짜(시간)
    private LocalDateTime modifiedTime;

}