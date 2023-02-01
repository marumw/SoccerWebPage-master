package com.example.spring03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring03.domain.SoccerReply;

public interface SoccerReplyRepository extends JpaRepository<SoccerReply, Integer> {

	// 해당 포스트 번호에 달려 있는 모든 댓글 리스트를 아이디 내림차순으로 검색
	List<SoccerReply> findByPostIdOrderByIdDesc(Integer postId);
	
}
