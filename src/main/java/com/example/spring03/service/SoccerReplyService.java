package com.example.spring03.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.domain.SoccerReply;
import com.example.spring03.dto.SoccerReplyReadDto;
import com.example.spring03.dto.SoccerReplyResiterDto;
import com.example.spring03.dto.SoccerReplyUpdateDto;
import com.example.spring03.repository.SoccerPostsRepository;
import com.example.spring03.repository.SoccerReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SoccerReplyService {
	
	private final SoccerReplyRepository soccerReplyRepository;
	private final SoccerPostsRepository soccerPostsRepository;
	
	@Transactional
	public Integer create(SoccerReplyResiterDto dto) {
		log.info("create(dto = {})", dto);
	
		SoccerPosts post = soccerPostsRepository.findById(dto.getPostId()).get();
		SoccerReply soccerReply = SoccerReply.builder().post(post)
								.replyText(dto.getReplyText()).writer(dto.getWriter()).build();
		
		soccerReply = soccerReplyRepository.save(soccerReply);
		
		return soccerReply.getId();
	}
	
	// 데이터베이스 테이블에서 포스트 번호에 달려있는 모든 댓글을 검색해서 리스트로 리턴.
	@Transactional(readOnly = true)
	public List<SoccerReplyReadDto> readReplies(Integer postId){
		log.info("readReplies(postId = {})", postId);
		
		List<SoccerReply> list = soccerReplyRepository.findByPostIdOrderByIdDesc(postId);
		
		return list.stream().map(SoccerReplyReadDto::fromEntity).toList();
	}

	@Transactional(readOnly = true)
	public SoccerReplyReadDto readReply(Integer replyId) {
		log.info("SoccerReplyReadDto(replyId = {})", replyId);
		
		SoccerReply entity = soccerReplyRepository.findById(replyId).get();
		
		return SoccerReplyReadDto.fromEntity(entity);
	}

	public Integer delete(Integer replyId) {
		log.info("delete(replyId = {})", replyId);
		
		soccerReplyRepository.deleteById(replyId);
		
		return replyId;
	}

	@Transactional
	public Integer update(SoccerReplyUpdateDto dto) {
		log.info("update(dto = {})", dto);
		
		// 수정하려는 댓글 아이디로 댓글 엔터티 객체를 검색.
		SoccerReply entity = soccerReplyRepository.findById(dto.getReplyId()).get();
		
		// 데이터베이스 테이블에서 검색한 엔터티 객체를 수정.
		entity.update(dto.getReplyText());
		// @Transactional이 적용된 경우에 메서드 실행이 끝날 때 DB에 자동으로 save(update)됨.
		
		return dto.getReplyId();
	}
	 

}
       